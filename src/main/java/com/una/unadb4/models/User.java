package com.una.unadb4.models;

public class User {
    private String username;
    private String password;
    private Boolean admin;
    public User() {
        //this.isAdmin=true;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getAdmin() {
        return admin;
    }
    public void setAdmin(Boolean isAdmin) {
        this.admin = isAdmin;
    }
}
