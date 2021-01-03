package com.sk.karte.servis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sk.karte.dto.KartaDto;
import com.sk.karte.dto.KreiranjeKarteDto;
import com.sk.karte.repository.KarteRepository;

@Service
public class KarteServisImpl implements KarteServis {

    private KarteRepository letoviRepository;

    public KarteServisImpl(KarteRepository letoviRepository) {
        this.letoviRepository = letoviRepository;
    }

	@Override
	public KartaDto add(KreiranjeKarteDto kreiranjeLetaDto) {
		
		return null;
	}

}
