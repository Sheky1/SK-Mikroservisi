package com.sk.karte.dto;

public class KorisnikDto {

    private Long id;
    private String ime;
    private String prezime;
    private String email;
    private String brPasosa;
    private String rank;
    
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
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
}
