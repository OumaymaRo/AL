package SERVICE;

import MODELS.FicheSalaire;
import MODELS.Employee;
import Interfaces.InterfaceSalaire;
import DATABASE.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionFicheSalaireJDBC implements InterfaceSalaire {

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
        String sql = "SELECT montantNet FROM FicheSalaire WHERE matricule = ? ORDER BY dateFiche DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricule);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("montantNet");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double calculerSalaireTotal() {
        String sql = "SELECT SUM(montantNet) AS total FROM FicheSalaire";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getDouble("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean ajouterSalaire(String matricule, double montantBrut, double montantNet) {
        String sql = "INSERT INTO FicheSalaire (dateFiche, nbHeures, tauxHoraire, montantBrut, taxe, montantNet, matricule) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Par défaut, ajustez les paramètres nécessaires ici
            int nbHeures = 160; // Exemple : heures mensuelles
            double tauxHoraire = montantBrut / nbHeures; // Déduit du montant brut
            double taxe = ((montantBrut - montantNet) / montantBrut) * 100; // Déduit du montant brut et net
            Date dateFiche = new Date(); // Date actuelle

            // Remplir les valeurs de la requête
            stmt.setDate(1, new java.sql.Date(dateFiche.getTime()));
            stmt.setInt(2, nbHeures);
            stmt.setDouble(3, tauxHoraire);
            stmt.setDouble(4, montantBrut);
            stmt.setDouble(5, taxe);
            stmt.setDouble(6, montantNet);
            stmt.setString(7, matricule);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0; // Retourne true si l'insertion réussit
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public double obtenirSalaire(String matricule) {
        return rechercherSalaire(matricule);
    }

    @Override
    public boolean modifierSalaire(String matricule, double nouveauMontantBrut, double nouveauMontantNet) {
        String sql = "UPDATE FicheSalaire SET montantBrut = ?, montantNet = ? WHERE matricule = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, nouveauMontantBrut);
            stmt.setDouble(2, nouveauMontantNet);
            stmt.setString(3, matricule);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // Retourne true si la mise à jour réussit
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean supprimerSalaire(String matricule) {
        String sql = "DELETE FROM FicheSalaire WHERE matricule = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricule);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0; // Retourne true si la suppression réussit
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<FicheSalaire> getFichesSalaires() {
        String sql = "SELECT * FROM FicheSalaire";
        List<FicheSalaire> fiches = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                // Créer une instance de FicheSalaire pour chaque ligne
                FicheSalaire fiche = new FicheSalaire(
                        new Employee(rs.getString("matricule")), // Associer un employé via matricule
                        rs.getDouble("montantNet"),
                        rs.getDouble("taxe"),
                        rs.getDouble("tauxHoraire"),
                        rs.getDouble("montantBrut"),
                        rs.getInt("nbHeures"),
                        rs.getInt("numFiche"),
                        rs.getDate("dateFiche")
                );
                fiches.add(fiche);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<FicheSalaire>) fiches;
    }



}
