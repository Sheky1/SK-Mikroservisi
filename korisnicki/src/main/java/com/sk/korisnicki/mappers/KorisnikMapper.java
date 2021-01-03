package com.sk.korisnicki.mappers;

import org.springframework.stereotype.Component;

import com.sk.korisnicki.dto.KorisnikDto;
import com.sk.korisnicki.dto.RegistracijaKorisnikaDto;
import com.sk.korisnicki.model.Korisnik;
import com.sk.korisnicki.repository.RoleRepository;

@Component
public class KorisnikMapper {

    private RoleRepository roleRepository;

    public KorisnikMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public KorisnikDto korisnikToKorisnikDto(Korisnik korisnik) {
    	KorisnikDto korisnikDto = new KorisnikDto();
    	korisnikDto.setId(korisnik.getId());
    	korisnikDto.setEmail(korisnik.getEmail());
    	korisnikDto.setIme(korisnik.getIme());
    	korisnikDto.setPrezime(korisnik.getPrezime());
    	korisnikDto.setBrPasosa(korisnik.getBrPasosa());
        return korisnikDto;
    }

    public Korisnik registracijaKorisnikaDtoToKorisnik(RegistracijaKorisnikaDto registracijaKorisnikaDto) {
    	Korisnik korisnik = new Korisnik();
        korisnik.setMilje(0);
        korisnik.setRank("Bronza");
        korisnik.setEmail(registracijaKorisnikaDto.getEmail());
        korisnik.setIme(registracijaKorisnikaDto.getIme());
        korisnik.setPrezime(registracijaKorisnikaDto.getPrezime());
        korisnik.setBrPasosa(registracijaKorisnikaDto.getBrPasosa());
        korisnik.setSifra(registracijaKorisnikaDto.getSifra());
        korisnik.setRole(roleRepository.findRoleByNaziv("ROLE_USER").get());
        return korisnik;
    }

    public Korisnik korisnikToUpdateKorisnik(Korisnik korisnik, RegistracijaKorisnikaDto registracijaKorisnikaDto) {
        korisnik.setEmail(registracijaKorisnikaDto.getEmail());
        korisnik.setIme(registracijaKorisnikaDto.getIme());
        korisnik.setPrezime(registracijaKorisnikaDto.getPrezime());
        korisnik.setBrPasosa(registracijaKorisnikaDto.getBrPasosa());
        korisnik.setSifra(registracijaKorisnikaDto.getSifra());
        return korisnik;
    }
}
