package edu.javeriana.abetapptests.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourseController extends BaseController{

    public String baseURL;

    public CourseController() {
        baseURL = getBaseURL().concat("/admin/course");
    }

    public HttpResponse<String> getCourse(Integer courseNumber) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "/" + courseNumber.toString()))
                .header("Content-Type","application/json")
                .timeout(timeout)
                .GET()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> createCourse(String course) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL))
                .header("Content-Type","application/json")
                .timeout(timeout)
                .POST(HttpRequest.BodyPublishers.ofString(course))
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public HttpResponse<String> deleteCourse(Integer courseNumber) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL+"/"+courseNumber))
                .header("Content-Type","application/json")
                .timeout(timeout)
                .DELETE()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
