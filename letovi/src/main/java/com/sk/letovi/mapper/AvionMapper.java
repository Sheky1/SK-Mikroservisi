package com.sk.letovi.mapper;

import org.springframework.stereotype.Component;

import com.sk.letovi.dto.AvionDto;
import com.sk.letovi.dto.KreiranjeAvionaDto;
import com.sk.letovi.model.Avion;

@Component
public class AvionMapper {

    public AvionDto avionToAvionDto(Avion avion) {
    	AvionDto avionDto = new AvionDto();
    	avionDto.setId(avion.getId());
    	avionDto.setKapacitetPutnika(avion.getKapacitetPutnika());
    	avionDto.setNaziv(avion.getNaziv());
        return avionDto;
    }

	public Avion kreiranjeAvionaDtoToAvion(KreiranjeAvionaDto kreiranjeAvionaDto) {
		Avion avion = new Avion();
		avion.setKapacitetPutnika(kreiranjeAvionaDto.getKapacitetPutnika());
		avion.setNaziv(kreiranjeAvionaDto.getNaziv());
        return avion;
	}

}
