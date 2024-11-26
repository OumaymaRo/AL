package Interfaces;

public interface InterfaceIHMemployee {
    void ajouterEmployee(String matricule, String nom, String prenom,String adresse);  // Ajouter un employé
    void afficherEmployee(String matricule);  // Afficher un employé
    void modifierEmployee(String matricule, String nouveauNom, String nouveauPrenom);  // Modifier un employé
    void supprimerEmployee(String matricule);  // Supprimer un employé
    void afficherTousEmployees();  // Afficher tous les employés
}
