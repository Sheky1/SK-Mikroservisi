package com.sk.karte.servis;

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
import com.sk.karte.dto.KorisnikDto;
import com.sk.karte.dto.KreiranjeKarteDto;
import com.sk.karte.dto.LetDto;
import com.sk.karte.dto.RezervisanjeKarteDto;
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

    public KarteServisImpl(KarteRepository karteRepository, RestTemplate letoviServisRestTemplate, 
    						RestTemplate korisnikServisRestTemplate, JmsTemplate jmsTemplate,
    						@Value("${destination.rezervisanje-karte}") String rezervisanjeKarte,
    						ObjectMapper objectMapper) {
        this.karteRepository = karteRepository;
        this.letoviServisRestTemplate = letoviServisRestTemplate;
        this.korisnikServisRestTemplate = korisnikServisRestTemplate;
        this.jmsTemplate = jmsTemplate;
        this.rezervisanjeKarte = rezervisanjeKarte;
        this.objectMapper = objectMapper;
    }

	@Override
	public KartaDto add(KreiranjeKarteDto kreiranjeLetaDto) {
		ResponseEntity<LetDto> responseEntityLetDto = 
				letoviServisRestTemplate.exchange("/letovi/" + kreiranjeLetaDto.getIdLeta(), HttpMethod.GET, null, LetDto.class);

		ResponseEntity<KorisnikDto> responseEntityKorisnikDto = 
				korisnikServisRestTemplate.exchange("/korisnik/" + kreiranjeLetaDto.getIdUsera(), HttpMethod.GET, null, KorisnikDto.class);
		
		int cena = 0;
		int cenaLeta = responseEntityLetDto.getBody().getCena();
		int duzinaLeta = responseEntityLetDto.getBody().getDuzinaLeta();
		String rank = responseEntityKorisnikDto.getBody().getRank(); 
		
		if(rank.equals("Zlato")) cena = cenaLeta - (cenaLeta * 20) / 100;
		else if(rank.equals("Srebro")) cena = cenaLeta - (cenaLeta * 10) / 100; 
		else cena = cenaLeta;
		
		Karta novaKarta = new Karta(kreiranjeLetaDto.getIdUsera(), kreiranjeLetaDto.getIdLeta(), cena);
		karteRepository.save(novaKarta);
		
		try {
			jmsTemplate.convertAndSend(rezervisanjeKarte, objectMapper.writeValueAsString(new RezervisanjeKarteDto(kreiranjeLetaDto.getIdUsera(), duzinaLeta)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
