package com.sk.korisnicki.servis;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import com.sk.korisnicki.model.Korisnik;
import com.sk.korisnicki.repository.KorisnickiRepository;
import com.sk.korisnicki.security.TokenServis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.POJONode;
import com.sk.korisnicki.dto.KartaDto;
import com.sk.korisnicki.dto.KorisnikDto;
import com.sk.korisnicki.dto.LetDto;
import com.sk.korisnicki.dto.RegistracijaKorisnikaDto;
import com.sk.korisnicki.dto.TokenOdgovorDto;
import com.sk.korisnicki.dto.TokenZahtevDto;

import com.sk.korisnicki.exceptions.NotFoundException;
import com.sk.korisnicki.mappers.KorisnikMapper;

@Service
public class KorisnickiServisImpl implements KorisnickiServis {

    private TokenServis tokenServis;
    private KorisnickiRepository korisnickiRepository;
    private KorisnikMapper korisnikMapper;
    private RestTemplate karteServisRestTemplate;

    public KorisnickiServisImpl(KorisnickiRepository korisnickiRepository, TokenServis tokenServis, KorisnikMapper korisnikMapper,
    						RestTemplate karteServisRestTemplate) {
        this.korisnickiRepository = korisnickiRepository;
        this.tokenServis = tokenServis;
        this.korisnikMapper = korisnikMapper;
        this.karteServisRestTemplate = karteServisRestTemplate;
    }

    @Override
    public Page<KorisnikDto> findAll(Pageable pageable) {
        return korisnickiRepository.findAll(pageable).map(korisnikMapper::korisnikToKorisnikDto);
    }

    @Override
    public KorisnikDto findKorisnik(Long id) {
    	Korisnik korisnik = korisnickiRepository
                .findKorisnikById(id)
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", id)));
        return korisnikMapper.korisnikToKorisnikDto(korisnik);
    }

    @Override
    public KorisnikDto add(RegistracijaKorisnikaDto registracijaKorisnikaDto) {
        Korisnik noviKorisnik = korisnikMapper.registracijaKorisnikaDtoToKorisnik(registracijaKorisnikaDto);
        korisnickiRepository.save(noviKorisnik);
        return korisnikMapper.korisnikToKorisnikDto(noviKorisnik);
    }

	@Override
	public KorisnikDto update(Long id, RegistracijaKorisnikaDto registracijaKorisnikaDto) {
    	Korisnik korisnik = korisnickiRepository
                .findKorisnikById(id)
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", id)));
        Korisnik updateKorisnik = korisnikMapper.korisnikToUpdateKorisnik(korisnik, registracijaKorisnikaDto);
        korisnickiRepository.save(updateKorisnik);
        return korisnikMapper.korisnikToKorisnikDto(updateKorisnik);
	}

    @Override
    public TokenOdgovorDto login(TokenZahtevDto tokenZahtevDto) {
    	Korisnik korisnik = korisnickiRepository
                .findKorisnikByEmailAndSifra(tokenZahtevDto.getEmail(), tokenZahtevDto.getSifra())
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa email-om: %s i sifrom: %s ne postoji.", tokenZahtevDto.getEmail(), tokenZahtevDto.getSifra())));
        Claims claims = Jwts.claims();
        claims.put("id", korisnik.getId());
        claims.put("role", korisnik.getRole().getNaziv());
        //Generate token
        return new TokenOdgovorDto(tokenServis.generate(claims));
    }

	@Override
	public void rezervacijaKarte(Long id, int duzinaLeta) {
    	Korisnik korisnik = korisnickiRepository
                .findKorisnikById(id)
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", id)));
    	
    	korisnik.setMilje(korisnik.getMilje() + duzinaLeta);
    	if(korisnik.getMilje() < 1000) korisnik.setRank("Bronza");
    	else if(korisnik.getMilje() >= 1000 && korisnik.getMilje() < 10000) korisnik.setRank("Srebro");
    	else korisnik.setRank("Zlato");
    	
    	korisnickiRepository.save(korisnik);
		
	}
	
	@Override
	public void otkazivanjeKarte(Long idLeta, int duzinaLeta) {
		
		@SuppressWarnings("unchecked")
		ResponseEntity<List<KartaDto>> responseEntityKarteDto = 
				karteServisRestTemplate.exchange("/karte/let/" + idLeta, HttpMethod.GET, null, (Class<List<KartaDto>>)(Object)List.class);

		
    	List<KartaDto> listaKarata = responseEntityKarteDto.getBody();
    	
		ObjectMapper mapper = new ObjectMapper();
		List<KartaDto> kara = mapper.convertValue(
				listaKarata,
			    new TypeReference<List<KartaDto>>() { });
		

    	List<Long> sviIdKorisnika = new ArrayList<Long>();
    	
    	for (KartaDto kartaDto : kara) {
			sviIdKorisnika.add(kartaDto.getIdUsera());
		}
		for (Long id : sviIdKorisnika) {
			Optional<Korisnik> korisnikOpt = korisnickiRepository.findKorisnikById(id);
			Korisnik korisnik = korisnikOpt.get();
			
			korisnik.setMilje(korisnik.getMilje() - duzinaLeta);
			
	    	if(korisnik.getMilje() < 1000) korisnik.setRank("Bronza");
	    	else if(korisnik.getMilje() >= 1000 && korisnik.getMilje() < 10000) korisnik.setRank("Srebro");
	    	else korisnik.setRank("Zlato");

	    	korisnickiRepository.save(korisnik);
		}
    	
	}
}
