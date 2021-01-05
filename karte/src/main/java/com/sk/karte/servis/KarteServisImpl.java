package com.sk.karte.servis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.karte.dto.KartaDto;
import com.sk.karte.dto.KarticaDto;
import com.sk.karte.dto.KorisnikDto;
import com.sk.karte.dto.KreiranjeKarteDto;
import com.sk.karte.dto.LetDto;
import com.sk.karte.dto.RezervisanjeKarteDto;
import com.sk.karte.exceptions.NotFoundException;
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
		ResponseEntity<KorisnikDto> responseEntityKorisnikDto = 
				korisnikServisRestTemplate.exchange("/korisnik/" + kreiranjeKarteDto.getIdUsera(), HttpMethod.GET, null, KorisnikDto.class);
		
		System.out.println(kreiranjeKarteDto.getIdKartice());
		if(kreiranjeKarteDto.getIdKartice() == null) throw new NotFoundException("Mora uneses karticu");
		int flag = 0;
		for (KarticaDto kartica: responseEntityKorisnikDto.getBody().getKartice()) {
			if(kartica.getId() == kreiranjeKarteDto.getIdKartice()) flag = 1;
		}
		if(flag == 0) throw new NotFoundException("Nema te kartice");
		
		ResponseEntity<LetDto> responseEntityLetDto = 
				letoviServisRestTemplate.exchange("/letovi/" + kreiranjeKarteDto.getIdLeta(), HttpMethod.GET, null, LetDto.class);
		
		if(responseEntityLetDto.getBody().getBrojKarata() >= responseEntityLetDto.getBody().getAvionDto().getKapacitetPutnika()) {
			throw new NotFoundException("kapacitet je pun ne moze da se doda karta");
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
		
		return null;
	}

	@Override
	public List<KartaDto> findByLet(Long idLeta) {
		Optional<List<Karta>> lista = karteRepository.findByIdLeta(idLeta);
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
		System.out.println(lista.get());
		List<Karta> nova = lista.get();
		for (Karta karta : nova) {
			karta.setOtkazanaKarta(true);
			karteRepository.save(karta);
		}
	}

}
