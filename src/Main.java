
import Interfaces.InterfaceIHMemployee;
import IHM.IHMemployeeConsole; // Version Console
import IHM.IHMemployeeSwing;   // Version Swing

import java.util.Scanner;

public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choisissez l'interface :");
            System.out.println("1. Console");
            System.out.println("2. Swing");
            int choix = scanner.nextInt();

            InterfaceIHMemployee interfaceIHMemployee;

            if (choix == 1) {
                interfaceIHMemployee = new IHMemployeeConsole();
                ((IHMemployeeConsole) interfaceIHMemployee).demarrerConsole();
            } else {
                interfaceIHMemployee = new IHMemployeeSwing();
            }

            scanner.close();
        }
}
