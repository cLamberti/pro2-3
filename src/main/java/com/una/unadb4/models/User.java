package com.una.unadb4.models;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.type.NumericBooleanConverter;

import java.io.Serializable;

@Named
@ViewScoped
@Entity(name = "users")
public class User implements Serializable {
    @Id
    private String userName;
    @Column(name = "password")
    private String password;
    @Convert(converter = NumericBooleanConverter.class)
    @Column(name = "admin")
    private Boolean admin;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    /*public void setAdmin(boolean admin) {
        this.admin = admin;
    }*/

    public void setAdmin(Boolean admin) {  // Cambiado a Boolean
        this.admin = admin;
    }
}
