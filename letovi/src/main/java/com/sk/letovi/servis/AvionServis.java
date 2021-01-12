package com.sk.letovi.servis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sk.letovi.dto.AvionDto;
import com.sk.letovi.dto.KreiranjeAvionaDto;

@Service
public interface AvionServis {

    AvionDto add(KreiranjeAvionaDto kreiranjeAvionaDto);
    AvionDto delete(Long id);
	Page<AvionDto> findAll(Pageable pageable);
}
