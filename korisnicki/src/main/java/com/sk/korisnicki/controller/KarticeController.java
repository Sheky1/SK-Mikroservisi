package com.sk.korisnicki.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.korisnicki.dto.KarticaDto;
import com.sk.korisnicki.dto.KreiranjeKarticeDto;
import com.sk.korisnicki.servis.KarticeServis;

@RestController
@RequestMapping("/kartice")
public class KarticeController {

    private KarticeServis karticeServis;

    public KarticeController(KarticeServis karticeServis) {
        this.karticeServis = karticeServis;
    }

    @PostMapping
    public ResponseEntity<KarticaDto> saveKorisnik(@RequestBody @Valid KreiranjeKarticeDto kreiranjeKarticeDto) {
        return new ResponseEntity<>(karticeServis.add(kreiranjeKarticeDto), HttpStatus.CREATED);
    }

}
