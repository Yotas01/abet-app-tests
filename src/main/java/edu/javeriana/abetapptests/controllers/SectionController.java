package edu.javeriana.abetapptests.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SectionController extends BaseController{

    public String baseURL;

    public SectionController() {
        this.baseURL = getBaseURL().concat("/admin/course/%d/section");
    }

    public HttpResponse<String> createSection(Integer courseNumber, String section) throws IOException, InterruptedException {
        String url = String.format(baseURL,courseNumber);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type","application/json")
                .timeout(timeout)
                .POST(HttpRequest.BodyPublishers.ofString(section))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> deleteSection(Integer courseNumber, Integer sectionNumber) throws IOException, InterruptedException {
        String url = String.format(baseURL, courseNumber) + "/" + sectionNumber;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type","application/json")
                .timeout(timeout)
                .DELETE()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
