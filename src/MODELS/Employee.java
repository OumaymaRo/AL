package MODELS;

import java.util.List;
import java.util.Objects;

public class Employee {

    private String matricule; // Matricule est maintenant un String
    private String nom;
    private String prenom;
    private List<FicheSalaire> listeFichesSalaire;


    public Employee() {
        this.matricule = "matricule";
        this.nom = "nom";
        this.prenom = "prenom";
        this.adresse = "adresse";
    }

    private String adresse;

    public String getMatricule() {
        return matricule;
    }

    public List<FicheSalaire> getListeFichesSalaire() {
        return listeFichesSalaire;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(matricule, employee.matricule) && Objects.equals(nom, employee.nom) && Objects.equals(prenom, employee.prenom) && Objects.equals(listeFichesSalaire, employee.listeFichesSalaire) && Objects.equals(adresse, employee.adresse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricule, nom, prenom, listeFichesSalaire, adresse);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "matricule='" + matricule + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", listeFichesSalaire=" + listeFichesSalaire +
                ", adresse='" + adresse + '\'' +
                '}';
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setListeFichesSalaire(List<FicheSalaire> listeFichesSalaire) {
        this.listeFichesSalaire = listeFichesSalaire;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public Employee(String matricule, String nom, String prenom, String adresse, List<FicheSalaire> listeFichesSalaire) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.listeFichesSalaire = listeFichesSalaire;
    }

    // List of salary slips associated with the employee
}
