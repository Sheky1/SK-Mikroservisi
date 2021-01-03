package com.sk.korisnicki.servis;

import org.springframework.stereotype.Service;

import com.sk.korisnicki.dto.KarticaDto;
import com.sk.korisnicki.dto.KreiranjeKarticeDto;
import com.sk.korisnicki.mappers.KarticaMapper;
import com.sk.korisnicki.model.Kartica;
import com.sk.korisnicki.repository.KarticeRepository;

@Service
public class KarticeServisImpl implements KarticeServis {

	private KarticeRepository karticeRepository;
    private KarticaMapper karticaMapper;

    public KarticeServisImpl(KarticeRepository karticeRepository, KarticaMapper karticaMapper) {
        this.karticeRepository = karticeRepository;
        this.karticaMapper = karticaMapper;
    }
	
	@Override
	public KarticaDto add(KreiranjeKarticeDto kreiranjeKarticeDto) {
        Kartica novaKartica = karticaMapper.kreiranjeKarticeDtoToKartica(kreiranjeKarticeDto);
        karticeRepository.save(novaKartica);
        return karticaMapper.karticaToKarticaDto(novaKartica);
	}

}
