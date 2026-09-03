package mediatheque.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EmpruntTest {

    @Test
    void retourDansLesTempsNeDoitPasMentionnerDeRetard() {
        Adherent etudiant = new Etudiant("Rakoto", "Sarah", "E001", LocalDate.of(2026, 1, 1));
        Livre livre = new Livre("Clean Code", "L001", "Robert C. Martin");
        Emprunt emprunt = new Emprunt(etudiant, livre, LocalDate.of(2026, 8, 1));

        String resultat = emprunt.traiterRetourEmprunt(LocalDate.of(2026, 8, 10));

        assertTrue(resultat.contains("rendu dans les temps"));
    }

    @Test
    void retourEnRetardDoitMentionnerLaPenalite() {
        Adherent enseignant = new Enseignant("Rabe", "Jean", "P001", LocalDate.of(2025, 9, 1));
        DVD dvd = new DVD("Interstellar", "D001", 169);
        Emprunt emprunt = new Emprunt(enseignant, dvd, LocalDate.of(2026, 8, 1));

        // duree max DVD = 7 jours, donc retour prevu le 8 aout ; rendu le 8 aout + 5 = 13 aout -> 5 jours de retard
        String resultat = emprunt.traiterRetourEmprunt(LocalDate.of(2026, 8, 13));

        assertFalse(resultat.contains("rendu dans les temps"));
        assertTrue(resultat.contains("5 jour"));
        assertTrue(resultat.contains("penalite"));
    }

    @Test
    void isRenduDoitEtreFauxAvantRetour() {
        Adherent etudiant = new Etudiant("Rakoto", "Sarah", "E001", LocalDate.of(2026, 1, 1));
        Livre livre = new Livre("Clean Code", "L001", "Robert C. Martin");
        Emprunt emprunt = new Emprunt(etudiant, livre, LocalDate.of(2026, 8, 1));

        assertFalse(emprunt.isRendu());
    }

    @Test
    void isRenduDoitEtreVraiApresTraitementDuRetour() {
        Adherent etudiant = new Etudiant("Rakoto", "Sarah", "E001", LocalDate.of(2026, 1, 1));
        Livre livre = new Livre("Clean Code", "L001", "Robert C. Martin");
        Emprunt emprunt = new Emprunt(etudiant, livre, LocalDate.of(2026, 8, 1));

        emprunt.traiterRetourEmprunt(LocalDate.of(2026, 8, 10));

        assertTrue(emprunt.isRendu());
    }

    @Test
    void messageDoitContenirLeTitreEtLeNomDeLAdherent() {
        Adherent etudiant = new Etudiant("Rakoto", "Sarah", "E001", LocalDate.of(2026, 1, 1));
        Livre livre = new Livre("Clean Code", "L001", "Robert C. Martin");
        Emprunt emprunt = new Emprunt(etudiant, livre, LocalDate.of(2026, 8, 1));

        String resultat = emprunt.traiterRetourEmprunt(LocalDate.of(2026, 8, 10));

        assertTrue(resultat.contains("Clean Code"));
        assertTrue(resultat.contains("Sarah Rakoto"));
    }
}