package com.sk.letovi.mapper;

import org.springframework.stereotype.Component;

import com.sk.letovi.dto.AvionDto;
import com.sk.letovi.dto.KreiranjeLetaDto;
import com.sk.letovi.dto.LetDto;
import com.sk.letovi.model.Let;

@Component
public class LetoviMapper {

    public LetDto letToLetDto(Let let) {
    	LetDto letDto = new LetDto();
    	letDto.setId(let.getId());
    	letDto.setPocetnaDestinacija(let.getPocetnaDestinacija());
    	letDto.setKrajnjaDestinacija(let.getKrajnjaDestinacija());
    	letDto.setDuzinaLeta(let.getDuzinaLeta());
    	letDto.setCena(let.getCena());
    	letDto.setBrojKarata(let.getBrojKarata());
    	AvionDto avionDto = new AvionDto();
    	avionDto.setId(let.getAvion().getId());
    	avionDto.setKapacitetPutnika(let.getAvion().getKapacitetPutnika());
    	avionDto.setNaziv(let.getAvion().getNaziv());
    	letDto.setAvionDto(avionDto);
        return letDto;
    }

	public Let kreiranjeLetaDtoToLet(KreiranjeLetaDto kreiranjeLetaDto) {
		Let let = new Let();
        let.setPocetnaDestinacija(kreiranjeLetaDto.getPocetnaDestinacija());
        let.setKrajnjaDestinacija(kreiranjeLetaDto.getKrajnjaDestinacija());
        let.setDuzinaLeta(kreiranjeLetaDto.getDuzinaLeta());
        let.setCena(kreiranjeLetaDto.getCena());
        let.setOtkazanLet(false);
        return let;
	}
}
