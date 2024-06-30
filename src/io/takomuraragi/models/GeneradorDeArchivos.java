package io.takomuraragi.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivos {
    public void generarJsonDeHistorial(HistorialDeConversion historialDeConversion,
                                       String nombreDeArchivo) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        FileWriter escritura = new FileWriter(nombreDeArchivo);
        escritura.write(gson.toJson(historialDeConversion));
        escritura.close();

    }
}
