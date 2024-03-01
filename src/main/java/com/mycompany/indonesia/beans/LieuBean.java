package com.mycompany.indonesia.beans;

import java.io.IOException;
import java.io.Serializable;

import com.mycompany.indonesia.business.LieuEntrepriseBean;
import com.mycompany.indonesia.entities.Lieu;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Named("lieuBean")
@RequestScoped
public class LieuBean implements Serializable {

    private Long lieuIdToDelete;
    private Long lieuIdToModify;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(min = 5, message = "Le nom doit avoir au moins 5 caractères")
    private String nomLieu;

    @NotBlank(message = "Le type est obligatoire")
    private String typeLieu;
    private String description;

    private double latitude;

    private double longitude;
    static List<Lieu> listeLieux = new ArrayList<>();

    // Getters et Setters pour nomLieu
    public String getNomLieu() {
        return nomLieu;
    }

    public void setNomLieu(String nomLieu) {
        this.nomLieu = nomLieu;
    }

    // Getters et Setters pour typeLieu
    public String getTypeLieu() {
        return typeLieu;
    }

    public void setTypeLieu(String typeLieu) {
        this.typeLieu = typeLieu;
    }

    // Getters et Setters pour description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters et Setters pour latitude
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    // Getters et Setters pour longitude
    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public Long getLieuIdToDelete() {
        return lieuIdToDelete;
    }

    public void setLieuIdToDelete(Long lieuIdToDelete) {
        this.lieuIdToDelete = lieuIdToDelete;
    }

    public Long getLieuIdToModify() {
        return lieuIdToModify;
    }

    public void setLieuIdToModify(Long lieuIdToModify) {
        this.lieuIdToModify = lieuIdToModify;
    }

    // Getters et Setters pour listeLieux
    public List<Lieu> getListeLieux() {
        return lieuEntrepriseBean.listerTousLesLieux();
    }


    @Inject
    private LieuEntrepriseBean lieuEntrepriseBean;
    public void ajouterLieu() {
        Lieu lieu = new Lieu(nomLieu, typeLieu, description, latitude, longitude);
        lieuEntrepriseBean.ajouterLieu(lieu);
    }

    public void supprimerLieu() {
        lieuEntrepriseBean.supprimerLieu(lieuIdToDelete);
    }

    public void modifierLieu() {
        Lieu lieu = new Lieu(nomLieu, typeLieu, description, latitude, longitude);
        lieu.setId(lieuIdToModify);
        lieuEntrepriseBean.modifierLieu(lieu);

        // Redirection vers la page "lieu.xhtml"
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        try {
            externalContext.redirect("lieu.xhtml");
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception si la redirection échoue
        }
    }

    // Méthode pour récupérer les informations du lieu à modifier
    public void edit_lieu() {
        // Récupérer l'identifiant du lieu à partir de la requête HTTP
        String lieuIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("lieuId");

        // Vérifier si l'identifiant du lieu est présent dans l'URL
        if (lieuIdParam != null) {
            // Convertir l'identifiant du lieu en Long
            Long lieuId = Long.valueOf(lieuIdParam);

            // Rechercher le lieu correspondant dans la base de données ou la liste des lieux
            Lieu lieuAModifier = lieuEntrepriseBean.trouverLieuParId(lieuId);

            // Pré-remplir les champs du formulaire avec les informations du lieu à modifier
            if (lieuAModifier != null) {
                nomLieu = lieuAModifier.getNom_lieu();
                typeLieu = lieuAModifier.getType_lieu();
                description = lieuAModifier.getDescription();
                latitude = lieuAModifier.getLatitude();
                longitude = lieuAModifier.getLongitute();
            }
        }
    }

}
