package model;

import javax.persistence.ManyToOne;

public class Korisnik {

	private Long id;
	private String ime;
	private String prezime;
	private String email;
	private int brojPasosa;
    @ManyToOne(optional = false)
    private Role role;
	
	
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
	public int getBrojPasosa() {
		return brojPasosa;
	}
	public void setBrojPasosa(int brojPasosa) {
		this.brojPasosa = brojPasosa;
	}
	
	
	
}
