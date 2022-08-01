package edu.javeriana.abetapptests.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class SectionReviewPage extends BasePage{

    public String URL = baseUrl + "/review/%s/%s/%s";
    public SelenideElement sendReviewButton = $x("//button[contains(text(),'Send course review')]");
    public SelenideElement exitButton = $x("//button[contains(text(),'Exit without saving')]");
    public SelenideElement saveAndFinishLaterButton = $x("//button[contains(text(),'Save and finish later')]");

}
