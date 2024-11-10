package com.una.unadb4.models;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;

@Named
@ViewScoped
@Entity(name = "agentes")
public class Agente implements Serializable {
    @Id
    private String id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellidos")
    private String lastname;

    @Column(name = "tipo_agente")
    private String agentType; // "Conductor" o "Escolta"

    @Column(name = "foto",nullable = true)
    //private String photo;
    private byte[] photo; // Se almacenará la fotografía en la base de datos como un blob

    @Column(name="nombre_foto",nullable = true)
    private String filename;

    // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }


    /*public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }*/
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
