package edu.javeriana.abetapptests.integration;

import edu.javeriana.abetapptests.common.Mapper;
import edu.javeriana.abetapptests.controllers.CourseController;
import edu.javeriana.abetapptests.controllers.RaeController;
import edu.javeriana.abetapptests.entities.CourseDTO;
import edu.javeriana.abetapptests.entities.RAEDTO;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.io.IOException;
import java.net.http.HttpResponse;

@TestMethodOrder(OrderAnnotation.class)
public class RaeTests {

    private static final CourseController courseController = new CourseController();
    private static final RaeController raeController = new RaeController();
    private static final Integer courseNumber = 123;
    private static Long raeId;

    @BeforeAll
    public static void setUp() throws IOException, InterruptedException {
        String courseName = "Test course";
        CourseDTO course = new CourseDTO();
        course.setNumber(courseNumber);
        course.setName(courseName);
        String json = course.parseToJsonRequiredFields();

        courseController.createCourse(json);
    }

    @Test
    @Order(1)
    void shouldCreateRae() throws IOException, InterruptedException {
        //Arrange
        String description = "This is a test RAE";
        RAEDTO rae = new RAEDTO();
        rae.setDescription(description);
        String json = rae.createJsonRequiredFields();

        //Act
        HttpResponse<String> response = raeController.createRae(courseNumber, json);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.statusCode());
        RAEDTO responseBody = (RAEDTO) Mapper.parseToObject(response.body(), RAEDTO.class);
        Assertions.assertEquals(rae.getDescription(), responseBody.getDescription());
        setRaeId(responseBody.getRaeId());
    }

    @Test
    @Order(2)
    void shouldGetRae() throws IOException, InterruptedException{
        //Arrange
        String description = "This is a test RAE";
        RAEDTO rae = new RAEDTO();
        rae.setDescription(description);

        //Act
        HttpResponse<String> response = raeController.getRae(courseNumber, raeId);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        RAEDTO responseBody = (RAEDTO) Mapper.parseToObject(response.body(), RAEDTO.class);
        Assertions.assertEquals(rae.getDescription(), responseBody.getDescription());
        Assertions.assertEquals(raeId, responseBody.getRaeId());
    }

    @Test
    @Order(3)
    void shouldDeleteRae() throws IOException, InterruptedException{
        //Arrange
        String description = "This is a test RAE";
        RAEDTO rae = new RAEDTO();
        rae.setDescription(description);

        //Act
        HttpResponse<String> response = raeController.deleteRae(courseNumber, raeId);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
    }

    @Test
    @Order(4)
    void shouldNotCreateRae() throws IOException, InterruptedException {
        //Act
        HttpResponse<String> response = raeController.createRae(courseNumber, "");

        //Assert
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }

    @Test
    @Order(5)
    void shouldNotCreateRaeNoCourse() throws IOException, InterruptedException {
        //Arrange
        RAEDTO rae = new RAEDTO();
        rae.setDescription("Test RAE");
        String json = rae.createJsonRequiredFields();
        Integer wrongNumber = 0;

        //Act
        HttpResponse<String> response = raeController.createRae(wrongNumber, json);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    }

    @AfterAll
    public static void cleanUp() throws IOException, InterruptedException {
        courseController.deleteCourse(courseNumber);
    }

    public void setRaeId(Long raeId) {
        RaeTests.raeId = raeId;
    }
}
