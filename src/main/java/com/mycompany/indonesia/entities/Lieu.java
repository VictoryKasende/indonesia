package com.mycompany.indonesia.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.io.Serializable;

@Entity
public class Lieu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom_lieu;
    private String type_lieu;

    private String description;
    private double latitude;
    private double longitute;

    public Lieu() {

    }

    public Lieu(String nom, String type, String description, double latitude, double longitude) {
        this.nom_lieu = nom;
        this.type_lieu = type;
        this.description = description;
        this.latitude = latitude;
        this.longitute = longitude;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNom_lieu() {
        return nom_lieu;
    }

    public void setNom_lieu(String nom_lieu) {
        this.nom_lieu = nom_lieu;
    }

    public String getType_lieu() {
        return type_lieu;
    }

    public void setType_lieu(String type_lieu) {
        this.type_lieu = type_lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }
}
