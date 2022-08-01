package edu.javeriana.abetapptests.pages;

import com.codeborne.selenide.WebDriverRunner;

public class BasePage {

    public String baseUrl = "http://localhost:8080";
    public String getCurrentUrl(){
        return WebDriverRunner.getWebDriver().getCurrentUrl();
    }
}
