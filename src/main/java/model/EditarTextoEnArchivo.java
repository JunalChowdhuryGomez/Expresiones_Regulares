package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EditarTextoEnArchivo {

    public void editarTexto(String palabraABuscar, String palabraReemplazo) {
        String nombreArchivoTexto = "src/main/java/data/archivos.txt";
        File archivo = new File(nombreArchivoTexto);
        File archivoTemp = new File("src/main/java/data/archivos_temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(archivo));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemp))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Reemplazar la palabra en la línea actual
                linea = linea.replaceAll(palabraABuscar, palabraReemplazo);
                bw.write(linea + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Eliminar el archivo original
        archivo.delete();

        // Renombrar el archivo temporal al nombre original
        archivoTemp.renameTo(archivo);
    }
}
