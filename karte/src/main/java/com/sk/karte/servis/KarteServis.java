package com.sk.karte.servis;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.sk.karte.dto.KartaDto;
import com.sk.karte.dto.KreiranjeKarteDto;

@Service
public interface KarteServis {

//    Page<KartaDto> findAll(Pageable pageable);

    KartaDto add(KreiranjeKarteDto kreiranjeLetaDto);
    List<KartaDto> findByLet(Long idLeta);
    Page<KartaDto> findByKorisnik(Long id, Pageable pageable);
//    KartaDto delete(Long id);
	void otkazivanjeKarte(Long idLeta, int duzinaLeta);

}
