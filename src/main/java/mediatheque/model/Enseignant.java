package mediatheque.model;

import java.time.LocalDate;

public class Enseignant extends Adherent {

    private static final int DUREE_MAX_PRET_JOURS = 21;
    private static final int NOMBRE_MAX_DOCUMENTS = 5;

    public Enseignant(String nom, String prenom, String numeroAdherent, LocalDate dateInscription) {
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