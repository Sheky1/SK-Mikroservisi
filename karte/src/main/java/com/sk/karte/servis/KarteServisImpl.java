package com.sk.karte.servis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.karte.dto.KartaDto;
import com.sk.karte.dto.KarticaDto;
import com.sk.karte.dto.KorisnikDto;
import com.sk.karte.dto.KreiranjeKarteDto;
import com.sk.karte.dto.LetDto;
import com.sk.karte.dto.RezervisanjeKarteDto;
import com.sk.karte.exceptions.NotFoundException;
import com.sk.karte.exceptions.NotValidException;
import com.sk.karte.mapper.KarteMapper;
import com.sk.karte.model.Karta;
import com.sk.karte.repository.KarteRepository;

@Service
public class KarteServisImpl implements KarteServis {

    private KarteRepository karteRepository;
    private RestTemplate letoviServisRestTemplate;
    private RestTemplate korisnikServisRestTemplate;
    private JmsTemplate jmsTemplate;
    private String rezervisanjeKarte;
    private ObjectMapper objectMapper;
    private KarteMapper karteMapper;

    public KarteServisImpl(KarteRepository karteRepository, RestTemplate letoviServisRestTemplate, 
    						RestTemplate korisnikServisRestTemplate, JmsTemplate jmsTemplate,
    						@Value("${destination.rezervisanje-karte}") String rezervisanjeKarte,
    						ObjectMapper objectMapper, KarteMapper karteMapper) {
        this.karteRepository = karteRepository;
        this.letoviServisRestTemplate = letoviServisRestTemplate;
        this.korisnikServisRestTemplate = korisnikServisRestTemplate;
        this.jmsTemplate = jmsTemplate;
        this.rezervisanjeKarte = rezervisanjeKarte;
        this.objectMapper = objectMapper;
        this.karteMapper = karteMapper;
    }

	@Override
	public KartaDto add(KreiranjeKarteDto kreiranjeKarteDto) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.TEXT_PLAIN);
	    headers.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwicm9sZSI6IlJPTEVfQURNSU4ifQ.dEuh0NrmaqBXOV5RrlIfUkTcKhXUJK0lf4gc7uanyuTmiTOdSkPEsMfB7CPt1pGOYz7JyVilV3cTs6u4IQtc7Q");
	    HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<KorisnikDto> responseEntityKorisnikDto = 
				korisnikServisRestTemplate.exchange("/korisnik/" + kreiranjeKarteDto.getIdUsera(), HttpMethod.GET, entity, KorisnikDto.class);
		
		System.out.println(kreiranjeKarteDto.getIdKartice());
		if(kreiranjeKarteDto.getIdKartice() == null) throw new NotValidException("Neophodno je uneti karticu kojom se placa let.");
		int flag = 0;
		for (KarticaDto kartica: responseEntityKorisnikDto.getBody().getKartice()) {
			if(kartica.getId() == kreiranjeKarteDto.getIdKartice()) flag = 1;
		}
		if(flag == 0) throw new NotFoundException("Zeljena kartica ne postoji");

		HttpHeaders headers2 = new HttpHeaders();
	    headers2.setContentType(MediaType.TEXT_PLAIN);
	    headers2.add("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpZCI6MSwicm9sZSI6IlJPTEVfQURNSU4ifQ.dEuh0NrmaqBXOV5RrlIfUkTcKhXUJK0lf4gc7uanyuTmiTOdSkPEsMfB7CPt1pGOYz7JyVilV3cTs6u4IQtc7Q");
	    HttpEntity<String> entity2 = new HttpEntity<String>(headers2);
		ResponseEntity<LetDto> responseEntityLetDto = 
				letoviServisRestTemplate.exchange("/letovi/" + kreiranjeKarteDto.getIdLeta(), HttpMethod.GET, entity2, LetDto.class);
		
		if(responseEntityLetDto.getBody().getBrojKarata() >= responseEntityLetDto.getBody().getAvionDto().getKapacitetPutnika()) {
			throw new NotValidException("Kapacitet trazenog leta je pun.");
		}
		
		int cena = 0;
		int cenaLeta = responseEntityLetDto.getBody().getCena();
		int duzinaLeta = responseEntityLetDto.getBody().getDuzinaLeta();
		String rank = responseEntityKorisnikDto.getBody().getRank(); 
		
		if(rank.equals("Zlato")) cena = cenaLeta - (cenaLeta * 20) / 100;
		else if(rank.equals("Srebro")) cena = cenaLeta - (cenaLeta * 10) / 100;
		else cena = cenaLeta;
		
		Karta novaKarta = new Karta(kreiranjeKarteDto.getIdUsera(), kreiranjeKarteDto.getIdLeta(), cena);
		karteRepository.save(novaKarta);
		
		try {
			jmsTemplate.convertAndSend(new ActiveMQTopic(rezervisanjeKarte), 
						objectMapper.writeValueAsString(new RezervisanjeKarteDto(kreiranjeKarteDto.getIdUsera(), kreiranjeKarteDto.getIdLeta(), duzinaLeta)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return karteMapper.kartaToKartaDto(novaKarta);
	}

	@Override
	public List<KartaDto> findByLet(Long idLeta) {
		Optional<List<Karta>> lista = karteRepository.findByIdLeta(idLeta);
		if(lista.isEmpty()) {
			System.out.println("ALOOOOOOOOO");
			return new ArrayList<KartaDto>();
		}
		System.out.println(idLeta);
		System.out.println(lista.get());
		List<Karta> nova = lista.get();
		List<KartaDto> zaVracanje = new ArrayList<KartaDto>();
		for (Karta karta : nova) {
			zaVracanje.add(karteMapper.kartaToKartaDto(karta));
		}
		return zaVracanje;
	}

	@Override
	public void otkazivanjeKarte(Long idLeta, int duzinaLeta) {
		Optional<List<Karta>> lista = karteRepository.findByIdLeta(idLeta);
		System.out.println(idLeta);
		System.out.println(lista);
		System.out.println(lista.get());
		List<Karta> nova = lista.get();
		for (Karta karta : nova) {
			karta.setOtkazanaKarta(true);
			karteRepository.save(karta);
		}
	}

	@Override
	public Page<KartaDto> findByKorisnik(Long idKorisnika, Pageable pageable) {
		Page<KartaDto> lista = karteRepository.findByIdUsera(pageable, idKorisnika).map(karteMapper::kartaToKartaDto);
		System.out.println(lista.getSort() + "--->sort");
		
		if(lista.isEmpty()) {
			System.out.println("ALOOOOOOOOO");
			return new PageImpl<KartaDto>(new ArrayList<KartaDto>());
		}
		List<KartaDto> listaSvihKarata = new ArrayList<KartaDto>();
		for (KartaDto kartaDto : lista) {
			listaSvihKarata.add(kartaDto);
			System.out.println(kartaDto);
		}
		List<KartaDto> zaVracanje = new ArrayList<KartaDto>();
		for (KartaDto karta : listaSvihKarata) {
			if(!karta.isOtkazanaKarta()) zaVracanje.add(karta);
		}
		System.out.println(zaVracanje + "vracanje");

		Page<KartaDto> karte = new PageImpl<KartaDto>(zaVracanje);
		
		return karte;
	}

}
