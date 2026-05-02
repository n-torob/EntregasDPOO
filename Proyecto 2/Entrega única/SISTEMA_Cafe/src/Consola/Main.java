// NUEVO: El otro main se borró porque no sirve para esta entrega. Este va a servir como "base" para las otras 3 subconsolas.

package Consola;

import java.util.Scanner;

public class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=========================================");
            System.out.println("      BOARD GAME CAFÉ — BIENVENIDO");
            System.out.println("=========================================");
            System.out.println("1. Acceso clientes");
            System.out.println("2. Acceso empleados");
            System.out.println("3. Acceso administración");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = leerInt();

            switch (opcion) {
                case 1:
                    new MainCliente().iniciar();
                    break;
                case 2:
                    new MainEmpleado().iniciar();
                    break;
                case 3:
                    new MainAdministrador().iniciar();
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    private static int leerInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un número entero: ");
            }
        }
    }
}