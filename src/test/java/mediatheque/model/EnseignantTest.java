package mediatheque.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EnseignantTest {

    @Test
    void dureeMaxPretDoitEtreDeVingtEtUnJours() {
        Enseignant enseignant = new Enseignant("Rabe", "Jean", "P001", LocalDate.of(2025, 9, 1));

        int resultat = enseignant.getDureeMaxPretJours();

        assertEquals(21, resultat);
    }

    @Test
    void nombreMaxDocumentsDoitEtreDeCinq() {
        Enseignant enseignant = new Enseignant("Rabe", "Jean", "P001", LocalDate.of(2025, 9, 1));

        int resultat = enseignant.getNombreMaxDocuments();

        assertEquals(5, resultat);
    }

    @Test
    void peutEmprunterSiSousLeQuota() {
        Enseignant enseignant = new Enseignant("Rabe", "Jean", "P001", LocalDate.of(2025, 9, 1));

        boolean resultat = enseignant.peutEmprunter(4);

        assertTrue(resultat);
    }

    @Test
    void nePeutPasEmprunterSiQuotaAtteint() {
        Enseignant enseignant = new Enseignant("Rabe", "Jean", "P001", LocalDate.of(2025, 9, 1));

        boolean resultat = enseignant.peutEmprunter(5);

        assertFalse(resultat);
    }
}