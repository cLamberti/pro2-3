package com.una.unadb4.models;

import com.una.unadb4.models.Agente;
import com.una.unadb4.models.Camion;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;

@Named
@ViewScoped
@Entity(name = "remesas")
public class Remesa implements Serializable {
    @Id
    private int id;

    @Column(name = "tipo")
    private String type; // "Entrega" o "Envío"

    @Column(name = "fecha")
    private String date;

    @Column(name = "hora")
    private String time;

    @Column(name = "estado")
    private String status; // "Solicitada", "Autorizada", etc.

    @ManyToOne
    private Camion camion;

    @ManyToOne
    private Agente agente;

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public Agente getAgent() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }
}

