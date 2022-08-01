package edu.javeriana.abetapptests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import edu.javeriana.abetapptests.pages.SearchPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchPageTest {
    SearchPage searchPage = new SearchPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open(searchPage.Url);
    }

    @Test
    public void searchNonExistingCourse(){
        //Arrange
        String expectedError = "Error 404 - The course with the number 123456 was not found";
        searchPage.courseField.setValue("123456");
        searchPage.sectionField.setValue("1");
        searchPage.semesterSelect.selectOption("2110");

        //Act
        searchPage.searchButton.click();

        //Assert
        searchPage.errorMessage.shouldHave(text(expectedError), Duration.ofSeconds(2));
    }

    @Test
    public void searchWithoutFillingCourse(){
        //Arrange
        searchPage.sectionField.setValue("1");
        searchPage.semesterSelect.selectOption("2110");

        //Act
        searchPage.searchButton.click();

        //Assert
        Assertions.assertEquals(searchPage.Url, searchPage.getCurrentUrl());
    }

}
