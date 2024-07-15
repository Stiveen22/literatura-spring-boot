package com.alura.literatura.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Libro;

public class GutendexClient {

    private static final String BASE_URL = "https://gutendex.com/books/";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GutendexClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Libro buscarLibroPorTitulo(String query) throws IOException, InterruptedException {
        URI uri = URI.create(BASE_URL + "?search=" + query);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error en la consulta a la API de Gutendex: " + response.statusCode());
        }

        String responseBody = response.body();
        JsonNode root = objectMapper.readTree(responseBody).path("results").get(0);

        // Crear el autor
        JsonNode autorNode = root.path("authors").get(0);
        Autor autor = new Autor();
        autor.setNombre(autorNode.path("name").asText());
        autor.setAnoNacimiento(autorNode.path("birth_year").asInt());
        autor.setAnoFallecimiento(autorNode.path("death_year").asInt());

        // Crear el libro
        Libro libro = new Libro();
        libro.setTitulo(root.path("title").asText());
        libro.setAutor(autor);
        libro.setIdioma(root.path("languages").get(0).asText());
        libro.setNumeroDescargas(root.path("download_count").asInt());

        return libro;
    }
}
