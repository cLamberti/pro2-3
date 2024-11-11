package com.una.unadb4.controllers;

import com.una.unadb4.models.Agente;
import com.una.unadb4.models.Camion;
import com.una.unadb4.models.Empresa;
import com.una.unadb4.models.Remesa;
import com.una.unadb4.models.User;
import com.una.unadb4.services.AgenteService;
import com.una.unadb4.services.CamionService;
import com.una.unadb4.services.EmpresaService;
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

    private Remesa remesa;
    private List<Remesa> remesas;
    private List<Camion> camiones;
    private List<Empresa> empresas;
    private List<Agente> agentes;// Lista de todas las remesas
    private RemesaService remesaService; // Servicio para operaciones CRUD
    private Logger logger;
    User userLogged;

    public RemesaController() {
        userLogged = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userLogged");
        this.remesaService = new RemesaService();
        this.remesa = new Remesa();
        this.logger = Logger.getLogger(this.getClass().getName());
        this.remesas = new ArrayList<>();
        //loadRemesas();
        loadCamiones();
        loadEmpresas();
        loadAgentes();

    }

    public void loadCamiones() {
        try {
            CamionService camionService = new CamionService();
            this.camiones = camionService.getAll();
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error al cargar las remesas", e);
        }
    }

    public void loadEmpresas() {
        try {
            EmpresaService empresaService = new EmpresaService();
            this.empresas = empresaService.getAll();
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error al cargar las remesas", e);
        }
    }

    public void loadAgentes() {
        try {
            AgenteService agenteService = new AgenteService();
            this.agentes = agenteService.getAll();
        }catch (Exception e) {
            logger.log(Level.WARNING, "Error al cargar las remesas", e);
        }
    }

    public void loadRemesas() {
        logger.log(Level.INFO, "Loading remesas");
        this.remesas.clear();
        try {
            if(userLogged!=null) {
                if(userLogged.isAdmin()) {
                    this.remesas = remesaService.getAll();
                }else{
                    this.remesas = remesaService.getByUser(userLogged.getUserName());
                }
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error al cargar las remesas", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar la remesa.");
        }
    }

    public String saveRemesa() {
        try {
            this.remesa.setAgente(agentes.stream().filter(c -> c.getId().equals(this.remesa.getAgente().getId())).findFirst().get());
            this.remesa.setEmpresa(empresas.stream().filter(c -> c.getCompanyName().equals(this.remesa.getEmpresa().getCompanyName())).findFirst().get());
            this.remesa.setCamion(camiones.stream().filter(c -> c.getLicensePlate().equals(this.remesa.getCamion().getLicensePlate())).findFirst().get());
            this.remesa.setUser(userLogged);
            remesaService.store(remesa);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Remesa guardado correctamente.");
            remesa = new Remesa();
            loadRemesas();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar la remesa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar la remesa.");
        }
        return null;
    }

    public String updateRemesa() {
        try {
            remesaService.update(remesa);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Remesa actualizado correctamente.");
            return this.backList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar la Remesa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar la remesa.");
        }
        return null;
    }

    public void deleteRemesa(String id) {
        try {
            remesaService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Remesa eliminado correctamente.");
            loadRemesas(); // Recarga la lista
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar la remesa", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la remesa.");
        }
    }

    public String setEdit(Remesa remesa) {
        this.remesa = remesa;
        return "update-remesa?faces-redirect=true";
    }

    public String backList(){
        loadRemesas();
        return "/remesa/list-remesa?faces-redirect=true";
    }


    // Método para mostrar mensajes en la interfaz
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    // Getters y Setters
    public Remesa getRemesa() {
        return remesa;
    }

    public void setEmpresa(Remesa remesa) {
        this.remesa = remesa;
    }

    public List<Remesa> getRemesas() {
        return remesas;
    }

    public void setRemesas(List<Remesa> remesas) {
        this.remesas = remesas;
    }


    public List<Camion> getCamiones() {
        return camiones;
    }

    public void setCamiones(List<Camion> camiones) {
        this.camiones = camiones;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(List<Empresa> empresas) {
        this.empresas = empresas;
    }

    public List<Agente> getAgentes() {
        return agentes;
    }

    public void setAgentes(List<Agente> agentes) {
        this.agentes = agentes;
    }
}
