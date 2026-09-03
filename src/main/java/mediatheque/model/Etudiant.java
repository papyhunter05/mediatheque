package mediatheque.model;

import java.time.LocalDate;

public class Etudiant extends Adherent {

    private static final int DUREE_MAX_PRET_JOURS = 14;
    private static final int NOMBRE_MAX_DOCUMENTS = 3;

    public Etudiant(String nom, String prenom, String numeroAdherent, LocalDate dateInscription) {
        super(nom, prenom, numeroAdherent, dateInscription);
    }

    @Override
    public int getDureeMaxPretJours() {
        return DUREE_MAX_PRET_JOURS;
    }

    @Override
    public int getNombreMaxDocuments() {
        return NOMBRE_MAX_DOCUMENTS;
    }
}