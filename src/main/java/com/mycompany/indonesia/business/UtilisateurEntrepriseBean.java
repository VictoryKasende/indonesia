package com.mycompany.indonesia.business;
import com.mycompany.indonesia.entities.Utilisateur;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.mindrot.jbcrypt.BCrypt;


import java.util.List;

@Stateless
@LocalBean
public class UtilisateurEntrepriseBean {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void enregistrerUtilisateur(Utilisateur utilisateur) {
        // Hasher le mot de passe avant de l'enregistrer dans la base de données
        String motDePasseHash = BCrypt.hashpw(utilisateur.getMot_de_passe(), BCrypt.gensalt());
        utilisateur.setMot_de_passe(motDePasseHash);

        entityManager.persist(utilisateur);
    }

    public boolean trouveUtilisateurParNom(String nomUtilisateur) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(u) FROM Utilisateur u WHERE u.nom = :nomUtilisateur", Long.class);
        query.setParameter("nomUtilisateur", nomUtilisateur);

        Long count = query.getSingleResult();
        return count > 0;
    }

    public boolean trouverUtilisateurParEmail(String email) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(u) FROM Utilisateur u WHERE u.email = :email", Long.class);
        query.setParameter("email", email);

        Long count = query.getSingleResult();
        return count > 0;
    }

    public boolean verifierMotDePasse(Utilisateur utilisateur, String motDePasse) {
        String motDePasseHash = utilisateur.getMot_de_passe();
        return BCrypt.checkpw(motDePasse, motDePasseHash);
    }


    public boolean authentification(String nomUtilisateur, String motDePasse) {
        // Chercher l'utilisateur par son nom d'utilisateur
        TypedQuery<Utilisateur> query = entityManager.createQuery(
                "SELECT u FROM Utilisateur u WHERE u.nom = :nomUtilisateur", Utilisateur.class);
        query.setParameter("nomUtilisateur", nomUtilisateur);

        // Vérifier si un utilisateur avec ce nom d'utilisateur existe
        List<Utilisateur> resultats = query.getResultList();
        if (resultats.isEmpty()) {
            // Aucun utilisateur trouvé avec ce nom d'utilisateur
            return false;
        }

        // Récupérer le premier utilisateur trouvé (il ne devrait y en avoir qu'un avec ce nom d'utilisateur)
        Utilisateur utilisateur = resultats.get(0);

        // Vérifier si le mot de passe correspond
        return verifierMotDePasse(utilisateur, motDePasse);
    }
    public void modifierUtilisateur(Utilisateur utilisateur) {
        entityManager.merge(utilisateur);
    }

    public void supprimerUtilisateur(Long utilisateurId) {
        Utilisateur utilisateur = entityManager.find(Utilisateur.class, utilisateurId);
        if (utilisateur != null) {
            entityManager.remove(utilisateur);
        }
    }
    @Transactional
    public List<Utilisateur> listerTousLesUtilisateurs() {
        return entityManager.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class)
                .getResultList();
    }

    public List<Utilisateur> getTousLesUtilisateurs() {
        return entityManager.createQuery("SELECT u FROM Utilisateur u", Utilisateur.class)
                .getResultList();
    }
}
