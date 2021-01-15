package com.sk.letovi.dto;

import javax.validation.constraints.NotBlank;

public class KreiranjeAvionaDto {

    @NotBlank
	private String naziv;
	private int kapacitetPutnika;
	
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public int getKapacitetPutnika() {
		return kapacitetPutnika;
	}
	public void setKapacitetPutnika(int kapacitetPutnika) {
		this.kapacitetPutnika = kapacitetPutnika;
	}

}
