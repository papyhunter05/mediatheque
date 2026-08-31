package mediatheque.model;

import java.time.LocalDate;

/**
 * Classe de base pour un adhérent de la médiathèque.
 * NOTE : la logique de durée de prêt et de quota de documents est
 * volontairement dupliquée dans les sous-classes (voir Etudiant / Enseignant).
 */
public class Adherent {

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
}
