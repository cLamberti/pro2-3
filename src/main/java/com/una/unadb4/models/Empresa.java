package com.una.unadb4.models;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;

@Named
@ViewScoped
@Entity(name = "empresas")
public class Empresa implements Serializable {
    @Id
    private String companyName;

    @Column(name = "direccion")
    private String address;

    @Column(name = "contacto")
    private String contact;

    // Getters y Setters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}

