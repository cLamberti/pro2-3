package com.una.unadb4.controllers;

import com.una.unadb4.models.Camion;
import com.una.unadb4.models.Empresa;
import com.una.unadb4.services.EmpresaService;
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
public class EmpresaController implements Serializable {

    private Empresa empresa; // Empresa actual en el formulario
    private List<Empresa> empresas; // Lista de todas las empresas
    private final EmpresaService empresaService; // Servicio para operaciones CRUD
    private final Logger logger;

    public EmpresaController() throws Exception {
        this.empresaService = new EmpresaService();
        this.empresa = new Empresa();
        this.logger = Logger.getLogger(this.getClass().getName());
        this.empresaService.getAll();
        loadEmpresas(); // Carga inicial de todas las empresas
    }

    // Método para cargar todas las empresas desde la base de datos
    public void loadEmpresas() {
        try {
            this.empresas = empresaService.getAll();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar las empresas", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar las empresas.");
        }
    }

    // Método para guardar una nueva empresa
    public void saveEmpresa() {
        try {
            empresaService.store(empresa);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Empresa guardada correctamente.");
            empresa = new Empresa(); // Reinicia el formulario
            loadEmpresas(); // Recarga la lista de empresas
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar la empresa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la empresa.");
        }
    }

    // Método para actualizar una empresa existente
    public String updateEmpresa() {
        try {
            empresaService.update(empresa);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Camión actualizado correctamente.");
            return this.backList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar el camión", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el camión.");
        }
        return null;
    }

    // Método para eliminar una empresa por su ID
    public void deleteEmpresa(String id) {
        try {
            empresaService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Empresa eliminada correctamente.");
            loadEmpresas(); // Recarga la lista de empresas
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

