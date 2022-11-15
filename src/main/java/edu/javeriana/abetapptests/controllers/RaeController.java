package edu.javeriana.abetapptests.controllers;

import org.openqa.selenium.remote.http.HttpMethod;

import java.io.IOException;
import java.net.http.HttpResponse;

public class RaeController extends BaseController{

    public String baseUrl;

    public RaeController() {
        this.baseUrl = getBaseURL().concat("/admin/course/%d/rae");
    }

    public HttpResponse<String> createRae(Integer courseNumber, String rae) throws IOException, InterruptedException {
        String url = String.format(baseUrl, courseNumber);
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.POST, rae), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> deleteRae(Integer courseNumber, Long raeId) throws IOException, InterruptedException {
        String url = String.format(baseUrl, courseNumber).concat("/"+raeId);
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.DELETE), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> getRae(Integer courseNumber, Long raeId) throws IOException, InterruptedException {
        String url = String.format(baseUrl, courseNumber).concat("/"+raeId);
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.GET), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }
}
