package util;
import java.util.Scanner;

public class Validador {
    
    public static int leerInt(Scanner scanner) {
        while (true) {
            try {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            } catch (Exception e) {
                System.out.print("Error: Ingrese un número válido: ");
                scanner.nextLine();
            }
        }
    }
    
    public static double leerDouble(Scanner scanner) {
        while (true) {
            try {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            } catch (Exception e) {
                System.out.print("Error: Ingrese un número válido: ");
                scanner.nextLine();
            }
        }
    }
}