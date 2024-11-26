package IHM;

import Interfaces.InterfaceIHMficheSalaire;
import SERVICE.GestionFicheSalaireArray;
import MODELS.FicheSalaire;
import MODELS.Employee;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IHMFicheSalaireSwing extends JFrame implements InterfaceIHMficheSalaire {
    private GestionFicheSalaireArray gestionFicheSalaire = new GestionFicheSalaireArray();
    private JTextArea textArea;
    private JTextField matriculeField, montantBrutField, montantNetField;
    private JButton ajouterButton, modifierButton, supprimerButton, afficherButton, afficherTousButton;

    public IHMFicheSalaireSwing() {
        setTitle("Gestion des Fiches de Salaire");
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
        montantBrutField = new JTextField();
        montantNetField = new JTextField();

        // Boutons
        ajouterButton = new JButton("Ajouter Fiche");
        modifierButton = new JButton("Modifier Fiche");
        supprimerButton = new JButton("Supprimer Fiche");
        afficherButton = new JButton("Afficher Salaire");
        afficherTousButton = new JButton("Afficher Tous");

        // Zone de texte pour afficher les résultats
        textArea = new JTextArea();
        textArea.setEditable(false);

        // Ajout des composants au panel
        panel.add(new JLabel("Matricule :"));
        panel.add(matriculeField);
        panel.add(new JLabel("Montant Brut :"));
        panel.add(montantBrutField);
        panel.add(new JLabel("Montant Net :"));
        panel.add(montantNetField);
        panel.add(ajouterButton);
        panel.add(modifierButton);
        panel.add(supprimerButton);
        panel.add(afficherButton);
        panel.add(afficherTousButton);

        // Ajout du panel et de la zone de texte à la fenêtre
        this.add(panel, BorderLayout.NORTH);
        this.add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Gestion des événements
        ajouterButton.addActionListener(e -> ajouterFicheSalaire());
        modifierButton.addActionListener(e -> modifierFicheSalaire());
        supprimerButton.addActionListener(e -> supprimerFicheSalaire());
        afficherButton.addActionListener(e -> afficherSalaire(matriculeField.getText()));
        afficherTousButton.addActionListener(e -> afficherTousSalaires());
    }

    @Override
    public void afficherMenu() {
        // Pas nécessaire en Swing, car l'interface graphique remplace le menu console.
    }

    @Override
    public void ajouterFicheSalaire() {
        String matricule = matriculeField.getText();
        double montantBrut = Double.parseDouble(montantBrutField.getText());
        double montantNet = Double.parseDouble(montantNetField.getText());

        boolean success = gestionFicheSalaire.ajouterSalaire(matricule, montantBrut, montantNet);
        if (success) {
            textArea.setText("Fiche de salaire ajoutée avec succès.");
            viderChamps();  // Vider les champs après ajout
        } else {
            textArea.setText("Erreur lors de l'ajout de la fiche de salaire.");
        }
    }

    @Override
    public void modifierFicheSalaire() {
        String matricule = matriculeField.getText();
        double montantBrut = Double.parseDouble(montantBrutField.getText());
        double montantNet = Double.parseDouble(montantNetField.getText());

        boolean success = gestionFicheSalaire.modifierSalaire(matricule, montantBrut, montantNet);
        if (success) {
            textArea.setText("Fiche de salaire modifiée avec succès.");
            viderChamps();  // Vider les champs après modification
        } else {
            textArea.setText("Erreur lors de la modification de la fiche de salaire.");
        }
    }

    @Override
    public void supprimerFicheSalaire() {
        String matricule = matriculeField.getText();
        boolean success = gestionFicheSalaire.supprimerSalaire(matricule);
        if (success) {
            textArea.setText("Fiche de salaire supprimée avec succès.");
            viderChamps();  // Vider les champs après suppression
        } else {
            textArea.setText("Aucune fiche de salaire trouvée pour le matricule : " + matricule);
        }
    }


    @Override
    public void afficherSalaire(String matricule) {
        matricule = matriculeField.getText();  // Récupérer le matricule du champ de texte
        if (matricule.isEmpty()) {
            textArea.setText("Veuillez entrer un matricule.");
        } else {
            double salaire = gestionFicheSalaire.rechercherSalaire(matricule);
            if (salaire > 0) {
                textArea.setText("Le salaire net de l'employé " + matricule + " est : " + salaire);
            } else {
                textArea.setText("Aucune fiche de salaire trouvée pour le matricule : " + matricule);
            }
        }
    }

    @Override
    public void afficherTousSalaires() {
        ArrayList<FicheSalaire> fiches = gestionFicheSalaire.getFichesSalaires();
        if (fiches.isEmpty()) {
            textArea.setText("Aucune fiche de salaire disponible.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (FicheSalaire fiche : fiches) {
                sb.append(fiche).append("\n");  // Afficher toutes les fiches de salaire
            }
            textArea.setText(sb.toString());
        }
    }

    // Nouvelle fonction pour vider les champs
    private void viderChamps() {
        matriculeField.setText("");
        montantBrutField.setText("");
        montantNetField.setText("");
    }

    public static void main(String[] args) {
        new IHMFicheSalaireSwing();
    }
}
