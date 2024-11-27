package SERVICE;

import Interfaces.InterfaceEmployee;
import MODELS.Employee;
import DATABASE.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestionEmployeeJDBC implements InterfaceEmployee {

    @Override
    public int AjouterEmployee(Employee emp) {
        String sql = "INSERT INTO Employee (matricule, nom, prenom, adresse) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emp.getMatricule());
            stmt.setString(2, emp.getNom());
            stmt.setString(3, emp.getPrenom());
            stmt.setString(4, emp.getAdresse());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employé ajouté : " + emp);
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int ModifierEmployee(String matricule, Employee emp) {
        String sql = "UPDATE Employee SET nom = ?, prenom = ?, adresse = ? WHERE matricule = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, emp.getNom());
            stmt.setString(2, emp.getPrenom());
            stmt.setString(3, emp.getAdresse());
            stmt.setString(4, matricule);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employé modifié : " + emp);
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Employee RechercherEmployee(String matricule) {
        String sql = "SELECT * FROM Employee WHERE matricule = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricule);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Employee emp = new Employee(
                        rs.getString("matricule"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse")
                );
                return emp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int SupprimerEmployee(String matricule) {
        String sql = "DELETE FROM Employee WHERE matricule = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricule);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employé supprimé : " + matricule);
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Employee> AfficherListeEmployee() {
        String sql = "SELECT * FROM Employee";
        List<Employee> employees = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee emp = new Employee(
                        rs.getString("matricule"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("adresse")
                );
                employees.add(emp);
            }

            if (employees.isEmpty()) {
                System.out.println("Aucun employé dans la base de données.");
            } else {
                for (Employee emp : employees) {
                    System.out.println(emp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }
}
