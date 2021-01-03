package com.sk.letovi.servis;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sk.letovi.dto.KreiranjeLetaDto;
import com.sk.letovi.dto.LetDto;

@Service
public interface LetoviServis {

    Page<LetDto> findAll(Pageable pageable);

    LetDto add(KreiranjeLetaDto kreiranjeLetaDto);
    LetDto delete(Long id);

}
