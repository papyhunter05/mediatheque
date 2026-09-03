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

    public String traiterRetourEmprunt(LocalDate dateRetour) {
        enregistrerRetour(dateRetour);
        int joursRetard = calculerJoursRetard(dateRetour);
        double penalite = document.calculerPenalite(joursRetard);

        return construireMessageRetour(joursRetard, penalite);
    }

    private void enregistrerRetour(LocalDate dateRetour) {
        this.dateRetourEffective = dateRetour;
        this.rendu = true;
    }

    private int calculerJoursRetard(LocalDate dateRetour) {
        return (int) ChronoUnit.DAYS.between(calculerDateRetourPrevue(), dateRetour);
    }

    private LocalDate calculerDateRetourPrevue() {
        return dateEmprunt.plusDays(document.getDureeMaxPretJours());
    }

    private String construireMessageRetour(int joursRetard, double penalite) {
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
