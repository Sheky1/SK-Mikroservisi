package com.sk.letovi.servis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sk.letovi.dto.AvionDto;
import com.sk.letovi.dto.KreiranjeAvionaDto;
import com.sk.letovi.dto.LetDto;
import com.sk.letovi.exceptions.NotFoundException;
import com.sk.letovi.exceptions.NotValidException;
import com.sk.letovi.mapper.AvionMapper;
import com.sk.letovi.model.Avion;
import com.sk.letovi.model.Let;
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
    public Page<AvionDto> findAll(Pageable pageable) {
        return avionRepository.findAll(pageable).map(avionMapper::avionToAvionDto);
    }

	@Override
	public AvionDto add(KreiranjeAvionaDto kreiranjeAvionaDto) {
        Avion noviAvion = avionMapper.kreiranjeAvionaDtoToAvion(kreiranjeAvionaDto);
        avionRepository.save(noviAvion);
        return avionMapper.avionToAvionDto(noviAvion);
	}

	@Override
	public AvionDto delete(Long id) {
    	Avion avion= avionRepository
                .findAvionById(id)
                .orElseThrow(() -> new NotFoundException(String
                .format("Avion sa id-jem: %s ne postoji.", id)));
    	
    	if(avion.getLetovi().size() != 0) throw new NotValidException("Ne mozete obrisati avion jer postoje letovi vezani za njega");
    	
    	avionRepository.delete(avion);
    	return avionMapper.avionToAvionDto(avion);
	}

}
