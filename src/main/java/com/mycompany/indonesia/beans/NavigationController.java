package com.mycompany.indonesia.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author user
 */
@Named(value = "navigationController")
@RequestScoped
public class NavigationController implements Serializable{
    private boolean visited=false;
    public void guide(){
        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect("pages/guide.xhtml"); // Redirection vers la nouvelle page
        } catch (IOException e) {
            e.printStackTrace(); // Gérer l'exception
        }
    }

    public boolean isVisited() {
        return visited;
    }

    public void visiter(){
        visited=true;
    }

}