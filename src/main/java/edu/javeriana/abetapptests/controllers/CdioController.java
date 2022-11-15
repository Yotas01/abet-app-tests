package edu.javeriana.abetapptests.controllers;

import org.openqa.selenium.remote.http.HttpMethod;

import java.io.IOException;
import java.net.http.HttpResponse;

public class CdioController extends BaseController{

    public String baseURL;

    public CdioController() {
        baseURL = getBaseURL().concat("/admin/cdio");
    }

    public HttpResponse<String> createCdio(String cdio) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(createRequest(baseURL, HttpMethod.POST, cdio), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> deleteCdio(Float cdioNumber) throws IOException, InterruptedException {
        String url = baseURL + "/" + cdioNumber;
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.DELETE), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> getCdio(Float cdioNumber) throws IOException, InterruptedException {
        String url = baseURL + "/" + cdioNumber;
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.GET), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }
}
