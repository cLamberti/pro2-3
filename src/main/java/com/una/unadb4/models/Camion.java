package com.una.unadb4.models;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;

@Named
@ViewScoped
@Entity(name = "camiones")
public class Camion implements Serializable {
    @Id
    private String licensePlate;

    @Column(name = "numero_unidad")
    private String unitNumber;

    @Column(name = "marca")
    private String brand;

    @Column(name = "color")
    private String color;

    @Column(name = "modelo")
    private String model;

    @Column(name = "tipo")
    private String type; // "Pick-Up", "Blindado", "Particular"

    // Getters y Setters
    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

