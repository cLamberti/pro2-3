package com.una.unadb4.models;

import com.una.unadb4.models.Agente;
import com.una.unadb4.models.Camion;
import com.una.unadb4.models.User;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Entity(name = "remesas")
public class Remesa implements Serializable {
    @Id
    private int id;

    @Column(name = "fecha")
    private String date;

    @Column(name = "hora")
    private String time;

    @Column(name = "estado")
    private String status; // "Solicitada", "Autorizada", etc.

    @OneToOne
    @JoinColumn(name = "idAgente")
    private Agente agente;

    @OneToOne
    @JoinColumn(name = "idCamion")
    private Camion camion;

    @OneToOne
    @JoinColumn(name = "idEmpresa")
    private Empresa empresa;

    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;

    public Remesa() {
        this.agente = new Agente();
        this.camion = new Camion();
        this.empresa = new Empresa();
        this.user = new User();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


