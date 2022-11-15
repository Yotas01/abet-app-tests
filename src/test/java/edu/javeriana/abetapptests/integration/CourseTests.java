package edu.javeriana.abetapptests.integration;

import edu.javeriana.abetapptests.common.Mapper;
import edu.javeriana.abetapptests.controllers.CdioController;
import edu.javeriana.abetapptests.controllers.CourseController;
import edu.javeriana.abetapptests.entities.CDIODTO;
import edu.javeriana.abetapptests.entities.CourseDTO;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.http.HttpResponse;

public class CourseTests {

    private static final CourseController courseController = new CourseController();
    private static final CdioController cdioController = new CdioController();
    private static final Integer courseNumber = 123;
    private static final String courseName = "Test course";
    private static final Float cdioNumber = 1.1f;

    @BeforeClass
    public static void setUp() throws IOException, InterruptedException {
        CDIODTO cdio = new CDIODTO();
        cdio.setNumber(cdioNumber);
        cdio.setDescription("This is the description");
        String json = cdio.createJsonRequiredFields();

        cdioController.createCdio(json);
    }

    @Test
    void shouldCreateCourse() throws IOException, InterruptedException {
        //Arrange
        CourseDTO course = new CourseDTO();
        course.setNumber(courseNumber);
        course.setName(courseName);
        String json = course.parseToJsonRequiredFields();

        //Act
        HttpResponse<String> response = courseController.createCourse(json);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.statusCode());

        CourseDTO responseBody = (CourseDTO) Mapper.parseToObject(response.body(), CourseDTO.class);
        Assertions.assertEquals(course.getName(), responseBody.getName());
        Assertions.assertEquals(course.getNumber(), responseBody.getNumber());
    }

    @Test
    void shouldNotCreateCourse() throws IOException, InterruptedException {
        //Arrange
        CourseDTO course = new CourseDTO();
        course.setNumber(courseNumber);
        String json = Mapper.parseToJson(course);

        //Act
        HttpResponse<String> response = courseController.createCourse(json);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }

    @Test
    void shouldGetCourse() throws IOException, InterruptedException {
        //Arrange
        CourseDTO course = new CourseDTO();
        course.setNumber(courseNumber);
        course.setName(courseName);

        //Act
        HttpResponse<String> response = courseController.getCourse(courseNumber);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());

        CourseDTO responseBody = (CourseDTO) Mapper.parseToObject(response.body(), CourseDTO.class);
        Assertions.assertEquals(course.getName(), responseBody.getName());
        Assertions.assertEquals(course.getNumber(), responseBody.getNumber());
    }

    @Test
    void shouldNotDeleteCourse() throws IOException, InterruptedException {
        //Arrange
        Integer wrongNumber = 0;

        //Act
        HttpResponse<String> response = courseController.deleteCourse(wrongNumber);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    }

    @Test
    void shouldAddCdio() throws IOException, InterruptedException {
        //Arrange
        Integer bloomValue = 3;

        //Act
        HttpResponse<String> response = courseController.addCdiotoCourse(courseNumber, cdioNumber, bloomValue);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    void shouldNotAddCdio() throws IOException, InterruptedException {
        //Arrange
        Integer bloomValue = 3;
        Float wrongCdioNumber = 0f;

        //Act
        HttpResponse<String> response = courseController.addCdiotoCourse(courseNumber, wrongCdioNumber, bloomValue);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    }

    @Test
    void shouldRemoveCdio() throws IOException, InterruptedException {
        //Act
        HttpResponse<String> response = courseController.removeCdioFromCourse(courseNumber, cdioNumber);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    void shouldNotRemoveCdio() throws IOException, InterruptedException {
        //Arrange
        Float wrongNumber = 0f;

        //Act
        HttpResponse<String> response = courseController.removeCdioFromCourse(courseNumber, wrongNumber);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    }

    @AfterClass
    public static void cleanUp() throws IOException, InterruptedException {
        courseController.deleteCourse(courseNumber);
        cdioController.deleteCdio(cdioNumber);
    }
}
