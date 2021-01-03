package com.sk.korisnicki.mappers;

import org.springframework.stereotype.Component;

import com.sk.korisnicki.dto.KarticaDto;
import com.sk.korisnicki.dto.KreiranjeKarticeDto;
import com.sk.korisnicki.model.Kartica;

@Component
public class KarticaMapper {


    public KarticaDto karticaToKarticaDto(Kartica kartica) {
    	KarticaDto karticaDto = new KarticaDto();
    	karticaDto.setId(kartica.getId());
    	karticaDto.setImeVlasnika(kartica.getImeVlasnika());
    	karticaDto.setPrezimeVlasnika(kartica.getPrezimeVlasnika());
    	karticaDto.setBrKartice(kartica.getBrKartice());
    	karticaDto.setSigurnosniBroj(kartica.getSigurnosniBroj());
        return karticaDto;
    }

    public Kartica kreiranjeKarticeDtoToKartica(KreiranjeKarticeDto kreiranjeKarticeDto) {
    	Kartica kartica = new Kartica();
        kartica.setImeVlasnika(kreiranjeKarticeDto.getImeVlasnika());
        kartica.setPrezimeVlasnika(kreiranjeKarticeDto.getPrezimeVlasnika());
        kartica.setBrKartice(kreiranjeKarticeDto.getBrKartice());
        kartica.setSigurnosniBroj(kreiranjeKarticeDto.getSigurnosniBroj());
        return kartica;
    }
}
