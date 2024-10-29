package com.una.unadb4.models;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import java.io.Serializable;

@Named
@ViewScoped
@Entity(name="profesores")
public class Professor implements Serializable {
    @Id
    private Integer id;
    @Column(name = "nombre")
    private String name;
    @Column(name="apellidos")
    private String lastName;
    @Column(name = "correo")
    private String email;
    @Lob
    @Column(name="imagen",nullable = true)
    private byte[] photo;
    @Column(name="nombre_imagen",nullable = true)
    private String filename;
    public Professor() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
