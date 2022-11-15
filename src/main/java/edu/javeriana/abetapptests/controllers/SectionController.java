package edu.javeriana.abetapptests.controllers;

import edu.javeriana.abetapptests.common.Mapper;
import edu.javeriana.abetapptests.entities.NameDTO;
import org.openqa.selenium.remote.http.HttpMethod;

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
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.POST, section), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> deleteSection(Integer courseNumber, Integer sectionNumber, Integer semester) throws IOException, InterruptedException {
        String url = String.format(baseURL,courseNumber).concat("/"+sectionNumber+"/semester"+semester);
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.DELETE), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> getSection(Integer courseNumber, Integer sectionNumber, Integer semester) throws IOException, InterruptedException {
        String url = String.format(baseURL,courseNumber).concat("/"+sectionNumber+"/semester/"+semester);
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.GET), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }

    public HttpResponse<String> getSectionsFromProfessor(String professor) throws IOException, InterruptedException {
        String url = getBaseURL().concat("/admin/course/sectionByProfessor");
        String json = Mapper.parseToJson(new NameDTO(professor));
        HttpResponse<String> response = client.send(createRequest(url, HttpMethod.GET, json), HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode() + " " + response.body());
        return response;
    }
}
