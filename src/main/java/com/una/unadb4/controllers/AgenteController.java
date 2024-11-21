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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Base64;

@Model
@ViewScoped
public class AgenteController implements Serializable {

    private Agente agente; // Camión actual en el formulario
    private List<Agente> agentes; // Lista de todos los agentes
    private final AgenteService agenteService; // Servicio para operaciones CRUD
    private final Logger logger;
    private String tempFilename;
    private UploadedFile tempFile;

    public AgenteController() throws Exception { // HOLA
        this.agentes = new ArrayList<>();
        this.agenteService = new AgenteService();
        this.logger = Logger.getLogger(this.getClass().getName());
        loadAgentes();
    }
    public UploadedFile getTempFile() {return tempFile;}

    public void setTempFile(UploadedFile tempFile) {this.tempFile = tempFile;}

    public void loadAgentes() {
        logger.info("Loading Agentes");
        this.agentes.clear();
        try {
            this.agentes = agenteService.getAll();
            this.setOnAgent();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar los agentes", e);
            addMessage(e.getMessage());
        }
    }


    // Método para guardar un nuevo agente
    public void saveAgente(Agente agente) {
        if(this.tempFile!=null){
            agente.setPhoto(this.tempFile.getContent());
            agente.setFilename(this.tempFile.getFileName());
        }
        logger.info("Saving Agente: " + agente.getId());
        try {
            agenteService.store(agente);
            addMessage("Agente guardado correctamente.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar el agente", e);
            this.addMessage(e.getMessage());
        }
    }
    public void setOnAgent (){
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") +
                File.separatorChar + "resources" + File.separatorChar + "agents" + File.separatorChar;
        this.agentes.forEach(new Consumer<Agente>() {
            @Override
                public void accept(Agente agente) {
                    if (!agente.getFilename().isEmpty()) {
                        String filePath = path + File.separatorChar + agente.getFilename();
                        if (!new File(filePath).exists()) {
                            try {
                                FileOutputStream out = new FileOutputStream(new File(filePath));
                                InputStream in = new ByteArrayInputStream(agente.getPhoto());
                                int r = 0;
                                while ((r = in.read()) >=0) {
                                    out.write(r);
                                }
                                out.flush();
                                out.close();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

            }
        });
    }



    // Método para actualizar un agente existente
    public String updateAgente(Agente agente) {
        try {
            agenteService.update(agente);
            addMessage("Agente actualizado correctamente.");
            return this.backList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar el agente", e);
            addMessage(e.getMessage());
        }
        return null;
    }

    // Método para eliminar un agente por su ID
    public void deleteAgente(String id) {
        try {
            agenteService.delete(id);
            addMessage("Agente eliminado correctamente.");
            loadAgentes(); // Recarga la lista de agentes
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar el agente", e);
            addMessage(e.getMessage());
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
    private void addMessage(String msg) {
        FacesMessage fMsg = new FacesMessage();
        FacesContext.getCurrentInstance().addMessage(null, fMsg);
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
