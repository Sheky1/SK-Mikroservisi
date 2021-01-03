package com.sk.korisnicki.servis;

import org.springframework.stereotype.Service;

import com.sk.korisnicki.dto.KarticaDto;
import com.sk.korisnicki.dto.KreiranjeKarticeDto;
import com.sk.korisnicki.exceptions.NotFoundException;
import com.sk.korisnicki.mappers.KarticaMapper;
import com.sk.korisnicki.model.Kartica;
import com.sk.korisnicki.model.Korisnik;
import com.sk.korisnicki.repository.KarticeRepository;
import com.sk.korisnicki.repository.KorisnickiRepository;

@Service
public class KarticeServisImpl implements KarticeServis {

	private KarticeRepository karticeRepository;
	private KorisnickiRepository korisnickiRepository;
    private KarticaMapper karticaMapper;

    public KarticeServisImpl(KarticeRepository karticeRepository, KarticaMapper karticaMapper, KorisnickiRepository korisnickiRepository) {
        this.karticeRepository = karticeRepository;
        this.korisnickiRepository = korisnickiRepository;
        this.karticaMapper = karticaMapper;
    }
	
	@Override
	public KarticaDto add(KreiranjeKarticeDto kreiranjeKarticeDto) {
    	Korisnik korisnik = korisnickiRepository
                .findKorisnikById(kreiranjeKarticeDto.getIdUsera())
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", kreiranjeKarticeDto.getIdUsera())));
    	
        Kartica novaKartica = karticaMapper.kreiranjeKarticeDtoToKartica(kreiranjeKarticeDto);
        novaKartica.setKorisnik(korisnik);
        karticeRepository.save(novaKartica);
        
        korisnik.getKartice().add(novaKartica);
        korisnickiRepository.save(korisnik);
        
        return karticaMapper.karticaToKarticaDto(novaKartica);
	}

}
