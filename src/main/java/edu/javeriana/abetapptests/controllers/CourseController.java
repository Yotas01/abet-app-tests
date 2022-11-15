package edu.javeriana.abetapptests.controllers;

import edu.javeriana.abetapptests.common.Mapper;
import edu.javeriana.abetapptests.entities.ValueDTO;
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

    public HttpResponse<String> addCdiotoCourse(Integer courseNumber, Float cdioNumber, Integer bloomValue) throws IOException, InterruptedException {
        String url = baseURL + "/" + courseNumber + "/cdio/" + cdioNumber;
        String json = Mapper.parseToJson(new ValueDTO(bloomValue));
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.POST, json), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> removeCdioFromCourse(Integer courseNumber, Float cdioNumber) throws IOException, InterruptedException {
        String url = baseURL + "/" + courseNumber + "/cdio/" + cdioNumber;
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.DELETE), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> updateBloomValue(Integer courseNumber, Float cdioNumber, Integer bloomValue) throws IOException, InterruptedException {
        String url = baseURL + "/" + courseNumber + "/cdio/" + cdioNumber;
        String json = Mapper.parseToJson(new ValueDTO(bloomValue));
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.valueOf("PUT") , json), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }
}
