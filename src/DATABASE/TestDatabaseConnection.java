package DATABASE;

import java.sql.Connection;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            // Essayer d'établir une connexion
            Connection connection = DatabaseConnection.getConnection();

            if (connection != null) {
                System.out.println("Connexion réussie à la base de données !");
                connection.close(); // Toujours fermer la connexion après utilisation
            } else {
                System.out.println("Échec de la connexion à la base de données.");
            }
        } catch (Exception e) {
            System.out.println("Une erreur est survenue lors de la connexion :");
            e.printStackTrace();
        }
    }
}
