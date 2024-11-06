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
    private String surname;

    @Column(name = "tipo_agente")
    private String agentType; // "Conductor" o "Escolta"

    @Column(name = "foto")
    private byte[] photo; // Se almacenará la fotografía en la base de datos como un blob

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAgentType() {
        return agentType;
    }

    public void setAgentType(String agentType) {
        this.agentType = agentType;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
