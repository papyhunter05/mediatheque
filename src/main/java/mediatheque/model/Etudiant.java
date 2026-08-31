package mediatheque.model;

import java.time.LocalDate;

public class Etudiant extends Adherent {

    public Etudiant(String nom, String prenom, String numeroAdherent, LocalDate dateInscription) {
        super(nom, prenom, numeroAdherent, dateInscription);
    }

    // Code smell : duplication quasi identique avec Enseignant.getDureeMaxPretJours()
    public int getDureeMaxPretJours() {
        int duree;
        if (this.getDateInscription() != null) {
            duree = 14;
        } else {
            duree = 14;
        }
        return duree;
    }

    // Code smell : duplication quasi identique avec Enseignant.getNombreMaxDocuments()
    public int getNombreMaxDocuments() {
        int max;
        if (this.getDateInscription() != null) {
            max = 3;
        } else {
            max = 3;
        }
        return max;
    }

    // Code smell : logique de validation dupliquée mot pour mot avec Enseignant
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
