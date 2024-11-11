package com.una.unadb4.controllers;

import com.una.unadb4.models.Remesa;
import com.una.unadb4.models.User;
import com.una.unadb4.services.RemesaService;
import jakarta.annotation.Nonnull;
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
    private RemesaService remesaService; // Servicio para operaciones CRUD
    private Logger logger;
    User userLogged;

    public RemesaController() {
        userLogged = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogged");
        this.remesaService = new RemesaService();
        this.logger = Logger.getLogger(this.getClass().getName());
        this.remesas = new ArrayList<>();
    }

    public void loadRemesas() {
        logger.log(Level.INFO, "Loading remesas");
        this.remesas.clear();
        try {
            if(userLogged!=null) {
                if(userLogged.isAdmin()) {
                    this.remesas = remesaService.getAll();
                }else{
                    this.remesas = remesaService.getByUser(userLogged.getUsername());
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error al cargar las remesas", e);
            this.addMessage(e.getMessage());
        }
    }

    public String saveRemesa(@Nonnull Remesa remesa) {
        Object id = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
        logger.info("Guardando nueva remesa");
        try {
            remesaService.store(remesa);
            return "/remesa/list-remesa?faces-redirect=true";
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error al guardar la remesa", e);
            addMessage(e.getMessage());
            return null;
        }
    }

    public void updateRemesa(@Nonnull Remesa remesa) {
        try {
            remesaService.update(remesa);
            this.addMessage("Exito remesa actualizada correctamente.");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error al actualizar la remesa", e);
            addMessage(e.getMessage());
        }
    }

    public String deleteRemesa(@Nonnull String id) {
        try {
            int Sid = Integer.parseInt(id);
            Remesa aux = remesaService.getById(Sid);
            remesaService.delete(id);
            this.addMessage("Éxito, Remesa eliminada correctamente.");
            return "/home?faces-redirect=true";
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error al eliminar la remesa", e);
            addMessage(e.getMessage());
            return null;
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
