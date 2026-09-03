package mediatheque.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DVDTest {

    @Test
    void pasDeRetardDoitDonnerPenaliteZero() {
        DVD dvd = new DVD("Interstellar", "D001", 169);

        double resultat = dvd.calculerPenalite(0);

        assertEquals(0.0, resultat);
    }

    @Test
    void troisJoursDeRetardDoitDonnerPenaliteDeUnCinquante() {
        DVD dvd = new DVD("Interstellar", "D001", 169);

        double resultat = dvd.calculerPenalite(3);

        assertEquals(1.50, resultat);
    }

    @Test
    void grosRetardDoitEtrePlafonneADixEuros() {
        DVD dvd = new DVD("Interstellar", "D001", 169);

        double resultat = dvd.calculerPenalite(100);

        assertEquals(10.0, resultat);
    }

    @Test
    void dureeMaxPretDoitEtreDeSeptJours() {
        DVD dvd = new DVD("Interstellar", "D001", 169);

        int resultat = dvd.getDureeMaxPretJours();

        assertEquals(7, resultat);
    }

    @Test
    void getDureeMinutesDoitRetournerLaValeurDonnee() {
        DVD dvd = new DVD("Interstellar", "D001", 169);

        int resultat = dvd.getDureeMinutes();

        assertEquals(169, resultat);
    }
}