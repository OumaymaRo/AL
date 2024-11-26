package SERVICE;

import MODELS.FicheSalaire;
import MODELS.Employee;
import Interfaces.InterfaceSalaire;
import java.util.ArrayList;
import java.util.Date;

public class GestionFicheSalaireArray implements InterfaceSalaire {
    private ArrayList<FicheSalaire> fichesSalaires = new ArrayList<>();
    private ArrayList<Employee> employees=new ArrayList<>(); // Liste des employés

    @Override
    public double CalculerMontantBrut(int nb, double tauxH) {
        if (nb < 0 || tauxH < 0) {
            throw new IllegalArgumentException("Le nombre d'heures et le taux horaire doivent être positifs.");
        }
        return nb * tauxH;
    }

    @Override
    public double CalculerMontantNet(double taxe, double montant) {
        if (taxe < 0 || taxe > 100) {
            throw new IllegalArgumentException("La taxe doit être comprise entre 0 et 100.");
        }
        return montant * (1 - taxe / 100);
    }

    @Override
    public double rechercherSalaire(String matricule) {
        for (FicheSalaire fiche : fichesSalaires) {
            if (fiche.getEmployee().getMatricule().equals(matricule)) {
                return fiche.getMontantNet();
            }
        }
        return 0; // Si aucune fiche ne correspond
    }

    @Override
    public double calculerSalaireTotal() {
        double total = 0.0;
        for (FicheSalaire ficheSalaire : fichesSalaires) {
            total += ficheSalaire.getMontantNet();
        }
        return total;
    }


    @Override
    public boolean ajouterSalaire(String matricule, double montantBrut, double montantNet) {
        // Vérifiez si l'employé existe déjà dans la liste des employés
        boolean employeeExists = false;
        Employee existingEmployee = null; // Référence pour l'employé existant
        for (Employee employee : employees) {  // Liste d'employés (supposée être définie ailleurs)
            if (employee.getMatricule().equals(matricule)) {
                employeeExists = true;
                existingEmployee = employee; // L'employé existant
                break; // L'employé existe, on sort de la boucle
            }
        }

        // Si l'employé n'existe pas, on le crée
        if (!employeeExists) {
            existingEmployee = new Employee(matricule); // Créer un nouvel employé avec le matricule
            employees.add(existingEmployee); // Ajouter l'employé à la liste
        }

        // Calculer le nombre d'heures (par exemple, c'est une valeur par défaut, à ajuster selon vos besoins)
        int nbHeures = 0;  // A ajuster selon le cas
        double tauxHoraire = 0;  // A ajuster selon le cas
        double taxe = 0;  // A ajuster selon le cas

        // Obtenir la date actuelle pour la fiche de salaire
        Date dateFiche = new Date(); // Date actuelle

        // Générer un numéro de fiche unique, par exemple en utilisant la taille de la liste des fiches
        int numFiche = fichesSalaires.size() + 1;

        // Créer une fiche de salaire pour l'employé
        FicheSalaire fiche = new FicheSalaire(existingEmployee, montantNet, taxe, tauxHoraire, montantBrut, nbHeures, numFiche,dateFiche);

        // Ajouter la fiche de salaire à la liste des fiches de salaire
        fichesSalaires.add(fiche);

        return true; // Le salaire a été ajouté avec succès
    }

    @Override
    public double obtenirSalaire(String matricule) {
        return rechercherSalaire(matricule);
    }

    @Override
    public boolean modifierSalaire(String matricule, double nouveauMontantBrut, double nouveauMontantNet) {
        for (FicheSalaire fiche : fichesSalaires) {
            if (fiche.getEmployee().getMatricule().equals(matricule)) {
                fiche.setMontantBrut(nouveauMontantBrut);
                fiche.setMontantNet(nouveauMontantNet);
                return true;
            }
        }
        return false; // Salaire non trouvé
    }

    @Override
    public boolean supprimerSalaire(String matricule) {
        return fichesSalaires.removeIf(fiche -> fiche.getEmployee().getMatricule().equals(matricule));
    }
    public ArrayList<FicheSalaire> getFichesSalaires() {
        return fichesSalaires;
    }

}
