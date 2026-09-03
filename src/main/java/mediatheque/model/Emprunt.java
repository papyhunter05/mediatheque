package mediatheque.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprunt {

    private Adherent adherent;
    private Document document;
    private LocalDate dateEmprunt;
    private LocalDate dateRetourEffective;
    private boolean rendu;

    public Emprunt(Adherent adherent, Document document, LocalDate dateEmprunt) {
        this.adherent = adherent;
        this.document = document;
        this.dateEmprunt = dateEmprunt;
        this.rendu = false;
    }

    public Adherent getAdherent() {
        return adherent;
    }

    public Document getDocument() {
        return document;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public boolean isRendu() {
        return rendu;
    }

    /**
     * Code smell (God Method) : cette méthode fait tout à la fois :
     * - déterminer la durée max de prêt (via des instanceof au lieu du polymorphisme)
     * - calculer le retard
     * - calculer la pénalité via le polymorphisme
     * - mettre à jour l'état de l'emprunt
     * - construire un message de retour formaté
     * À décomposer en plusieurs méthodes à responsabilité unique.
     */
    public String traiterRetourEmprunt(LocalDate dateRetour) {
        this.dateRetourEffective = dateRetour;
        this.rendu = true;

        int dureeMaxJours;
        if (document instanceof Livre) {
            dureeMaxJours = ((Livre) document).getDureeMaxPretJours();
        } else if (document instanceof DVD) {
            dureeMaxJours = ((DVD) document).getDureeMaxPretJours();
        } else if (document instanceof JeuDeSociete) {
            dureeMaxJours = ((JeuDeSociete) document).getDureeMaxPretJours();
        } else {
            dureeMaxJours = 14;
        }

        LocalDate dateRetourPrevue = dateEmprunt.plusDays(dureeMaxJours);
        long joursRetardLong = ChronoUnit.DAYS.between(dateRetourPrevue, dateRetour);
        int joursRetard = (int) joursRetardLong;

        double penalite = document.calculerPenalite(joursRetard);

        StringBuilder message = new StringBuilder();
        message.append("Retour de \"").append(document.getTitre()).append("\" par ")
                .append(adherent.getNomComplet()).append(" : ");
        if (joursRetard > 0) {
            message.append(joursRetard).append(" jour(s) de retard, penalite = ")
                    .append(penalite).append(" euros.");
        } else {
            message.append("rendu dans les temps.");
        }

        return message.toString();
    }
}
