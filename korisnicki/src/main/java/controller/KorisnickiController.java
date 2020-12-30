package controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.KorisnikDto;
import dto.RegistracijaKorisnikaDto;
import dto.TokenOdgovorDto;
import dto.TokenZahtevDto;
import security.CheckSecurity;

import javax.validation.Valid;

import servis.KorisnickiServis;

@RestController
@RequestMapping("/korisnik")
public class KorisnickiController {

    private KorisnickiServis korisnickiServis;

    public KorisnickiController(KorisnickiServis korisnickiServis) {
        this.korisnickiServis = korisnickiServis;
    }
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Page<KorisnikDto>> getAllUsers(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(korisnickiServis.findAll(pageable), HttpStatus.OK);
    }

//    @GetMapping("/{id}/kartica")
//    public ResponseEntity<DiscountDto> getDiscount(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(korisnickiServis.findDiscount(id), HttpStatus.OK);
//    }

    @PostMapping
    public ResponseEntity<KorisnikDto> saveUser(@RequestBody @Valid RegistracijaKorisnikaDto registracijaKorisnikaDro) {
        return new ResponseEntity<>(korisnickiServis.add(registracijaKorisnikaDro), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenOdgovorDto> loginUser(@RequestBody @Valid TokenZahtevDto tokenZahtevDto) {
        return new ResponseEntity<>(korisnickiServis.login(tokenZahtevDto), HttpStatus.OK);
    }

}
