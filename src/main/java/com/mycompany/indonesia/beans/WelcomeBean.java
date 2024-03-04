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
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;

@Named("welcomeBean")
@RequestScoped
public class WelcomeBean implements Serializable {

    private String name;
    private String pseudo;
    private String password;
    private String welcomeMessage;
    static boolean commence=false;

    public WelcomeBean() {

    }


    public  boolean getCommence(){
        return commence;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }
    public void sayWelcome() {

        if (!commence) {
            welcomeMessage = "Selamat datang, " + name + "!";
            commence = true;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String commencer() {
        commence=false;
        return "page_accueil";
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassword() {
        return password;
    }

    public String seConnecter() {
        commence=false;
        return "page_accueil";
    }

    public String getRegistrationPageUrl() {
        return "register.xhtml";
    }
}