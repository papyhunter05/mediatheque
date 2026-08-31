package mediatheque;

import java.time.LocalDate;

import mediatheque.model.Adherent;
import mediatheque.model.DVD;
import mediatheque.model.Emprunt;
import mediatheque.model.Etudiant;
import mediatheque.model.Livre;

public class Main {

    public static void main(String[] args) {
        Adherent etudiant = new Etudiant("Rakoto", "Fandresena", "E001", LocalDate.of(2025, 9, 1));

        Livre livre = new Livre("Clean Code", "L001", "Robert C. Martin");
        DVD dvd = new DVD("Le Fabuleux Destin d'Amelie Poulain", "D001", 122);

        Emprunt empruntLivre = new Emprunt(etudiant, livre, LocalDate.of(2026, 8, 1));
        System.out.println(empruntLivre.traiterRetourEmprunt(LocalDate.of(2026, 8, 20)));

        Emprunt empruntDvd = new Emprunt(etudiant, dvd, LocalDate.of(2026, 8, 1));
        System.out.println(empruntDvd.traiterRetourEmprunt(LocalDate.of(2026, 8, 5)));
    }
}
