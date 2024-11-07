package com.una.unadb4.controllers;

import com.una.unadb4.models.Agente;
import com.una.unadb4.services.AgenteService;
import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import org.primefaces.model.file.UploadedFile;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Model
@ViewScoped
public class AgenteController implements Serializable {
    private List<Agente> agentes;
    private AgenteService agenteService;
    private Agente newAgente;
    private Logger logger;
    private UploadedFile file;

    public AgenteController() {
        this.agentes = new ArrayList();
        this.agenteService = new AgenteService();
        this.logger = Logger.getLogger(this.getClass().getName());
        this.newAgente = new Agente();
    }
    public void loadAgentes() {
        logger.info("Loading agentes...");
        this.agentes.clear();
        try {
            this.agentes = agenteService.getAll();
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage(), e);
            this.addMessage(e.getMessage());
        }
    }
    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void uploadFile () {
        if (file != null) {
            try {
                newAgente.setPhoto(file.getContent());
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Archivo subido"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al subir el archivo", ""));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage("Archivo no encontrado"));
        }
    }

    public String saveAgente() {
        logger.info("Agregando un nuevo agente: " + newAgente.getName());
        try {
            agenteService.store(newAgente);
            addMessage("Agente guardado exitosamente");
            newAgente = new Agente();
            return "/agente/add-agente.xhtml?faces-redirect=true";
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error al agregar el agente: {0}", e.getMessage());
            addMessage("Error al agregar el agente.");
            return null;
        }
    }

    public UploadedFile getFile() {return file;}

    private void addMessage(String message) {
        FacesMessage msg = new FacesMessage( message);
        FacesContext.getCurrentInstance().addMessage(null,msg);
    }
    public List<Agente> getAgentes() {return agentes;}

    public Agente getNewAgente() {return newAgente;}
    public void setNewAgente(Agente newAgente) {this.newAgente = newAgente;}
}
