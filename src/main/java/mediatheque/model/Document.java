package mediatheque.model;

/**
 * Classe de base pour un document empruntable.
 * NOTE : le calcul de pénalité est volontairement dupliqué dans chaque
 * sous-classe (Livre, DVD, JeuDeSociete) au lieu d'être centralisé ici.
 */
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
}
