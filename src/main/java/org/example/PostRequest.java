package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class PostRequest {
    private final URI uri;
    private final HttpClient client;

    public PostRequest(String uri) {
        this.uri = URI.create(uri);
        this.client = HttpClient.newHttpClient();
    }

    public HttpResponse<String> send(String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        return this.client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
