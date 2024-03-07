/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.indonesia.beans;

/**
 *
 * @author Mk
 */
import java.io.Serializable;

import com.mycompany.indonesia.business.UtilisateurEntrepriseBean;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.validation.constraints.NotBlank;

@Named("welcomeBean")
@RequestScoped
public class WelcomeBean implements Serializable {

    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    private String password;
    @NotBlank(message = "Le nom d'utilisateur est obiligatoire")
    private String username;
    private String welcomeMessage;
    static boolean commence=false;

    @Inject
    private UtilisateurEntrepriseBean utilisateurEntrepriseBean;
    public WelcomeBean() {

    }

    public String authenticate() {
        boolean authentifie = utilisateurEntrepriseBean.authentification(username, password);
        if (authentifie) {
            welcomeMessage = "Authentification réussie. Bienvenue, " + username + "!";


            return this.commencer();
        } else {
            welcomeMessage = "Échec de l'authentification. Veuillez vérifier vos identifiants.";
            return "failure"; // Rediriger vers une page d'échec
        }
    }

    public String commencer() {

        commence=false;
        return "page_accueil";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }
}