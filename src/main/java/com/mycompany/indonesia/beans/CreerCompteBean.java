package com.mycompany.indonesia.beans;

import com.mycompany.indonesia.business.UtilisateurEntrepriseBean;
import com.mycompany.indonesia.entities.Utilisateur;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Named("creerCompteBean")
@RequestScoped
public class CreerCompteBean {

    @NotBlank(message = "Le nom d'utilisateur est requis")
    @Size(min = 3, max = 20, message = "Le nom d'utilisateur doit avoir entre 3 et 20 caractères")
    private String nomUtilisateur;

    @NotBlank(message = "L'adresse e-mail est requise")
    @Email(message = "L'email doit être valide")
    private String email;

    @NotBlank(message = "Le mot de passe est requis")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,20}$",
            message = "Le mot de passe doit contenir au moins une lettre majuscule, " +
                    "un chiffre et un caractère spécial")
    @Size(min = 6, max = 20, message = "Le mot de passe doit avoir entre 6 et 20 caractères")
    private String motDePasse;

    @NotBlank(message="La confirmation du mot de passe est requise")
    private String confirmationMotDePasse;

    @Inject
    private UtilisateurEntrepriseBean utilisateurEntrepriseBean;

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
        if(!motDePasse.equals(confirmationMotDePasse)){
            this.confirmationMotDePasse="Les mots de passe ne correspondent pas";
            return "erreur";
        }
        // Créer une instance de Utilisateur et la remplir avec les données du formulaire
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom(nomUtilisateur);
        utilisateur.setEmail(email);
        utilisateur.setMot_de_passe(motDePasse);

        utilisateurEntrepriseBean.enregistrerUtilisateur(utilisateur);

        return "Success";
    }
}
