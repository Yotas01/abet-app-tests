package edu.javeriana.abetapptests.controllers;

import org.openqa.selenium.remote.http.HttpMethod;

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
        String url = baseURL+"/"+courseNumber;
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.GET), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> createCourse(String course) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(createRequest(baseURL, HttpMethod.POST, course), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> deleteCourse(Integer courseNumber) throws IOException, InterruptedException {
        String url = baseURL+"/"+courseNumber;
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.DELETE), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }
}
