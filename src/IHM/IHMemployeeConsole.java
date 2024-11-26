package IHM;

import Interfaces.InterfaceEmployee;
import Interfaces.InterfaceIHMemployee;
import MODELS.Employee;
import SERVICE.GestionEmployeeArray;

import java.util.Scanner;

public class IHMemployeeConsole implements InterfaceIHMemployee {
    private InterfaceEmployee gestionEmployee = new GestionEmployeeArray();  // Correction du type

    public IHMemployeeConsole() {
    }

    public void demarrerConsole() {
        Scanner scanner = new Scanner(System.in);
        boolean quitter = false;

        while (!quitter) {
            System.out.println("Menu : ");
            System.out.println("1. Ajouter un employé");
            System.out.println("2. Afficher un employé");
            System.out.println("3. Modifier un employé");
            System.out.println("4. Supprimer un employé");
            System.out.println("5. Afficher tous les employés");
            System.out.println("6. Quitter");

            System.out.print("Choisir une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le saut de ligne restant

            switch (choix) {
                case 1:
                    // Demander les informations de l'employé
                    System.out.print("Entrer le matricule : ");
                    String matricule = scanner.nextLine();
                    System.out.print("Entrer le nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Entrer le prénom : ");
                    String prenom = scanner.nextLine();
                    System.out.print("Entrer l'adresse : ");
                    String adresse = scanner.nextLine();

                    // Créer un objet Employee
                    Employee newEmployee = new Employee(matricule, nom, prenom, adresse);

                    // Ajouter l'employé via la méthode d'InterfaceEmployee
                    gestionEmployee.AjouterEmployee(newEmployee);
                    break;
                case 2:
                    System.out.print("Entrer le matricule de l'employé à afficher : ");
                    matricule = scanner.nextLine();
                    gestionEmployee.RechercherEmployee(matricule);
                    break;
                case 3:
                    System.out.print("Entrer le matricule de l'employé à modifier : ");
                    matricule = scanner.nextLine();
                    System.out.print("Entrer le nouveau nom : ");
                    nom = scanner.nextLine();
                    System.out.print("Entrer le nouveau prénom : ");
                    prenom = scanner.nextLine();
                    System.out.print("Entrer l'adresse : ");
                    String adress = scanner.nextLine();
                    Employee updatedEmployee = new Employee(matricule, nom, prenom, adress); // Ajoutez la logique nécessaire
                    gestionEmployee.ModifierEmployee(matricule, updatedEmployee);
                    break;
                case 4:
                    System.out.print("Entrer le matricule de l'employé à supprimer : ");
                    matricule = scanner.nextLine();
                    gestionEmployee.SupprimerEmployee(matricule);
                    break;
                case 5:
                    gestionEmployee.AfficherListeEmployee();
                    break;
                case 6:
                    quitter = true;
                    break;
                default:
                    System.out.println("Option invalide.");
            }
        }
        scanner.close();
    }

    @Override
    public void ajouterEmployee(String matricule, String nom, String prenom, String adresse) {
        // Crée un objet Employee avec les informations passées en paramètres
        Employee emp = new Employee(matricule, nom, prenom, adresse);
        // Appelle la méthode d'ajout
        gestionEmployee.AjouterEmployee(emp);
    }

    @Override
    public void afficherEmployee(String matricule) {
        gestionEmployee.RechercherEmployee(matricule);
    }

    @Override
    public void modifierEmployee(String matricule, String nouveauNom, String nouveauPrenom) {
        // Crée un employé avec les nouvelles informations et modifie
        Employee empToUpdate = new Employee(matricule, nouveauNom, nouveauPrenom, "");
        gestionEmployee.ModifierEmployee(matricule, empToUpdate);
    }

    @Override
    public void supprimerEmployee(String matricule) {
        gestionEmployee.SupprimerEmployee(matricule);
    }

    @Override
    public void afficherTousEmployees() {
        System.out.println("Affichage de tous les employés :");
        gestionEmployee.AfficherListeEmployee();  // Appelle la méthode pour afficher les employés
    }

}
