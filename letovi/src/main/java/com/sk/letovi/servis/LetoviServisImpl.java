package com.sk.letovi.servis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sk.letovi.dto.KreiranjeLetaDto;
import com.sk.letovi.dto.LetDto;
import com.sk.letovi.exceptions.NotFoundException;
import com.sk.letovi.mapper.LetoviMapper;
import com.sk.letovi.model.Avion;
import com.sk.letovi.model.Let;
import com.sk.letovi.repository.AvionRepository;
import com.sk.letovi.repository.LetoviRepository;

@Service
public class LetoviServisImpl implements LetoviServis {

    private LetoviRepository letoviRepository;
    private AvionRepository avionRepository;
    private LetoviMapper letoviMapper;

    public LetoviServisImpl(LetoviRepository letoviRepository, LetoviMapper letoviMapper, AvionRepository avionRepository) {
        this.letoviRepository = letoviRepository;
        this.avionRepository = avionRepository;
        this.letoviMapper = letoviMapper;
    }
    
	@Override
	public Page<LetDto> findAll(Pageable pageable) {
        return letoviRepository.findAll(pageable).map(letoviMapper::letToLetDto);
	}

	@Override
	public LetDto add(KreiranjeLetaDto kreiranjeLetaDto) {
    	Avion avion = avionRepository
                .findAvionById(kreiranjeLetaDto.getIdAviona())
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", kreiranjeLetaDto.getIdAviona())));
    	
        Let novLet = letoviMapper.kreiranjeLetaDtoToLet(kreiranjeLetaDto);
        novLet.setAvion(avion);
        letoviRepository.save(novLet);
        
        avion.getLetovi().add(novLet);
        avionRepository.save(avion);
        
        return letoviMapper.letToLetDto(novLet);
	}

	@Override
	public LetDto delete(Long id) {
    	Let let= letoviRepository
                .findLetById(id)
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", id)));
    	Avion avion = avionRepository
                .findAvionById(let.getAvion().getId())
                .orElseThrow(() -> new NotFoundException(String
                .format("Korisnik sa id-jem: %s ne postoji.", let.getAvion().getId())));
    	
    	avion.getLetovi().remove(let);
    	avionRepository.save(avion);
    	letoviRepository.delete(let);
    	return letoviMapper.letToLetDto(let);
	}

}
