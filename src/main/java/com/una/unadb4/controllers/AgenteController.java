package com.una.unadb4.controllers;

import com.una.unadb4.models.Agente;
import com.una.unadb4.services.AgenteService;
import com.una.unadb4.services.CamionService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Model
@SessionScoped
public class AgenteController implements Serializable {

    private Agente agente; // Camión actual en el formulario
    private List<Agente> agentes; // Lista de todos los camiones
    private final AgenteService agenteService; // Servicio para operaciones CRUD
    private final Logger logger;

    public AgenteController() throws Exception { // HOLA
        this.agenteService = new AgenteService();
        this.agente = new Agente();
        this.logger = Logger.getLogger(this.getClass().getName());
        this.agenteService.getAll();
        loadAgentes();
    }

    public void loadAgentes() {
        try {
            this.agentes = agenteService.getAll();
            System.out.println(this.agentes);
            //addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Algo"+this.camiones.get(0).getBrand());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar los agentes", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los agentesf.");
        }
    }

    // Método para guardar un nuevo agente
    public String saveAgente() {
        try {
            agenteService.store(agente);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Agente guardado correctamente.");
            agente = new Agente();
            loadAgentes();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar el agente", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el agente.");
        }
        return null;
    }

    // Método para actualizar un agente existente
    public String updateAgente() {
        try {
            agenteService.update(agente);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "agente actualizado correctamente.");
            return this.backList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar el agente", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el agente.");
        }
        return null;
    }

    // Método para eliminar un agente por su ID
    public void deleteAgente(String id) {
        try {
            agenteService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Agente eliminado correctamente.");
            loadAgentes(); // Recarga la lista de agentes
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar el agente", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el agente.");
        }
    }

    public String setEdit(Agente agente){
        this.agente = agente;
        return "update-agente?faces-redirect=true";
    }

    public String backList(){
        loadAgentes();
        return "/agente/list-agente?faces-redirect=true";
    }


    // Método para mostrar mensajes en la interfaz
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    // Getters y Setters
    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public List<Agente> getAgentes() {
        return agentes;
    }

    public void setAgentes(List<Agente> agentes) {
        this.agentes = agentes;
    }
}
