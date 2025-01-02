package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;


public class GetRequest {
    private final URI uri;
    private final HttpClient client;

    public GetRequest(String uri) {
        this.uri = URI.create(uri);
        this.client = HttpClient.newHttpClient();
    }

    public HttpResponse<String> send() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("accept", "application/json")
                .GET().build();

        return this.client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
