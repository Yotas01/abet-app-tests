package edu.javeriana.abetapptests.e2e;

import edu.javeriana.abetapptests.controllers.CourseController;
import edu.javeriana.abetapptests.controllers.SectionController;
import edu.javeriana.abetapptests.entities.CourseDTO;
import edu.javeriana.abetapptests.entities.SectionDTO;
import edu.javeriana.abetapptests.exceptions.NotCreated;
import edu.javeriana.abetapptests.pages.SearchPage;
import edu.javeriana.abetapptests.pages.SectionReviewPage;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

public class SearchPageTest extends BaseTest {

    private SearchPage searchPage;
    private static final Integer courseNumber = 1;
    private static final Integer sectionNumber = 1;
    private static final Integer semester = 2110;


    @BeforeAll
    public static void dataSetUp() throws IOException, InterruptedException {

        CourseController courseController = new CourseController();
        CourseDTO course = new CourseDTO();
        course.setNumber(courseNumber);
        course.setName("Test course");
        String json = course.parseToJsonRequiredFields();

        HttpResponse<String> response = courseController.createCourse(json);
        if (response.statusCode() != HttpStatus.SC_CREATED)
            throw new NotCreated("The course with number " + courseNumber + " and body:\n " + json + "\n was not created");

        SectionController sectionController = new SectionController();
        SectionDTO section = new SectionDTO();
        section.setNumber(sectionNumber);
        section.setProfessor("Professor");
        section.setSemester(semester);
        section.setTotalStudents(10);
        String sectionJson = section.parseToJson();

        HttpResponse<String> sectionResponse = sectionController.createSection(courseNumber, sectionJson);
        if (sectionResponse.statusCode() != HttpStatus.SC_CREATED)
            throw new NotCreated("The section with number " + sectionNumber + ", course " + courseNumber +
                    " and body:\n " + sectionJson + "\n was not created");

    }

    @BeforeEach
    void setUp(){
        searchPage = new SearchPage();
        open(searchPage.URL);
    }

    @Test
    void searchNonExistingCourse(){
        //Arrange
        Integer wrongCourseNumber = 0;
        String expectedError = "Error 404 - The course with the number " + wrongCourseNumber + " was not found";
        searchPage.courseField.setValue(String.valueOf(wrongCourseNumber));
        searchPage.sectionField.setValue(String.valueOf(wrongCourseNumber));
        searchPage.semesterSelect.selectOption("2110");

        //Act
        searchPage.searchButton.click();

        //Assert
        searchPage.errorMessage.shouldHave(text(expectedError), Duration.ofSeconds(2));
    }

    @Test
    void searchWithoutFillingCourse(){
        //Arrange
        searchPage.sectionField.setValue("1");
        searchPage.semesterSelect.selectOption("2110");

        //Act
        searchPage.searchButton.click();

        //Assert
        Assertions.assertEquals(searchPage.URL, searchPage.getCurrentUrl());
    }

    @Test
    void searchExistingCourse(){
        //Arrange
        searchPage.courseField.setValue(String.valueOf(courseNumber));
        searchPage.sectionField.setValue(String.valueOf(sectionNumber));
        searchPage.semesterSelect.selectOption(String.valueOf(semester));

        //Act
        searchPage.searchButton.click();
        SectionReviewPage sectionReviewPage = new SectionReviewPage();
        String expectedUrl = String.format(sectionReviewPage.URL, courseNumber, sectionNumber, semester);
        waitForElement(sectionReviewPage.exitButton);

        //Assert
        Assertions.assertEquals(expectedUrl,sectionReviewPage.getCurrentUrl());
    }

    @AfterAll
    public static void cleanUp() throws IOException, InterruptedException {
        SectionController sectionController = new SectionController();
        sectionController.deleteSection(courseNumber, sectionNumber, 2110);

        CourseController courseController = new CourseController();
        courseController.deleteCourse(courseNumber);
    }
}
