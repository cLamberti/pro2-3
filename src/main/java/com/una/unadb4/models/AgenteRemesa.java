package com.una.unadb4.models;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;

public class AgenteRemesa {
    @OneToMany
    @PrimaryKeyJoinColumn(name = "idAgente")
    private Agente agente;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "idRemesa")
    private Remesa remesa;

    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public Remesa getRemesa() {
        return remesa;
    }

    public void setRemesa(Remesa remesa) {
        this.remesa = remesa;
    }
}
