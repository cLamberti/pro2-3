package com.una.unadb4.controllers;

import com.una.unadb4.models.Camion;
import com.una.unadb4.services.CamionService;
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
public class CamionController implements Serializable {

    private Camion camion; // Camión actual en el formulario
    private List<Camion> camiones; // Lista de todos los camiones
    private final CamionService camionService; // Servicio para operaciones CRUD
    private final Logger logger;

    public CamionController() {
        this.camionService = new CamionService();
        this.camion = new Camion();
        this.logger = Logger.getLogger(this.getClass().getName());
        loadCamiones(); // Carga inicial de todos los camiones
    }

    // Método para cargar todos los camiones desde la base de datos
    public void loadCamiones() {
        try {
            this.camiones = camionService.getAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar los camiones", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los camiones.");
        }
    }

    // Método para guardar un nuevo camión
    public void saveCamion() {
        try {
            camionService.store(camion);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Camión guardado correctamente.");
            this.camion = new Camion(); // Reinicia el formulario
            loadCamiones(); // Recarga la lista de camiones
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar el camión", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el camión.");
        }
    }

    // Método para actualizar un camión existente
    public void updateCamion() {
        try {
            camionService.update(camion);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Camión actualizado correctamente.");
            loadCamiones(); // Recarga la lista de camiones
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar el camión", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el camión.");
        }
    }

    // Método para eliminar un camión por su ID
    public void deleteCamion(int id) {
        try {
            camionService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Camión eliminado correctamente.");
            loadCamiones(); // Recarga la lista de camiones
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar el camión", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el camión.");
        }
    }

    // Método para mostrar mensajes en la interfaz
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    // Getters y Setters
    public Camion getCamion() {
        return camion;
    }

    public void setCamion(Camion camion) {
        this.camion = camion;
    }

    public List<Camion> getCamiones() {
        return camiones;
    }

    public void setCamiones(List<Camion> camiones) {
        this.camiones = camiones;
    }
}
