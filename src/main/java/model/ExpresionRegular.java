/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author JUNAL
 */
public class ExpresionRegular {
     public static String[] buscar(String[] nombresArchivos, double porcentajeSimilitud, String nombreArchivoBuscar) {
        List<String> archivosFiltrados = new ArrayList<>();

        // Escapar caracteres especiales del nombre del archivo a buscar
        String nombreEscapado = Pattern.quote(nombreArchivoBuscar);

        // Construir la expresión regular con el porcentaje de similitud
        String regex = ".*" + generarExpresionRegular(nombreEscapado, porcentajeSimilitud) + ".*";

        // Compilar la expresión regular
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        // Buscar coincidencias en los nombres de archivos
        for (String nombreArchivo : nombresArchivos) {
            Matcher matcher = pattern.matcher(nombreArchivo);
            if (matcher.matches()) {
                archivosFiltrados.add(nombreArchivo);
            }
        }

        return archivosFiltrados.toArray(new String[0]);
    }

    // Generar la expresión regular para el porcentaje de similitud
    private static String generarExpresionRegular(String nombreEscapado, double porcentajeSimilitud) {
        // Calcular el número de caracteres que se permitirán de diferencia
        int maxDiferencia = (int) Math.ceil(nombreEscapado.length() * (1 - porcentajeSimilitud));
        
        // Construir la expresión regular para permitir hasta "maxDiferencia" caracteres de diferencia
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(nombreEscapado).append("]");
        sb.append("{").append(nombreEscapado.length() - maxDiferencia).append(",").append(nombreEscapado.length() + maxDiferencia).append("}");
        return sb.toString();
    }
    public static String[] leerArchivo(String nombreArchivo) throws IOException {
        List<String> nombresArchivos = new ArrayList<>();

        // Abre el archivo de texto y lee los nombres de archivos
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                nombresArchivos.add(linea);
            }
        }

        // Convierte la lista de nombres de archivos a un array de strings
        return nombresArchivos.toArray(new String[0]);
    }
    
}
