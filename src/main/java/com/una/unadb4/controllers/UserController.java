package com.una.unadb4.controllers;

import com.una.unadb4.models.User;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class UserController {
    private User user;

    public User getUser() {
        if(user==null){
            user = new User();
        }
        return user;
    }
    public String login(){
        if(this.user.getUsername().equals("admin") && this.user.getPassword().equals("admin")){
            this.user.setAdmin(true);
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
}
