package com.sk.korisnicki.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class RegistracijaKorisnikaDto {

    @Email
    private String email;
    @NotBlank
    private String ime;
    @NotBlank
    private String prezime;
    @NotBlank
    @Length(min = 8, max = 20)
    private String sifra;
    @NotBlank
    @Length(min = 13, max = 13)
    private String brPasosa;
    
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getBrPasosa() {
		return brPasosa;
	}
	public void setBrPasosa(String brPasosa) {
		this.brPasosa = brPasosa;
	}
    
    
    
}
