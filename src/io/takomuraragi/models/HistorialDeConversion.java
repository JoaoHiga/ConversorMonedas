package io.takomuraragi.models;

import java.util.ArrayList;
import java.util.List;

public class HistorialDeConversion {
    private List<Conversion> listaDeConversiones = new ArrayList<>();

    public void agregarConversion (Conversion conversion) {
        this.listaDeConversiones.add(conversion);
    }

    public void imprimirHistorialDeConversiones (){
        int counter = 1;
        System.out.println("""
                ************************************************************
                                 Historial de Conversiones
                ************************************************************""");
        for (Conversion item :listaDeConversiones){
            System.out.printf("Transacción N°%d:\n\t%s\n", counter, item.toString());
            counter++;
        }
    }
}
