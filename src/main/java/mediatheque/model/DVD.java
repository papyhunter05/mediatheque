package mediatheque.model;

public class DVD extends Document {

    private static final double PLAFOND_PENALITE = 10.0;
    private int dureeMinutes;

    public DVD(String titre, String reference, int dureeMinutes) {
        super(titre, reference);
        this.dureeMinutes = dureeMinutes;
    }

    public int getDureeMinutes() {
        return dureeMinutes;
    }

    @Override
    public int getDureeMaxPretJours() {
        return 7;
    }

    @Override
    protected double getPlafondPenalite() {
        return PLAFOND_PENALITE;
    }
}
