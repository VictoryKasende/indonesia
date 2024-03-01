package com.mycompany.indonesia.beans;

import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Named("creerCompteBean")
@RequestScoped
public class CreerCompteBean {

    @NotBlank(message = "Le nom d'utilisateur est requis")
    private String nomUtilisateur;

    @NotBlank(message = "L'email est requis")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le mot de passe est requis")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String motDePasse;

    @NotBlank(message = "La confirmation du mot de passe est requise")
    private String confirmationMotDePasse;

    // Getters et setters

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getConfirmationMotDePasse() {
        return confirmationMotDePasse;
    }

    public void setConfirmationMotDePasse(String confirmationMotDePasse) {
        this.confirmationMotDePasse = confirmationMotDePasse;
    }

    // Méthode d'action pour créer le compte
    public String creerCompte() {
        // Logique de création de compte ici
        // Vous pouvez accéder aux données saisies via les champs nomUtilisateur, email, motDePasse, etc.
        // Enregistrez les données dans la base de données ou effectuez toute autre opération nécessaire
        return "success"; // Redirige vers une page de succès
    }
}
