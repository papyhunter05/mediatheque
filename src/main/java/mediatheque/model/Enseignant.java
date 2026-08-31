package mediatheque.model;

import java.time.LocalDate;

public class Enseignant extends Adherent {

    public Enseignant(String nom, String prenom, String numeroAdherent, LocalDate dateInscription) {
        super(nom, prenom, numeroAdherent, dateInscription);
    }

    // Code smell : duplication quasi identique avec Etudiant.getDureeMaxPretJours()
    public int getDureeMaxPretJours() {
        int duree;
        if (this.getDateInscription() != null) {
            duree = 21;
        } else {
            duree = 21;
        }
        return duree;
    }

    // Code smell : duplication quasi identique avec Etudiant.getNombreMaxDocuments()
    public int getNombreMaxDocuments() {
        int max;
        if (this.getDateInscription() != null) {
            max = 5;
        } else {
            max = 5;
        }
        return max;
    }

    // Code smell : logique de validation dupliquée mot pour mot avec Etudiant
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
