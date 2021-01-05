package com.sk.karte.mapper;

import org.springframework.stereotype.Component;

import com.sk.karte.dto.KartaDto;
import com.sk.karte.model.Karta;

@Component
public class KarteMapper {

    public KartaDto kartaToKartaDto(Karta karta) {
    	KartaDto kartaDto = new KartaDto();
    	kartaDto.setId(karta.getId());
    	kartaDto.setIdLeta(karta.getIdLeta());
    	kartaDto.setIdUsera(karta.getIdUsera());
    	kartaDto.setCena(karta.getCena());
    	kartaDto.setOtkazanaKarta(karta.isOtkazanaKarta());
        return kartaDto;
    }
//
//	public Karta kreiranjeLetaDtoToLet(KreiranjeKarteDto kreiranjeLetaDto) {
//		Karta let = new Karta();
//        let.setPocetnaDestinacija(kreiranjeLetaDto.getPocetnaDestinacija());
//        let.setKrajnjaDestinacija(kreiranjeLetaDto.getKrajnjaDestinacija());
//        let.setDuzinaLeta(kreiranjeLetaDto.getDuzinaLeta());
//        let.setCena(kreiranjeLetaDto.getCena());
//        return let;
//	}
}
