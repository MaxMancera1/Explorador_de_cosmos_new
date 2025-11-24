package org.example.explorador_de_cosmos.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.explorador_de_cosmos.models.Planet;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PlanetService {
    
    // API Key de NASA - Puedes reemplazar DEMO_KEY con tu propia API key
    private static final String NASA_API_KEY = "DEMO_KEY";
    
    /**
     * Obtiene la imagen astronómica del día (APOD)
     * @return Objeto Planet con los datos de APOD
     */
    public static Planet obtenerApod() {
        try {
            String endpoint = "https://api.nasa.gov/planetary/apod?api_key=" + NASA_API_KEY;
            HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(conn.getInputStream());
                JsonObject data = JsonParser.parseReader(reader).getAsJsonObject();
                reader.close();

                String date = data.has("date") ? data.get("date").getAsString() : "";
                String title = data.has("title") ? data.get("title").getAsString() : "";
                String explanation = data.has("explanation") ? data.get("explanation").getAsString() : "";
                String url = data.has("url") ? data.get("url").getAsString() : "";
                String mediaType = data.has("media_type") ? data.get("media_type").getAsString() : "";

                return new Planet(date, title, explanation, url, mediaType);
            } else {
                System.out.println("❌ Error HTTP: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error en la conexión: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Obtiene la imagen astronómica de una fecha específica
     * @param fecha Fecha en formato YYYY-MM-DD
     * @return Objeto Planet con los datos de APOD
     */
    public static Planet obtenerApodPorFecha(String fecha) {
        try {
            String endpoint = "https://api.nasa.gov/planetary/apod?api_key=" + NASA_API_KEY + "&date=" + fecha;
            HttpURLConnection conn = (HttpURLConnection) new URL(endpoint).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() == 200) {
                InputStreamReader reader = new InputStreamReader(conn.getInputStream());
                JsonObject data = JsonParser.parseReader(reader).getAsJsonObject();
                reader.close();

                String date = data.has("date") ? data.get("date").getAsString() : "";
                String title = data.has("title") ? data.get("title").getAsString() : "";
                String explanation = data.has("explanation") ? data.get("explanation").getAsString() : "";
                String url = data.has("url") ? data.get("url").getAsString() : "";
                String mediaType = data.has("media_type") ? data.get("media_type").getAsString() : "";

                return new Planet(date, title, explanation, url, mediaType);
            } else {
                System.out.println("❌ Error HTTP: " + conn.getResponseCode());
            }
        } catch (Exception e) {
            System.out.println("⚠️ Error en la conexión: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
