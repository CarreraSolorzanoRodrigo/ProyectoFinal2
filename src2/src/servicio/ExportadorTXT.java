package servicio;

import modelo.Deportista;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportadorTXT {
    public static void exportarReporte(String nombreArchivo, List<Deportista> deportistas) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write("# | Año | Federación | Nombre | Monto | País | Departamento | Provincia | Ubigeo\n");
            writer.write("==========================================================================================\n");

            int index = 1;
            for (Deportista d : deportistas) {
                writer.write(String.format("%d | %d | %s | %s | S/ %.2f | %s | %s | %s | %s\n",
                        index++, d.getAnio(), d.getFederacion(), d.getNombres(), d.getMonto(),
                        d.getPais(), d.getDepartamento(), d.getProvincia(), d.getDistrito()));
            }

            System.out.println("Reporte exportado exitosamente a " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al exportar el reporte: " + e.getMessage());
        }
    }
}