package com.sk.korisnicki.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class KreiranjeKarticeDto {

	private Long idUsera;
	@NotBlank
	private String imeVlasnika;
	@NotBlank
	private String prezimeVlasnika;
    @NotBlank
    @Length(min = 16, max = 16)
	private String brKartice;
    @NotBlank
    @Length(min = 3, max = 3)
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
	public Long getIdUsera() {
		return idUsera;
	}
	public void setIdUsera(Long idUsera) {
		this.idUsera = idUsera;
	}
}
