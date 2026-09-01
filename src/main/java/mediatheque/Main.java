package mediatheque;

import java.time.LocalDate;
import java.util.Scanner;

import mediatheque.model.Adherent;
import mediatheque.model.DVD;
import mediatheque.model.Emprunt;
import mediatheque.model.Etudiant;
import mediatheque.model.Livre;
import mediatheque.model.Banner;


public class Main {

    public static void main(String[] args) {

        Banner banner = new Banner();
        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        System.out.println("Bienvenue sur Mediatek");
        banner.afficher();

        while (continuer) {
            System.out.println("""
                    
                    ================================
                         MENU PRINCIPAL
                    ================================
                    
                    1. Gérer les livres
                    2. Gérer les adhérents
                    3. Effectuer un emprunt
                    4. Retourner un livre
                    5. Voir les pénalités
                    0. Quitter
                    
                    --------------------------------
                    """);

            System.out.print("Votre choix : ");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("\n Gestion livre");
                    break;

                case 2:
                    System.out.println("\n Gestion adherents");
                    break;

                case 3:
                    System.out.println("\n Effectuer un emprunt");
                    break;

                case 4:
                    System.out.println("\n Retourner un emprunt");
                    break;

                case 5:
                    System.out.println("\n Les penalites");
                    break;

                case 0:
                    System.out.println("\n Merci d'avoir utiliser Mediatek");
                    break;

                default:
                    System.out.println("\n❌ Choix invalide !");
            }
        }


    }
}
