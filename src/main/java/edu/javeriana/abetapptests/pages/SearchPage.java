package edu.javeriana.abetapptests.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage extends BasePage{

    public String URL = baseUrl + "/search";
    public SelenideElement courseField = $x("//*[@id=\"course\"]");
    public SelenideElement sectionField = $x("//*[@id=\"section\"]");
    public SelenideElement semesterSelect = $x("//*[@id=\"semesterSel\"]");
    public SelenideElement searchButton = $x("//button[contains(text(),'Search')]");
    public SelenideElement reportsButton = $x("//*[@id=\"report\"]/a");
    public SelenideElement errorMessage = $x("//div[contains(@class,'error')]");

}
