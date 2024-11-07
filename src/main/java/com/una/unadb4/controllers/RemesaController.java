package com.una.unadb4.controllers;

import com.una.unadb4.models.Remesa;
import com.una.unadb4.services.RemesaService;
import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Model
@ViewScoped
public class RemesaController implements Serializable {

    private Remesa newRemesa;
    private List<Remesa> remesas; // Lista de todas las remesas
    private final RemesaService remesaService; // Servicio para operaciones CRUD
    private final Logger logger;

    public RemesaController() {
        this.remesaService = new RemesaService();
        this.logger = Logger.getLogger(this.getClass().getName());
        this.newRemesa = new Remesa();
        this.remesas = new ArrayList<>();
    }

    public void loadRemesas() {
        try {
            this.remesas = remesaService.getAll();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error al agregar la remesa: {0}", e.getMessage());
            addMessage("Error no se pudieron cargar las remesas.");
        }
    }

    public String saveRemesa() {
        logger.info("Agregando nueva remesa: " + newRemesa.getId());
        try {
            remesaService.store(newRemesa);
            addMessage("Éxito, remesa guardada correctamente.");
            return "/remesa/add.remesa?faces-redirect=true";
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar la remesa", e);
            addMessage("Error no se pudo guardar la remesa.");
            return null;
        }
    }

    public void updateRemesa() {
        try {
            remesaService.update(newRemesa);
            addMessage("Exito remesa actualizada correctamente.");
            loadRemesas(); // Recarga la lista de remesas
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar la remesa", e);
            addMessage("Error No se pudo actualizar la remesa.");
        }
    }

    public void deleteRemesa(int id) {
        try {
            remesaService.delete(id);
            addMessage("Éxito, Remesa eliminada correctamente.");
            loadRemesas(); // Recarga la lista de remesas
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar la remesa", e);
            addMessage("No se pudo eliminar la remesa.");
        }
    }

    private void addMessage(String message) {
        FacesMessage msg = new FacesMessage( message);
        FacesContext.getCurrentInstance().addMessage(null,msg);
    }

    // Getters y Setters
    public Remesa getRemesa() {
        return newRemesa;
    }

    public void setRemesa(Remesa remesa) {
        this.newRemesa = remesa;
    }

    public List<Remesa> getRemesas() {
        return remesas;
    }

    public void setRemesas(List<Remesa> remesas) {
        this.remesas = remesas;
    }
}
