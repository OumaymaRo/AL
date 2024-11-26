package IHM;

import Interfaces.InterfaceIHMficheSalaire;
import SERVICE.GestionFicheSalaireArray;
import MODELS.FicheSalaire;
import MODELS.Employee;

import java.util.ArrayList;
import java.util.Scanner;

public class IHMFicheSalaireConsole implements InterfaceIHMficheSalaire {
    private GestionFicheSalaireArray gestionFicheSalaire = new GestionFicheSalaireArray(); // Instance de gestion des fiches de salaire
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void afficherMenu() {
        boolean quitter = false;

        while (!quitter) {
            System.out.println("Menu de gestion des fiches de salaire : ");
            System.out.println("1. Ajouter une fiche de salaire");
            System.out.println("2. Modifier une fiche de salaire");
            System.out.println("3. Supprimer une fiche de salaire");
            System.out.println("4. Afficher le salaire d'un employé");
            System.out.println("5. Afficher tous les salaires");
            System.out.println("6. Quitter");

            System.out.print("Choisir une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne restant

            switch (choix) {
                case 1:
                    ajouterFicheSalaire();
                    break;
                case 2:
                    modifierFicheSalaire();
                    break;
                case 3:
                    supprimerFicheSalaire();
                    break;
                case 4:
                    System.out.print("Entrer le matricule de l'employé : ");
                    String matricule = scanner.nextLine();
                    afficherSalaire(matricule);
                    break;
                case 5:
                    afficherTousSalaires();
                    break;
                case 6:
                    quitter = true;
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        }
    }

    @Override
    public void ajouterFicheSalaire() {
        System.out.print("Entrer le matricule de l'employé : ");
        String matricule = scanner.nextLine();
        System.out.print("Entrer le montant brut : ");
        double montantBrut = scanner.nextDouble();
        System.out.print("Entrer le montant net : ");
        double montantNet = scanner.nextDouble();

        boolean success = gestionFicheSalaire.ajouterSalaire(matricule, montantBrut, montantNet);
        if (success) {
            System.out.println("Fiche de salaire ajoutée avec succès.");
        } else {
            System.out.println("Erreur lors de l'ajout de la fiche de salaire.");
        }
    }

    @Override
    public void modifierFicheSalaire() {
        System.out.print("Entrer le matricule de l'employé : ");
        String matricule = scanner.nextLine();
        System.out.print("Entrer le nouveau montant brut : ");
        double montantBrut = scanner.nextDouble();
        System.out.print("Entrer le nouveau montant net : ");
        double montantNet = scanner.nextDouble();

        boolean success = gestionFicheSalaire.modifierSalaire(matricule, montantBrut, montantNet);
        if (success) {
            System.out.println("Fiche de salaire modifiée avec succès.");
        } else {
            System.out.println("Erreur lors de la modification de la fiche de salaire.");
        }
    }

    @Override
    public void supprimerFicheSalaire() {
        System.out.print("Entrer le matricule de l'employé à supprimer : ");
        String matricule = scanner.nextLine();

        boolean success = gestionFicheSalaire.supprimerSalaire(matricule);
        if (success) {
            System.out.println("Fiche de salaire supprimée avec succès.");
        } else {
            System.out.println("Aucune fiche de salaire trouvée pour le matricule : " + matricule);
        }
    }

    @Override
    public void afficherSalaire(String matricule) {
        double salaire = gestionFicheSalaire.rechercherSalaire(matricule);
        if (salaire > 0) {
            System.out.println("Le salaire net de l'employé " + matricule + " est : " + salaire);
        } else {
            System.out.println("Aucune fiche de salaire trouvée pour le matricule : " + matricule);
        }
    }

    @Override
    public void afficherTousSalaires() {
        // Récupérer la liste des fiches de salaire
        ArrayList<FicheSalaire> fiches = gestionFicheSalaire.getFichesSalaires();

        // Afficher chaque fiche de salaire
        if (fiches.isEmpty()) {
            System.out.println("Aucune fiche de salaire disponible.");
        } else {
            for (FicheSalaire fiche : fiches) {
                System.out.println(fiche); // Vous pouvez définir un `toString` personnalisé dans FicheSalaire
            }
        }
    }
}
