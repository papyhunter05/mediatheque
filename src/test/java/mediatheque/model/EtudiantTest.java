package mediatheque.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class EtudiantTest {

    @Test
    void dureeMaxPretDoitEtreDeQuatorzeJours() {
        Etudiant etudiant = new Etudiant("Rakoto", "Sarah", "E001", LocalDate.of(2026, 1, 1));

        int resultat = etudiant.getDureeMaxPretJours();

        assertEquals(14, resultat);
    }

    @Test
    void nombreMaxDocumentsDoitEtreDeTrois() {
        Etudiant etudiant = new Etudiant("Rakoto", "Sarah", "E001", LocalDate.of(2026, 1, 1));

        int resultat = etudiant.getNombreMaxDocuments();

        assertEquals(3, resultat);
    }

    @Test
    void peutEmprunterSiSousLeQuota() {
        Etudiant etudiant = new Etudiant("Rakoto", "Sarah", "E001", LocalDate.of(2026, 1, 1));

        boolean resultat = etudiant.peutEmprunter(2);

        assertTrue(resultat);
    }

    @Test
    void nePeutPasEmprunterSiQuotaAtteint() {
        Etudiant etudiant = new Etudiant("Rakoto", "Sarah", "E001", LocalDate.of(2026, 1, 1));

        boolean resultat = etudiant.peutEmprunter(3);

        assertFalse(resultat);
    }

    @Test
    void nePeutPasEmprunterSiNombreDocumentsNegatif() {
        Etudiant etudiant = new Etudiant("Rakoto", "Sarah", "E001", LocalDate.of(2026, 1, 1));

        boolean resultat = etudiant.peutEmprunter(-1);

        assertFalse(resultat);
    }
}