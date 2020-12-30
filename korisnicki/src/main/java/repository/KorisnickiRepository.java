package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Korisnik;

import java.util.Optional;


@Repository
public interface KorisnickiRepository extends JpaRepository<Korisnik, Long> {

    Optional<Korisnik> findKorisnikByEmailAndSifra(String email, String sifra);
}
