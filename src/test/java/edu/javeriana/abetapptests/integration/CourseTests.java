package edu.javeriana.abetapptests.integration;

import edu.javeriana.abetapptests.common.Mapper;
import edu.javeriana.abetapptests.controllers.CourseController;
import edu.javeriana.abetapptests.entities.CourseDTO;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

public class CourseTests {

    private static final CourseController courseController = new CourseController();
    private static final Integer courseNumber = 123;
    private static final String courseName = "Test course";

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

    @AfterAll
    public static void cleanUp() throws IOException, InterruptedException {
        courseController.deleteCourse(courseNumber);
    }
}
