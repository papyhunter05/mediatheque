package mediatheque.model;

public class DVD extends Document {

    private int dureeMinutes;

    public DVD(String titre, String reference, int dureeMinutes) {
        super(titre, reference);
        this.dureeMinutes = dureeMinutes;
    }

    public int getDureeMinutes() {
        return dureeMinutes;
    }

    public int getDureeMaxPretJours() {
        return 7;
    }

    // Code smell : formule de pénalité copiée-collée depuis Livre (avec plafond différent)
    public double calculerPenalite(int joursRetard) {
        if (joursRetard <= 0) {
            return 0.0;
        }
        double penalite = joursRetard * 0.50;
        if (penalite > 10.0) {
            penalite = 10.0;
        }
        return penalite;
    }
}
