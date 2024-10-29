package com.una.unadb4.controllers;

import com.una.unadb4.models.Professor;
import com.una.unadb4.services.ProfessorService;
import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Model
@ViewScoped
public class ProfessorController implements Serializable {
    private List<Professor> professors;
    private ProfessorService service;
    private Logger logger;
    public ProfessorController() {
        this.professors = new ArrayList<>();
        this.service=new ProfessorService();
        this.logger = Logger.getLogger(this.getClass().getName());
    }
    public void loadProfessors() {
        logger.info("Cargando los professores");
        this.professors.clear();
        try{
            this.professors=service.getAll();
        }catch (Exception e){
            logger.log(Level.WARNING,e.getMessage(),e);
            this.addMessage(e.getMessage());
        }
    }
    private void addMessage(String message) {
        FacesMessage msg = new FacesMessage( message);
        FacesContext.getCurrentInstance().addMessage(null,msg);
    }
    public List<Professor> getProfessors() {return professors;}
}
