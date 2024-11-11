package com.una.unadb4.controllers;

import com.una.unadb4.models.Empresa;
import com.una.unadb4.models.User;
import com.una.unadb4.services.UserService;
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

//@Named
//@RequestScoped
@Model
@SessionScoped
public class UserController implements Serializable {
    private User user;
    private List<User> users;
    private final UserService userService; // Servicio para operaciones CRUD
    private final Logger logger;

    public UserController() throws Exception { // HOLA
        this.userService = new UserService();
        this.user = new User();
        this.logger = Logger.getLogger(this.getClass().getName());
        this.userService.getAll();
        loadUsers();
    }

    public void loadUsers() {
        try {
            this.users = userService.getAll();
            System.out.println(this.users);
            //addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Algo"+this.users.get(0).getBrand());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al cargar el usuario", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudieron cargar los usuarios.");
        }
    }

    public String saveUser() {
        try {
            userService.store(user);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario guardado correctamente.");
            user = new User();
            loadUsers();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar el Usuario", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo guardar el Usuario.");
        }
        return null;
    }

    public String updateUsuario() {
        try {
            userService.update(user);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario actualizado correctamente.");
            return this.backList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar el Usuario", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo actualizar el Usuario.");
        }
        return null;
    }

    public void deleteUser(String id) {
        try {
            userService.delete(id);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Usuario eliminado correctamente.");
            loadUsers(); // Recarga la lista
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al eliminar el Usuario", e);
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el Usuario.");
        }
    }

    public String setEdit(User user) {
        this.user = user;
        return "update-usuario?faces-redirect=true";
    }

    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public String backList(){
        loadUsers();
        return "/usuario/list-usuario?faces-redirect=true";
    }



    public String login(){
        UserService userService = new UserService();
        User userCheck = userService.checkCredentials(getUser().getUserName(), getUser().getPassword());
        if(userCheck!=null){
            this.user = userCheck;
            FacesContext.getCurrentInstance().getExternalContext()
                    .getSessionMap().put("userLogged", this.user);
            return "/home?faces-redirect=true";
        }else{
            FacesMessage fMsg=new FacesMessage("Usuario o contraseña invalido");
            FacesContext.getCurrentInstance().addMessage(null, fMsg);
            return null;
        }

    }
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().remove("userLogged");
        return "/index?faces-redirect=true";
    }

    public User getUser() {
        //if(user==null){
        //    user = new User();
        //}
        return user;
    }

    public void setUser(Empresa empresa) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
