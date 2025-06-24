import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Cenection {


    public String generarConeccion( String MonedaBase, String MonedaConversion, String cantidad) throws IOException, InterruptedException {
        String resultadoConversion = "";
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        String json;

        URI url = URI.create("https://v6.exchangerate-api.com/v6/cfe303fd77d037c9228bed8b/pair/"+MonedaBase+"/"+MonedaConversion+"/"+cantidad);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        json = response.body();
        resultadoConversion = gson.fromJson(json, Conversion.class).conversion_result()+"";

        return resultadoConversion;
    }



}
