package controlador;

import modelo.Deportista;
import servicio.ExportadorTXT;
import java.io.*;
import java.util.*;

public class CSVReader {
    private List<Deportista> deportistas;
    private List<Deportista> reporteActual;

    public CSVReader(String archivo) {
        deportistas = new ArrayList<>();
        reporteActual = new ArrayList<>();
        cargarDatos(archivo);
    }

    private void cargarDatos(String archivo) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), "ISO-8859-1"))) {
            System.out.println("Archivo encontrado. Leyendo datos...");

            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(";");
                if (datos.length < 17) {
                    System.out.println("Linea ignorada (no tiene suficientes datos): " + linea);
                    continue;
                }

                try {
                    Deportista deportista = new Deportista(
                            datos[1].trim(),
                            Integer.parseInt(datos[2].trim()),
                            datos[4].trim(),
                            datos[7].trim(),
                            datos[5].trim(),
                            Double.parseDouble(datos[10].trim()),
                            datos[11].trim(),
                            datos[12].trim(),
                            datos[16].trim(),
                            datos[15].trim(),
                            datos[13].trim()
                    );

                    deportistas.add(deportista);

                } catch (NumberFormatException e) {
                    System.out.println("Error en formato numerico en la linea: " + linea);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public boolean validarUsuario(String usuario, String contrasena) {
        return deportistas.stream()
                .anyMatch(d -> {
                    String[] partes = d.getNombres().trim().split("\\s+");
                    String primerNombre = partes[0];
                    return primerNombre.equalsIgnoreCase(usuario) && d.getItem().equals(contrasena);
                });
    }

public void generarReporte(int opcion, String filtro, int cantidad) {
    try {
        double montoFiltro = (opcion == 6) ? Double.parseDouble(filtro.trim()) : 0;

        reporteActual = deportistas.stream()
                .filter(d -> switch (opcion) {
                    case 1 -> String.valueOf(d.getAnio()).equalsIgnoreCase(filtro);
                    case 2 -> d.getProvincia().equalsIgnoreCase(filtro);
                    case 3 -> d.getDistrito().equalsIgnoreCase(filtro);
                    case 4 -> d.getFederacion().equalsIgnoreCase(filtro);
                    case 5 -> d.getPais().equalsIgnoreCase(filtro);
                    case 6 -> Math.abs(d.getMonto() - montoFiltro) < 0.01;
                    case 7 -> d.getDepartamento().equalsIgnoreCase(filtro);
                    default -> false;
                })
                .limit(cantidad)
                .toList();

        if (reporteActual.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            System.out.println("\nReporte Generado:");
            System.out.printf("%-5s %-6s %-20s %-20s %-10s %-12s %-15s %-15s %-10s\n", 
                    "#", "Año", "Nombre", "Federacion", "Monto", "Pais", "Departamento", "Provincia", "Ubigeo");
            System.out.println("========================================================================================================================");

            int index = 1;
            for (Deportista d : reporteActual) {
                System.out.printf("%-5d %-6d %-20s %-20s S/ %-8.2f %-12s %-15s %-15s %-10s\n", 
                        index++, d.getAnio(), d.getNombres(), d.getFederacion(), d.getMonto(), d.getPais(), d.getDepartamento(), d.getProvincia(), d.getDistrito());
            }
        }
    } catch (NumberFormatException e) {
        System.out.println("El filtro de monto no es un numero valido.");
    }
}

    public void exportarReporteATxt() {
        if (reporteActual.isEmpty()) {
            System.out.println("No hay un reporte generado para exportar.");
        } else {
            ExportadorTXT.exportarReporte("Reporte_Deportistas.txt", reporteActual);
        }
    }
}