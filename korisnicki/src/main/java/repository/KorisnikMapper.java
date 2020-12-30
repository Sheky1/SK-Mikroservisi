package repository;

import org.springframework.stereotype.Component;

import dto.KorisnikDto;
import dto.RegistracijaKorisnikaDto;
import model.Korisnik;

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
        korisnik.setEmail(registracijaKorisnikaDto.getEmail());
        korisnik.setIme(registracijaKorisnikaDto.getIme());
        korisnik.setPrezime(registracijaKorisnikaDto.getPrezime());
        korisnik.setBrPasosa(registracijaKorisnikaDto.getBrPasosa());
        korisnik.setSifra(registracijaKorisnikaDto.getSifra());
        korisnik.setRole(roleRepository.findRoleByNaziv("ROLE_USER").get());
        //Set address
//        Address address = new Address();
//        address.setCountry(registracijaKorisnikaDto.getAddress().getCountry());
//        address.setCity(registracijaKorisnikaDto.getAddress().getCity());
//        address.setPostcode(registracijaKorisnikaDto.getAddress().getPostcode());
//        address.setStreet(registracijaKorisnikaDto.getAddress().getStreet());
//        address.setNumber(registracijaKorisnikaDto.getAddress().getNumber());
//        address.setApartmentNumber(registracijaKorisnikaDto.getAddress().getApartmentNumber());
//        user.setAddress(address);
        return korisnik;
    }
}
