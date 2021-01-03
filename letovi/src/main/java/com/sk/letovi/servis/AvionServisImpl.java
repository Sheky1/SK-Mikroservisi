package com.sk.letovi.servis;

import org.springframework.stereotype.Service;
import com.sk.letovi.dto.AvionDto;
import com.sk.letovi.dto.KreiranjeAvionaDto;
import com.sk.letovi.mapper.AvionMapper;
import com.sk.letovi.model.Avion;
import com.sk.letovi.repository.AvionRepository;

@Service
public class AvionServisImpl implements AvionServis {

    private AvionRepository avionRepository;
    private AvionMapper avionMapper;

    public AvionServisImpl(AvionRepository avionRepository, AvionMapper avionMapper) {
        this.avionRepository = avionRepository;
        this.avionMapper = avionMapper;
    }

	@Override
	public AvionDto add(KreiranjeAvionaDto kreiranjeAvionaDto) {
        Avion noviAvion = avionMapper.kreiranjeAvionaDtoToAvion(kreiranjeAvionaDto);
        avionRepository.save(noviAvion);
        return avionMapper.avionToAvionDto(noviAvion);
	}

}
