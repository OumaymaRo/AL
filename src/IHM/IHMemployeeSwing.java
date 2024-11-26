package IHM;

import Interfaces.InterfaceIHMemployee;
import MODELS.Employee;
import SERVICE.GestionEmployeeArray;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IHMemployeeSwing extends JFrame implements InterfaceIHMemployee {
    private GestionEmployeeArray gestionEmployee = new GestionEmployeeArray();
    private JTextArea textArea;
    private JTextField matriculeField, nomField, prenomField, adresseField;
    private JButton ajouterButton, modifierButton, supprimerButton, afficherButton, afficherTousButton;

    public IHMemployeeSwing() {
        setTitle("Gestion des Employés");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // Centrer la fenêtre

        // Initialisation des composants
        initComponents();

        // Affichage de la fenêtre
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Champs de texte
        matriculeField = new JTextField();
        nomField = new JTextField();
        prenomField = new JTextField();
        adresseField = new JTextField();

        // Boutons
        ajouterButton = new JButton("Ajouter Employé");
        modifierButton = new JButton("Modifier Employé");
        supprimerButton = new JButton("Supprimer Employé");
        afficherButton = new JButton("Afficher Employé");
        afficherTousButton = new JButton("Afficher Tous");

        // Zone de texte pour afficher les résultats
        textArea = new JTextArea();
        textArea.setEditable(false);

        // Ajout des composants au panel
        panel.add(new JLabel("Matricule :"));
        panel.add(matriculeField);
        panel.add(new JLabel("Nom :"));
        panel.add(nomField);
        panel.add(new JLabel("Prénom :"));
        panel.add(prenomField);
        panel.add(new JLabel("Adresse :"));
        panel.add(adresseField);
        panel.add(ajouterButton);
        panel.add(modifierButton);
        panel.add(supprimerButton);
        panel.add(afficherButton);
        panel.add(afficherTousButton);

        // Ajout du panel et de la zone de texte à la fenêtre
        this.add(panel, BorderLayout.NORTH);
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Gestion des événements
        ajouterButton.addActionListener(e -> {
            // Récupérer les valeurs des champs de texte
            String matricule = matriculeField.getText();
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            String adresse = adresseField.getText();

            // Appeler la méthode ajouterEmployee avec les valeurs extraites
            ajouterEmployee(matricule, nom, prenom, adresse);
        });

        modifierButton.addActionListener(e -> {
            String matricule = matriculeField.getText();
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            modifierEmployee(matricule, nom, prenom);
        });

        supprimerButton.addActionListener(e -> {
            String matricule = matriculeField.getText();
            supprimerEmployee(matricule);
        });

        afficherButton.addActionListener(e -> {
            String matricule = matriculeField.getText();
            afficherEmployee(matricule);
        });

        afficherTousButton.addActionListener(e -> afficherTousEmployees());
    }

    // Méthode pour ajouter un employé
    @Override
    public void ajouterEmployee(String matricule, String nom, String prenom, String adresse) {
        if (matricule.isEmpty() || nom.isEmpty() || prenom.isEmpty() || adresse.isEmpty()) {
            textArea.setText("Veuillez remplir tous les champs.");
        } else {
            Employee emp = new Employee(matricule, nom, prenom, adresse);
            gestionEmployee.AjouterEmployee(emp);
            textArea.setText("Employé ajouté avec succès.");
            viderChamps();  // Vider les champs après ajout
        }
    }

    // Méthode pour modifier un employé
    @Override
    public void modifierEmployee(String matricule, String nouveauNom, String nouveauPrenom) {
        if (matricule.isEmpty() || nouveauNom.isEmpty() || nouveauPrenom.isEmpty()) {
            textArea.setText("Veuillez remplir tous les champs.");
        } else {
            Employee empToUpdate = new Employee(matricule, nouveauNom, nouveauPrenom, adresseField.getText());
            gestionEmployee.ModifierEmployee(matricule, empToUpdate);
            textArea.setText("Employé modifié avec succès.");
            viderChamps();  // Vider les champs après modification
        }
    }

    // Méthode pour supprimer un employé
    @Override
    public void supprimerEmployee(String matricule) {
        if (matricule.isEmpty()) {
            textArea.setText("Veuillez entrer le matricule de l'employé à supprimer.");
        } else {
            gestionEmployee.SupprimerEmployee(matricule);
            textArea.setText("Employé supprimé avec succès.");
            viderChamps();  // Vider les champs après suppression
        }
    }

    // Méthode pour afficher un employé
    @Override
    public void afficherEmployee(String matricule) {
        if (matricule.isEmpty()) {
            textArea.setText("Veuillez entrer le matricule de l'employé.");
        } else {
            Employee emp = gestionEmployee.RechercherEmployee(matricule);
            if (emp != null) {
                textArea.setText("Détails de l'employé :\n" + emp);
            } else {
                textArea.setText("Aucun employé trouvé avec le matricule : " + matricule);
            }
        }
    }

    // Méthode pour afficher tous les employés
    @Override
    public void afficherTousEmployees() {
        ArrayList<Employee> employees = (ArrayList<Employee>) gestionEmployee.AfficherListeEmployee();
        if (employees.isEmpty()) {
            textArea.setText("Aucun employé trouvé.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Employee emp : employees) {
                sb.append(emp).append("\n");  // Affiche tous les employés
            }
            textArea.setText(sb.toString());
        }
    }

    // Méthode pour vider les champs de saisie
    private void viderChamps() {
        matriculeField.setText("");
        nomField.setText("");
        prenomField.setText("");
        adresseField.setText("");
    }

    public static void main(String[] args) {
        new IHMemployeeSwing();
    }
}
