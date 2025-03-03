package Vista;

import controlador.CSVReader;
import java.util.Scanner;

public class Vista {
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        CSVReader reader = new CSVReader("Data_DeportistasBeneficiadosPAD_2.csv");

        boolean autenticado = false;
        int intentos = 0;
        final int MAX_INTENTOS = 3;

        while (!autenticado && intentos < MAX_INTENTOS) {
            System.out.print("Ingrese su usuario (Nombre): ");
            String usuario = lector.nextLine().trim();
            System.out.print("Ingrese su contraseña (Item): ");
            String contrasena = lector.nextLine().trim();

            if (reader.validarUsuario(usuario, contrasena)) {
                autenticado = true;
                System.out.println("Acceso concedido. Bienvenido " + usuario + "!");
            } else {
                intentos++;
                System.out.println("Usuario o contraseña incorrectos. Intento " + intentos + " de " + MAX_INTENTOS);
            }
        }

        if (!autenticado) {
            System.out.println("Demasiados intentos fallidos.");
            System.out.println("Saliendo del sistema...");
            return;
        }

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Generar reporte por Año");
            System.out.println("2. Generar reporte por Provincia");
            System.out.println("3. Generar reporte por Ubigeo");
            System.out.println("4. Generar reporte por Federación");
            System.out.println("5. Generar reporte por País");
            System.out.println("6. Generar reporte por Monto");
            System.out.println("7. Generar reporte por Departamento");
            System.out.println("8. Exportar último reporte a TXT");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = 0;
            try {
                opcion = Integer.parseInt(lector.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido.");
                continue;
            }

            if (opcion >= 1 && opcion <= 7) {
                System.out.print("Ingrese el valor de búsqueda: ");
                String filtro = lector.nextLine();
                System.out.print("Cuántos resultados desea ver: ");
                int cantidad = 0;

                try {
                    cantidad = Integer.parseInt(lector.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Ingrese un número válido.");
                    continue;
                }

                reader.generarReporte(opcion, filtro, cantidad);
            } else if (opcion == 8) {
                reader.exportarReporteATxt();
            } else if (opcion == 9) {
                System.out.println("Programa finalizado.");
                lector.close();
                return;
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }
}