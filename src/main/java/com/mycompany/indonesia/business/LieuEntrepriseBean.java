package com.mycompany.indonesia.business;

import com.mycompany.indonesia.entities.Lieu;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@Stateless
@LocalBean
public class LieuEntrepriseBean {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void ajouterLieuEntreprise(String nom, String type, String description, double latitude, double longitude){
        Lieu lieu = new Lieu(nom, type, description, latitude, longitude);
        ajouterLieu(lieu);
    }

    @Transactional
    public void ajouterLieu(Lieu lieu){
        em.persist(lieu);
    }

    public List<Lieu> listerTousLesLieux(){
        String stringQuery = "SELECT L FROM Lieu L";
        Query q = em.createQuery(stringQuery, Lieu.class);
        return (List<Lieu>) q.getResultList();
    }


    @Transactional
    public void supprimerLieu(Long lieuId){
        Lieu lieuASupprimer = em.find(Lieu.class, lieuId);
        if (lieuASupprimer != null) {
            em.remove(lieuASupprimer);
        }
    }

    @Transactional
    public void modifierLieu(Lieu lieu) {
        Lieu lieuAModifier = em.find(Lieu.class, lieu.getId());
        if (lieuAModifier != null) {
            lieuAModifier.setNom_lieu(lieu.getNom_lieu());
            lieuAModifier.setType_lieu(lieu.getType_lieu());
            lieuAModifier.setDescription(lieu.getDescription());
            lieuAModifier.setLatitude(lieu.getLatitude());
            lieuAModifier.setLongitute(lieu.getLongitute());
            em.merge(lieuAModifier);
        }
    }
    public Lieu trouverLieuParId(Long lieuId) {
        return em.find(Lieu.class, lieuId);
    }
}
