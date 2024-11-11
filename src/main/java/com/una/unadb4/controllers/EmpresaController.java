package com.una.unadb4.controllers;

import com.una.unadb4.models.Camion;
import com.una.unadb4.models.Empresa;
import com.una.unadb4.services.CamionService;
import com.una.unadb4.services.EmpresaService;
import jakarta.enterprise.context.SessionScoped;
import jakarta.enterprise.inject.Model;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Model
@SessionScoped
public class EmpresaController implements Serializable {

    private Empresa empresa; // Camión actual en el formulario
    private List<Empresa> empresas; // Lista de todos los camiones
    private final EmpresaService empresaService; // Servicio para operaciones CRUD
    private final Logger logger;

    public EmpresaController() throws Exception { // HOLA
        this.empresaService = new EmpresaService();
        this.empresa = new Empresa();
        this.logger = Logger.getLogger(this.getClass().getName());
        loadEmpresas(); // Carga inicial de todos los camiones
    }

    public void loadEmpresas() {
        try {
            this.empresas = empresaService.getAll();
            System.out.println(this.empresas);
            //addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Algo"+this.camiones.get(0).getBrand());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar la empresa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar la empresa.");
        }
    }

    public String saveEmpresa() {
        try {
            empresaService.store(empresa);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Empresa guardado correctamente.");
            empresa = new Empresa();
            loadEmpresas();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar la empresa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la empresa.");
        }
        return null;
    }

    public String updateEmpresa() {
        try {
            empresaService.update(empresa);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Empresa actualizado correctamente.");
            return this.backList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar la empresa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar la empresa.");
        }
        return null;
    }

    public void deleteEmpresa(String id) {
        try {
            empresaService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Empresa eliminado correctamente.");
            loadEmpresas(); // Recarga la lista
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar la empresa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la empresa.");
        }
    }

    public String setEdit(Empresa empresa) {
        this.empresa = empresa;
        return "update-empresa?faces-redirect=true";
    }

    public String backList(){
        loadEmpresas();
        return "/empresa/list-empresa?faces-redirect=true";
    }


    // Método para mostrar mensajes en la interfaz
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    // Getters y Setters
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }
}
