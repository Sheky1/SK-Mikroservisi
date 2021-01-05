package com.sk.letovi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.letovi.dto.KreiranjeLetaDto;
import com.sk.letovi.dto.LetDto;
import com.sk.letovi.security.CheckSecurity;
import com.sk.letovi.servis.LetoviServis;

@RestController
@RequestMapping("/letovi")
public class LetoviController {

    private LetoviServis letoviServis;

    public LetoviController(LetoviServis letoviServis) {
        this.letoviServis = letoviServis;
    }

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Page<LetDto>> getAll(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(letoviServis.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<LetDto> getLet(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(letoviServis.findLet(id), HttpStatus.OK);
    }

    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<LetDto> saveLet(@RequestHeader("Authorization") String authorization, @RequestBody @Valid KreiranjeLetaDto kreiranjeLetaDto) {
        return new ResponseEntity<>(letoviServis.add(kreiranjeLetaDto), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<LetDto> deleteLet(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        return new ResponseEntity<>(letoviServis.delete(id), HttpStatus.OK);
    }

}
