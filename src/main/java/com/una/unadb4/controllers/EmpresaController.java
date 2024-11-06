package com.una.unadb4.controllers;

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

    public EmpresaController() {
        this.empresaService = new EmpresaService();
        this.empresa = new Empresa();
        this.logger = Logger.getLogger(this.getClass().getName());
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
            this.empresa = new Empresa(); // Reinicia el formulario
            loadEmpresas(); // Recarga la lista de empresas
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar la empresa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la empresa.");
        }
    }

    // Método para actualizar una empresa existente
    public void updateEmpresa() {
        try {
            empresaService.update(empresa);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Empresa actualizada correctamente.");
            loadEmpresas(); // Recarga la lista de empresas
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar la empresa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar la empresa.");
        }
    }

    // Método para eliminar una empresa por su ID
    public void deleteEmpresa(int id) {
        try {
            empresaService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Empresa eliminada correctamente.");
            loadEmpresas(); // Recarga la lista de empresas
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar la empresa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la empresa.");
        }
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

