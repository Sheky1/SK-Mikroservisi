package com.sk.korisnicki.servis;

import org.springframework.stereotype.Service;

import com.sk.korisnicki.dto.KarticaDto;
import com.sk.korisnicki.dto.KreiranjeKarticeDto;

@Service
public interface KarticeServis {

    KarticaDto add(KreiranjeKarticeDto kreiranjeKarticeDto);
    
}
