package com.sk.letovi.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.letovi.dto.AvionDto;
import com.sk.letovi.dto.KreiranjeAvionaDto;
import com.sk.letovi.servis.AvionServis;

@RestController
@RequestMapping("/avion")
public class AvionController {

    private AvionServis avionServis;

    public AvionController(AvionServis avionServis) {
        this.avionServis = avionServis;
    }

    @PostMapping
    public ResponseEntity<AvionDto> saveKorisnik(@RequestBody @Valid KreiranjeAvionaDto kreiranjeAvionaDto) {
        return new ResponseEntity<>(avionServis.add(kreiranjeAvionaDto), HttpStatus.CREATED);
    }

}
