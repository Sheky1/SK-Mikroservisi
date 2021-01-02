package com.sk.korisnicki.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String naziv;
    @Column
    private String opis;

    public Role() {
    }

    public Role(String naziv, String opis) {
        this.naziv = naziv;
        this.opis = opis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return naziv;
    }

    public void setName(String name) {
        this.naziv = name;
    }

    public String getDescription() {
        return opis;
    }

    public void setDescription(String description) {
        this.opis = description;
    }
}
