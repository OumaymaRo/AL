package MODELS;

import java.util.Date;

public class FicheSalaire
{
    private int numFiche;
    private Date dateFiche;
    private int nbHeures;
    private double tauxHoraire;
    private double montantBrut;

    public Date getDateFiche() {
        return dateFiche;
    }

    @Override
    public String toString() {
        return "FicheSalaire{" +
                "numFiche=" + numFiche +
                ", dateFiche=" + dateFiche +
                ", nbHeures=" + nbHeures +
                ", tauxHoraire=" + tauxHoraire +
                ", montantBrut=" + montantBrut +
                ", taxe=" + taxe +
                ", montantNet=" + montantNet +
                ", employee=" + employee +
                '}';
    }

    public FicheSalaire(Employee employee, double montantNet, double taxe, double tauxHoraire, double montantBrut, int nbHeures, int numFiche, Date dateFiche) {
        this.employee = employee;
        this.montantNet = montantNet;
        this.taxe = taxe;
        this.tauxHoraire = tauxHoraire;
        this.montantBrut = montantBrut;
        this.nbHeures = nbHeures;
        this.numFiche = numFiche;
        this.dateFiche = dateFiche;
    }

    public void setDateFiche(Date dateFiche) {
        this.dateFiche = dateFiche;
    }

    public int getNumFiche() {
        return numFiche;
    }

    public void setNumFiche(int numFiche) {
        this.numFiche = numFiche;
    }

    public int getNbHeures() {
        return nbHeures;
    }

    public void setNbHeures(int nbHeures) {
        this.nbHeures = nbHeures;
    }

    public double getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public double getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(double montantBrut) {
        this.montantBrut = montantBrut;
    }

    public double getTaxe() {
        return taxe;
    }

    public void setTaxe(double taxe) {
        this.taxe = taxe;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public double getMontantNet() {
        return montantNet;
    }

    public void setMontantNet(double montantNet) {
        this.montantNet = montantNet;
    }

    private double taxe;
    private double montantNet;
    private Employee employee;
}
