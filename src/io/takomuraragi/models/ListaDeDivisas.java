package io.takomuraragi.models;

import io.takomuraragi.exepciones.OpcionInvalidaException;

import java.util.InputMismatchException;

public class ListaDeDivisas {

    public String imprimirListaDeDivisas() {
        return """
               \n\t1. ARS - Peso Argentino
               \t2. BOB - Boliviano boliviano
               \t3. BRL - Real brasileño
               \t4. CLP - Peso chileno
               \t5. COP - Peso colombiano
               \t6. PEN - Sol peruano
               \t7. USD - Dólar estadounidense""";
    }

    public String devolverDivisa(String  eleccion) {
        int numeroEleccion = Integer.valueOf(eleccion);
        if (numeroEleccion > 7 || numeroEleccion < 1){
            throw new OpcionInvalidaException("Ingrese una opción válida.");
        }
        return switch (numeroEleccion) {
            case 1 -> "ARS";
            case 2 -> "BOB";
            case 3 -> "BRL";
            case 4 -> "CLP";
            case 5 -> "COP";
            case 6 -> "PEN";
            case 7 -> "USD";
            default -> null;
        };
    }
}
