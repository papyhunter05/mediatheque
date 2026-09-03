package mediatheque.model;

public class JeuDeSociete extends Document {

    private static final double PLAFOND_PENALITE = 20.0;
    private int nombreJoueursMax;

    public JeuDeSociete(String titre, String reference, int nombreJoueursMax) {
        super(titre, reference);
        this.nombreJoueursMax = nombreJoueursMax;
    }

    public int getNombreJoueursMax() {
        return nombreJoueursMax;
    }

    public int getDureeMaxPretJours() {
        return 21;
    }

    @Override
    protected double getPlafondPenalite() {
        return PLAFOND_PENALITE;
    }
}
