package com.sk.karte.dto;

public class KarticaDto {

    private Long id;
	private String imeVlasnika;
	private String prezimeVlasnika;
	private String brKartice;
	private String sigurnosniBroj;
	
	public String getImeVlasnika() {
		return imeVlasnika;
	}
	public void setImeVlasnika(String imeVlasnika) {
		this.imeVlasnika = imeVlasnika;
	}
	public String getPrezimeVlasnika() {
		return prezimeVlasnika;
	}
	public void setPrezimeVlasnika(String prezimeVlasnika) {
		this.prezimeVlasnika = prezimeVlasnika;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBrKartice() {
		return brKartice;
	}
	public void setBrKartice(String brKartice) {
		this.brKartice = brKartice;
	}
	public String getSigurnosniBroj() {
		return sigurnosniBroj;
	}
	public void setSigurnosniBroj(String sigurnosniBroj) {
		this.sigurnosniBroj = sigurnosniBroj;
	}
		
}
