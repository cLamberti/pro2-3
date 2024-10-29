package com.una.unadb4.models;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;

@Named
@ViewScoped
@Entity(name = "materias")
public class Course implements Serializable {
    @Id
    private Integer nrc;
    @Column(name = "nombre")
    private String name;
    @Column(name = "creditos")
    private int credits;
    @Column(name = "codigo")
    private String code;
    @JoinColumn(name = "id_profesor")
    @ManyToOne
    private Professor professor;
    public Course() {}

    public Integer getNrc() {
        return nrc;
    }

    public void setNrc(Integer nrc) {
        this.nrc = nrc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
