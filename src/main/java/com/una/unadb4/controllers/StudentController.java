package com.una.unadb4.controllers;

import com.una.unadb4.models.Student;
import com.una.unadb4.services.StudentService;
import jakarta.annotation.Nonnull;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@RequestScoped
public class StudentController {
    private List<Student> students;
    private Logger logger;
    private StudentService service;
    public StudentController() {
        this.logger = Logger.getLogger(this.getClass().getName());
        this.service = new StudentService();
        this.students=new ArrayList<>();
    }

    private void addMessage(String message) {
        FacesMessage msg = new FacesMessage( message);
        FacesContext.getCurrentInstance().addMessage(null,msg);
    }
    public List<Student> getStudents() {
        return students;
    }
    public void loadStudents(){
        this.students.clear();
        try {
            this.students=service.getAll();
        }catch (Exception e){
            logger.log(Level.WARNING, e.getMessage(),e);
            this.addMessage("Error al cargar los registros");
        }
    }
    public void loadStudent(){
        String id=FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap().get("id");
        logger.info("Cargando la información de id#"+id);
        try{
            Student st=service.getById(Integer.parseInt(id));
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("stupdate",st);
        }catch (Exception e){
            logger.log(Level.WARNING, e.getMessage(),e);
            this.addMessage("Error el registro");
        }
    }
    public String update(@Nonnull Student student){
        logger.info("Actualizando el registro con id"+student.getId());
        try{
            service.update(student);
            return "/student/list-students?faces-redirect=true";
        }catch (Exception e){
            logger.log(Level.WARNING, e.getMessage(),e);
            this.addMessage("Error al actualizar el registro");
            e.printStackTrace();
            return null;
        }
    }
}
