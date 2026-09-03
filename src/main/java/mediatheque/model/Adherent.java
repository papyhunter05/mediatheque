package mediatheque.model;

import java.time.LocalDate;

/**
 * Classe de base pour un adhérent de la médiathèque.
 * Chaque sous-catégorie d'adhérent définit sa propre durée de prêt
 * et son propre quota de documents ; la logique de validation
 * (peutEmprunter) est commune et centralisée ici.
 */
public abstract class Adherent {

    private String nom;
    private String prenom;
    private String numeroAdherent;
    private LocalDate dateInscription;

    public Adherent(String nom, String prenom, String numeroAdherent, LocalDate dateInscription) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroAdherent = numeroAdherent;
        this.dateInscription = dateInscription;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumeroAdherent() {
        return numeroAdherent;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    /**
     * Durée maximale de prêt en jours, propre à chaque type d'adhérent.
     */
    public abstract int getDureeMaxPretJours();

    /**
     * Nombre maximal de documents empruntables simultanément,
     * propre à chaque type d'adhérent.
     */
    public abstract int getNombreMaxDocuments();

    /**
     * Logique de validation commune à tous les adhérents.
     */
    public boolean peutEmprunter(int documentsActuellementEmpruntes) {
        if (documentsActuellementEmpruntes < 0) {
            System.out.println("Erreur : nombre de documents invalide pour " + this.getNomComplet());
            return false;
        }
        if (documentsActuellementEmpruntes >= this.getNombreMaxDocuments()) {
            System.out.println(this.getNomComplet() + " a atteint son quota de documents empruntes.");
            return false;
        }
        return true;
    }
}