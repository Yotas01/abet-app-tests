package edu.javeriana.abetapptests;

import edu.javeriana.abetapptests.pages.SearchPage;
import edu.javeriana.abetapptests.pages.SectionReviewPage;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchPageTest extends BaseTest{
    SearchPage searchPage = new SearchPage();

    @BeforeEach
    public void setUp() {
        open(searchPage.URL);
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
        Assertions.assertEquals(searchPage.URL, searchPage.getCurrentUrl());
    }

    @Test
    public void searchExistingCourse(){
        //Arrange
        String course = "4085", section = "1", semester = "2110";
        searchPage.courseField.setValue(course);
        searchPage.sectionField.setValue(section);
        searchPage.semesterSelect.selectOption(semester);

        //Act
        searchPage.searchButton.click();
        SectionReviewPage sectionReviewPage = new SectionReviewPage();
        String expectedUrl = String.format(sectionReviewPage.URL, course, section, semester);
        waitForElement(sectionReviewPage.exitButton);

        //Assert
        Assertions.assertEquals(expectedUrl,sectionReviewPage.getCurrentUrl());
    }

}
