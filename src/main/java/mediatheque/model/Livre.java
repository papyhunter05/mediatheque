package mediatheque.model;

public class Livre extends Document {

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

    // Code smell : formule de pénalité dupliquée dans DVD et JeuDeSociete
    public double calculerPenalite(int joursRetard) {
        if (joursRetard <= 0) {
            return 0.0;
        }
        double penalite = joursRetard * 0.50;
        if (penalite > 15.0) {
            penalite = 15.0;
        }
        return penalite;
    }
}
