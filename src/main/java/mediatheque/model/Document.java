package mediatheque.model;


public class Document {

    private String titre;
    private String reference;

    public Document(String titre, String reference) {
        this.titre = titre;
        this.reference = reference;
    }

    public String getTitre() {
        return titre;
    }

    public String getReference() {
        return reference;
    }

    /**
     * Retourne la durée maximale d'un prêt pour ce type de document.
     */
    public int getDureeMaxPretJours() {
        return 14;
    }

    public double calculerPenalite(int joursRetard) {
        if (joursRetard <= 0) {
            return 0.0;
        }
        return Math.min(joursRetard * 0.50, getPlafondPenalite());
    }

    protected double getPlafondPenalite() {
        return 0.0;
    }
}
