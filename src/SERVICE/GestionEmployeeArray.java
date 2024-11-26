package SERVICE;

import Interfaces.InterfaceEmployee;
import MODELS.Employee;

import java.util.ArrayList;
import java.util.List;

public class GestionEmployeeArray implements InterfaceEmployee {
    List<Employee> Employees = new ArrayList<Employee>();

    @Override
    public int AjouterEmployee(Employee emp) {
        if (RechercherEmployee(emp.getMatricule()) != null) {
            System.out.println("l employee" + emp.getMatricule() + "est deja existe!!!");
        } else {
            Employees.add(emp);
            System.out.println("Employé ajouté : " + emp);
            return 1;
        }

        return 0;
    }

    @Override
    public int ModifierEmployee(String matricule, Employee emp) {
        Employee empExist = RechercherEmployee(emp.getMatricule());
        if (empExist != null) {
            empExist.setMatricule(emp.getMatricule());
            empExist.setNom(emp.getNom());
            empExist.setPrenom(emp.getPrenom());
            empExist.setAdresse(emp.getAdresse());
            System.out.println("Employé modifié : " + empExist);
            return 1;
        }
        return 0;
    }

    @Override
    public Employee RechercherEmployee(String matricule) {
        for (Employee t : Employees) {
            if (t.getMatricule().equals(matricule)) {
                return t;
            }
        }
        return null;
    }

    @Override
    public int SupprimerEmployee(String Matricule) {
        if (RechercherEmployee(Matricule) != null) {
            Employees.remove(RechercherEmployee(Matricule));
            System.out.println("l employee" + Matricule + "est supprimé");
            return 1;
        } else
            System.out.println("aucun employé avec ce matricule :" + Matricule);
        return 0;
    }

    @Override
    public List<Employee> AfficherListeEmployee() {
        if (Employees.isEmpty()) {
            System.out.println("Aucun employé dans la liste.");
        } else {
            for (Employee emp : Employees) {
                System.out.println(emp);  // Utilise la méthode `toString` d'Employee pour afficher les infos
            }
        }
        return Employees;
    }

}



