package com.sk.korisnicki.dto;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(indexes = {@Index(columnList = "brPasosa", unique = true), @Index(columnList = "email", unique = true)})
public class KorisnikDto {

    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String brPasosa;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBrPasosa() {
		return brPasosa;
	}
	public void setBrPasosa(String brPasosa) {
		this.brPasosa = brPasosa;
	}
	
}
