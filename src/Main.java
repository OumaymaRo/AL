import Interfaces.InterfaceIHMemployee;
import Interfaces.InterfaceIHMficheSalaire;
import IHM.IHMemployeeConsole;
import IHM.IHMemployeeSwing;
import IHM.IHMFicheSalaireConsole;
import IHM.IHMFicheSalaireSwing;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean quitter = false;

        while (!quitter) {
            System.out.println("Choisissez un module :");
            System.out.println("1. Gestion des employés");
            System.out.println("2. Gestion des fiches de salaire");
            System.out.println("3. Quitter");
            int choixModule = scanner.nextInt();

            if (choixModule == 3) {
                quitter = true;
                break;
            }

            System.out.println("Choisissez l'interface :");
            System.out.println("1. Console");
            System.out.println("2. Swing");
            int choixInterface = scanner.nextInt();

            switch (choixModule) {
                case 1: // Gestion des employés
                    InterfaceIHMemployee interfaceIHMemployee;
                    if (choixInterface == 1) {
                        interfaceIHMemployee = new IHMemployeeConsole();
                        ((IHMemployeeConsole) interfaceIHMemployee).demarrerConsole();
                    } else {
                        interfaceIHMemployee = new IHMemployeeSwing();
                        // Lancer l'interface graphique Swing pour les employés
                    }
                    break;

                case 2: // Gestion des fiches de salaire
                    InterfaceIHMficheSalaire interfaceIHMficheSalaire;
                    if (choixInterface == 1) {
                        interfaceIHMficheSalaire = new IHMFicheSalaireConsole();
                        ((IHMFicheSalaireConsole) interfaceIHMficheSalaire).afficherMenu();
                    } else {
                        interfaceIHMficheSalaire = new IHMFicheSalaireSwing();
                        // Lancer l'interface graphique Swing pour les fiches de salaire
                    }
                    break;

                default:
                    System.out.println("Choix invalide.");
            }
        }

        scanner.close();
    }
}
