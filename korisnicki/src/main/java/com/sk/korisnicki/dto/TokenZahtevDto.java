package com.sk.korisnicki.dto;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;

public class TokenZahtevDto {

    @Email
    private String email;
    @Length(min = 8, max = 20)
    private String sifra;

    public TokenZahtevDto() {
    }

    public TokenZahtevDto(String email, String sifra) {
        this.email = email;
        this.sifra = sifra;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
}