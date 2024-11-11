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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Base64;

@Model
@SessionScoped
public class AgenteController implements Serializable {

    private Agente agente; // Camión actual en el formulario
    private List<Agente> agentes; // Lista de todos los agentes
    private final AgenteService agenteService; // Servicio para operaciones CRUD
    private final Logger logger;
    private String tempFilename;
    private byte[] tempFile;

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
            //addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Algo"+this.agentes.get(0).getBrand());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar los agentes", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los agentes.");
        }
    }

    public void handleUpload(FileUploadEvent event){
        this.tempFilename=event.getFile().getFileName();
        this.tempFile=event.getFile().getContent();
        addMessage(FacesMessage.SEVERITY_ERROR, "INFO", "Archivo "+tempFilename+" subido");
    }

    // Método para guardar un nuevo agente
    public String saveAgente() {
        try {
            if(this.tempFile!=null){
                agente.setPhoto(this.tempFile);
                agente.setFilename(this.tempFilename);
            }
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

    public String getPhotoAsBase64(Agente agente) {
        if (agente.getPhoto() != null && agente.getPhoto().length > 0) {
            return "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(agente.getPhoto());
        } else {
            // Retorna una imagen predeterminada si no hay foto
            return "data:image/jpeg;base64," + getDefaultPhoto();
        }
    }

    private String getDefaultPhoto() {
        // Inserta aquí la codificación Base64 de una imagen predeterminada
        String defaultPhotoBase64 = "BASE64_STRING_DE_IMAGEN_DEFAULT";
        return defaultPhotoBase64;
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
