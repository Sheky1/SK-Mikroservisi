package com.sk.karte.servis;

import org.springframework.stereotype.Service;

import com.sk.karte.dto.KartaDto;
import com.sk.karte.dto.KreiranjeKarteDto;

@Service
public interface KarteServis {

//    Page<KartaDto> findAll(Pageable pageable);

    KartaDto add(KreiranjeKarteDto kreiranjeLetaDto);
//    KartaDto delete(Long id);

}
