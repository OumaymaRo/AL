import SERVICE.GestionEmployeeArray;
import IHM.IHMemployeeConsole;
import Interfaces.InterfaceEmployee;
import IHM.IHMFicheSalaireConsole;
import Interfaces.InterfaceIHMficheSalaire;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Créer des instances des gestionnaires
        InterfaceEmployee gestionEmployee = new GestionEmployeeArray();
        InterfaceIHMficheSalaire gestionFicheSalaire = new IHMFicheSalaireConsole();

        // Créer des consoles pour chaque gestionnaire
        IHMemployeeConsole employeeConsole = new IHMemployeeConsole();


        IHMFicheSalaireConsole ficheSalaireConsole = new IHMFicheSalaireConsole();

        // Scanner pour capturer les choix de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        boolean quitter = false;
        while (!quitter) {
            System.out.println("Menu principal : ");
            System.out.println("1. Gestion des employés");
            System.out.println("2. Gestion des fiches de salaire");
            System.out.println("3. Quitter");
            System.out.print("Choisir une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne restant

            switch (choix) {
                case 1:
                    // Lancer le menu de gestion des employés
                    employeeConsole.demarrerConsole();
                    break;
                case 2:
                    // Lancer le menu de gestion des fiches de salaire
                    ficheSalaireConsole.afficherMenu();
                    break;
                case 3:
                    quitter = true;
                    System.out.println("Merci d'avoir utilisé notre programme !");
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        }
        scanner.close();
    }
}
