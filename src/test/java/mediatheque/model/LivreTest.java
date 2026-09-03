package mediatheque.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LivreTest {

    @Test
    void pasDeRetardDoitDonnerPenaliteZero() {
        Livre livre = new Livre("Clean Code", "L001", "Robert C. Martin");

        double resultat = livre.calculerPenalite(0);

        assertEquals(0.0, resultat);
    }

    @Test
    void cinqJoursDeRetardDoitDonnerPenaliteDeDeuxCinquante() {
        Livre livre = new Livre("Clean Code", "L001", "Robert C. Martin");

        double resultat = livre.calculerPenalite(5);

        assertEquals(2.50, resultat);
    }

    @Test
    void grosRetardDoitEtrePlafonneAQuinzeEuros() {
        Livre livre = new Livre("Clean Code", "L001", "Robert C. Martin");

        double resultat = livre.calculerPenalite(100);

        assertEquals(15.0, resultat);
    }

    @Test
    void dureeMaxPretDoitEtreDeQuatorzeJours() {
        Livre livre = new Livre("Clean Code", "L001", "Robert C. Martin");

        int resultat = livre.getDureeMaxPretJours();

        assertEquals(14, resultat);
    }
}