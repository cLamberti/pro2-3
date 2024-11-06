package com.una.unadb4.controllers;

import com.una.unadb4.models.Remesa;
import com.una.unadb4.services.RemesaService;
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
public class RemesaController implements Serializable {

    private Remesa remesa; // Remesa actual en el formulario
    private List<Remesa> remesas; // Lista de todas las remesas
    private final RemesaService remesaService; // Servicio para operaciones CRUD
    private final Logger logger;

    public RemesaController() {
        this.remesaService = new RemesaService();
        this.remesa = new Remesa();
        this.logger = Logger.getLogger(this.getClass().getName());
        loadRemesas(); // Carga inicial de todas las remesas
    }

    // Método para cargar todas las remesas desde la base de datos
    public void loadRemesas() {
        try {
            this.remesas = remesaService.getAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar las remesas", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar las remesas.");
        }
    }

    // Método para guardar una nueva remesa
    public void saveRemesa() {
        try {
            remesaService.store(remesa);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Remesa guardada correctamente.");
            this.remesa = new Remesa(); // Reinicia el formulario
            loadRemesas(); // Recarga la lista de remesas
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar la remesa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la remesa.");
        }
    }

    // Método para actualizar una remesa existente
    public void updateRemesa() {
        try {
            remesaService.update(remesa);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Remesa actualizada correctamente.");
            loadRemesas(); // Recarga la lista de remesas
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar la remesa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar la remesa.");
        }
    }

    // Método para eliminar una remesa por su ID
    public void deleteRemesa(int id) {
        try {
            remesaService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Remesa eliminada correctamente.");
            loadRemesas(); // Recarga la lista de remesas
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar la remesa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la remesa.");
        }
    }

    // Método para mostrar mensajes en la interfaz
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    // Getters y Setters
    public Remesa getRemesa() {
        return remesa;
    }

    public void setRemesa(Remesa remesa) {
        this.remesa = remesa;
    }

    public List<Remesa> getRemesas() {
        return remesas;
    }

    public void setRemesas(List<Remesa> remesas) {
        this.remesas = remesas;
    }
}
