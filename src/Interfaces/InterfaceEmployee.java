package Interfaces;

import MODELS.Employee;

import java.util.List;

public interface InterfaceEmployee {
    int AjouterEmployee(Employee emp);
    int ModifierEmployee(String matricule,Employee emp);
    Employee RechercherEmployee(String matricule);
    int SupprimerEmployee(String Matricule);
    List<Employee> AfficherListeEmployee();
}