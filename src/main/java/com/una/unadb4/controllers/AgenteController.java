package com.una.unadb4.controllers;

import com.una.unadb4.models.Agente;
import com.una.unadb4.services.AgenteService;
import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Model
@ViewScoped
public class AgenteController implements Serializable {

    private Agente agent; // Agente actual en el formulario
    private List<Agente> agents; // Lista de todos los agentes
    private final AgenteService agentService; // Servicio para operaciones CRUD
    private final Logger logger;

    public AgenteController() {
        this.agentService = new AgenteService();
        this.agent = new Agente();
        this.logger = Logger.getLogger(this.getClass().getName());
        loadAgents(); // Carga inicial de todos los agentes
    }

    // Método para cargar todos los agentes desde la base de datos
    public void loadAgents() {
        try {
            this.agents = agentService.getAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar los agentes", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los agentes.");
        }
    }

    // Método para guardar un nuevo agente
    public void saveAgent() {
        try {
            agentService.store(agent);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Agente guardado correctamente.");
            this.agent = new Agente(); // Reinicia el formulario
            loadAgents(); // Recarga la lista de agentes
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar el agente", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el agente.");
        }
    }

    // Método para actualizar un agente existente
    public void updateAgent() {
        try {
            agentService.update(agent);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Agente actualizado correctamente.");
            loadAgents(); // Recarga la lista de agentes
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar el agente", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el agente.");
        }
    }

    // Método para eliminar un agente por su ID
    public void deleteAgent(int id) {
        try {
            agentService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Agente eliminado correctamente.");
            loadAgents(); // Recarga la lista de agentes
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar el agente", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el agente.");
        }
    }

    // Método para mostrar mensajes en la interfaz
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    // Getters y Setters
    public Agente getAgent() {
        return agent;
    }

    public void setAgente(Agente agent) {
        this.agent = agent;
    }

    public List<Agente> getAgents() {
        return agents;
    }

    public void setAgents(List<Agente> agents) {
        this.agents = agents;
    }
}
