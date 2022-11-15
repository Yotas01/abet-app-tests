package edu.javeriana.abetapptests.controllers;

import org.openqa.selenium.remote.http.HttpMethod;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class BaseController {
    protected HttpClient client = HttpClient.newHttpClient();
    protected Duration timeout = Duration.ofSeconds(10);
    protected String getBaseURL(){
        return "http://localhost:8090";
    }

    protected HttpRequest createRequest(String url, HttpMethod method) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type","application/json")
                .timeout(timeout)
                .method(method.name(), HttpRequest.BodyPublishers.noBody())
                .build();
        System.out.println(request.uri() + " " + request.method());
        return request;
    }

    protected HttpRequest createRequest(String url, HttpMethod method, String body) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type","application/json")
                .timeout(timeout)
                .method(method.name(), HttpRequest.BodyPublishers.ofString(body))
                .build();
        System.out.println(request.uri() + " " + request.method() + ": " + body);
        return request;
    }
}
