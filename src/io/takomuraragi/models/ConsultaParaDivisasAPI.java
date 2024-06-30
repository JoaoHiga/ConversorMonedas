package io.takomuraragi.models;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaParaDivisasAPI {

    public ConversionAPI consultarConversion(String divisaBase, String divisaFinal, double montoAConvertir){
        String API_KEY =  "2d42a45a34051b0658c445c6";
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/"
                + API_KEY + "/pair/" + divisaBase + "/" + divisaFinal +
                "/" + Double.toString(montoAConvertir));

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return gson.fromJson(response.body(), ConversionAPI.class);
        } catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
