package mediatheque.model;

public class Livre extends Document {

    private static final double PLAFOND_PENALITE = 15.0;
    private String auteur;

    public Livre(String titre, String reference, String auteur) {
        super(titre, reference);
        this.auteur = auteur;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getDureeMaxPretJours() {
        return 14;
    }

    @Override
    protected double getPlafondPenalite() {
        return PLAFOND_PENALITE;
    }
}
