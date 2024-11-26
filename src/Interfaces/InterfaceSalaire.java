package Interfaces;

public interface InterfaceSalaire {
    // Calcul des montants de salaire
    double CalculerMontantBrut(int nb, double tauxH);
    double CalculerMontantNet(double taxe, double montant);

    // Recherche d'un salaire par matricule
    double rechercherSalaire(String matricule);

    // Calcul du total des salaires de tous les employés
    double calculerSalaireTotal();

    // Ajouter un nouveau salaire
    boolean ajouterSalaire(String matricule, double montantBrut, double montantNet);

    // Lire ou obtenir les informations de salaire pour un employé spécifique
    double obtenirSalaire(String matricule);

    // Mettre à jour le salaire d'un employé existant
    boolean modifierSalaire(String matricule, double nouveauMontantBrut, double nouveauMontantNet);

    // Supprimer le salaire d'un employé
    boolean supprimerSalaire(String matricule);
}
