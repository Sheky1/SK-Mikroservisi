package com.sk.korisnicki.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sk.korisnicki.dto.KarticaDto;
import com.sk.korisnicki.dto.KorisnikDto;
import com.sk.korisnicki.dto.RegistracijaKorisnikaDto;
import com.sk.korisnicki.dto.UpdateKorisnikaDto;
import com.sk.korisnicki.model.Kartica;
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
    	korisnikDto.setRank(korisnik.getRank());
    	List<KarticaDto> karticeDto = new ArrayList<KarticaDto>();
    	for (Kartica kartica: korisnik.getKartice()) {
    		KarticaDto karticaDto = new KarticaDto();
        	karticaDto.setId(kartica.getId());
        	karticaDto.setImeVlasnika(kartica.getImeVlasnika());
        	karticaDto.setPrezimeVlasnika(kartica.getPrezimeVlasnika());
        	karticaDto.setBrKartice(kartica.getBrKartice());
        	karticaDto.setSigurnosniBroj(kartica.getSigurnosniBroj());
        	karticeDto.add(karticaDto);
		}
    	korisnikDto.setKartice(karticeDto);
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
        korisnik.setVerifikovan(false);
        korisnik.setKartice(new ArrayList<Kartica>());
        return korisnik;
    }

    public Korisnik korisnikToUpdateKorisnik(Korisnik korisnik, UpdateKorisnikaDto registracijaKorisnikaDto) {
    	System.out.println("Parame " + registracijaKorisnikaDto.getEmail() + registracijaKorisnikaDto.getIme() + registracijaKorisnikaDto.getPrezime());
    	if(!registracijaKorisnikaDto.getEmail().equals("")) {
    		korisnik.setEmail(registracijaKorisnikaDto.getEmail());
    		korisnik.setVerifikovan(false);
    	}
    	if(!registracijaKorisnikaDto.getIme().equals("")) korisnik.setIme(registracijaKorisnikaDto.getIme());
    	if(!registracijaKorisnikaDto.getPrezime().equals("")) korisnik.setPrezime(registracijaKorisnikaDto.getPrezime());
    	if(!registracijaKorisnikaDto.getBrPasosa().equals("")) korisnik.setBrPasosa(registracijaKorisnikaDto.getBrPasosa());
    	if(!registracijaKorisnikaDto.getSifra().equals("")) korisnik.setSifra(registracijaKorisnikaDto.getSifra());
        return korisnik;
    }
}
