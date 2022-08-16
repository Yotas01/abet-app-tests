package edu.javeriana.abetapptests.controllers;

import java.net.http.HttpClient;
import java.time.Duration;

public class BaseController {
    protected HttpClient client = HttpClient.newHttpClient();
    protected Duration timeout = Duration.ofSeconds(10);
    protected String getBaseURL(){
        return "http://localhost:8090";
    }

}
