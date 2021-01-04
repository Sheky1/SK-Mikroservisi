package com.sk.karte.servis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sk.karte.configuration.KorisnikServisConfiguration;
import com.sk.karte.configuration.LetoviServisConfiguration;
import com.sk.karte.dto.KartaDto;
import com.sk.karte.dto.KorisnikDto;
import com.sk.karte.dto.KreiranjeKarteDto;
import com.sk.karte.dto.LetDto;
import com.sk.karte.repository.KarteRepository;

@Service
public class KarteServisImpl implements KarteServis {

    private KarteRepository letoviRepository;
    private RestTemplate letoviServisRestTemplate;
    private RestTemplate korisnikServisRestTemplate;

    public KarteServisImpl(KarteRepository letoviRepository, RestTemplate letoviServisRestTemplate, RestTemplate korisnikServisRestTemplate) {
        this.letoviRepository = letoviRepository;
        this.letoviServisRestTemplate = letoviServisRestTemplate;
        this.korisnikServisRestTemplate = korisnikServisRestTemplate;
    }

	@Override
	public KartaDto add(KreiranjeKarteDto kreiranjeLetaDto) {
		ResponseEntity<LetDto> responseEntityLetDto = 
				letoviServisRestTemplate.exchange("/letovi/" + kreiranjeLetaDto.getIdLeta(), HttpMethod.GET, null, LetDto.class);

		ResponseEntity<KorisnikDto> responseEntityKorisnikDto = 
				korisnikServisRestTemplate.exchange("/korisnik/" + kreiranjeLetaDto.getIdUsera(), HttpMethod.GET, null, KorisnikDto.class);
		
		System.out.println(responseEntityLetDto.getBody().getCena() + " " + responseEntityKorisnikDto.getBody().getBrPasosa());
		
		return null;
	}

}
