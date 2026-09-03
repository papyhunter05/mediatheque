package mediatheque.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JeuDeSocieteTest {

    @Test
    void pasDeRetardDoitDonnerPenaliteZero() {
        JeuDeSociete jeu = new JeuDeSociete("Catane", "J001", 4);

        double resultat = jeu.calculerPenalite(0);

        assertEquals(0.0, resultat);
    }

    @Test
    void dixJoursDeRetardDoitDonnerPenaliteDeCinq() {
        JeuDeSociete jeu = new JeuDeSociete("Catane", "J001", 4);

        double resultat = jeu.calculerPenalite(10);

        assertEquals(5.0, resultat);
    }

    @Test
    void grosRetardDoitEtrePlafonneAVingtEuros() {
        JeuDeSociete jeu = new JeuDeSociete("Catane", "J001", 4);

        double resultat = jeu.calculerPenalite(100);

        assertEquals(20.0, resultat);
    }

    @Test
    void dureeMaxPretDoitEtreDeVingtEtUnJours() {
        JeuDeSociete jeu = new JeuDeSociete("Catane", "J001", 4);

        int resultat = jeu.getDureeMaxPretJours();

        assertEquals(21, resultat);
    }

    @Test
    void getNombreJoueursMaxDoitRetournerLaValeurDonnee() {
        JeuDeSociete jeu = new JeuDeSociete("Catane", "J001", 4);

        int resultat = jeu.getNombreJoueursMax();

        assertEquals(4, resultat);
    }
}