package mediatheque.model;

public class JeuDeSociete extends Document {

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

    // Code smell : formule de pénalité copiée-collée une troisième fois
    public double calculerPenalite(int joursRetard) {
        if (joursRetard <= 0) {
            return 0.0;
        }
        double penalite = joursRetard * 0.50;
        if (penalite > 20.0) {
            penalite = 20.0;
        }
        return penalite;
    }
}
