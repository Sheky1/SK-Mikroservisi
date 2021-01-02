package com.sk.korisnicki.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class KreiranjeKarticeDto {

    @NotBlank
	private String vlasnik;
    @NotBlank
    @Length(min = 16, max = 16)
	private String brKartice;
    @NotBlank
    @Length(min = 3, max = 3)
	private String sigurnosniBroj;
    
	public String getVlasnik() {
		return vlasnik;
	}
	public void setVlasnik(String vlasnik) {
		this.vlasnik = vlasnik;
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
