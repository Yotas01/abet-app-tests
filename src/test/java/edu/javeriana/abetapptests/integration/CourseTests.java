package edu.javeriana.abetapptests.integration;

import edu.javeriana.abetapptests.common.Mapper;
import edu.javeriana.abetapptests.controllers.CourseController;
import edu.javeriana.abetapptests.entities.CourseDTO;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

public class CourseTests {

    private final CourseController courseController = new CourseController();
    private final Integer courseNumber = 1;

    @Test
    void shouldCreateCourse() throws IOException, InterruptedException {
        //Arrange
        CourseDTO course = new CourseDTO();
        course.setNumber(courseNumber);
        course.setName("Test course");
        String json = course.parseToJsonRequiredFields();

        //Act
        HttpResponse<String> response = courseController.createCourse(json);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.statusCode());

        CourseDTO responseBody = (CourseDTO) Mapper.parseToObject(response.body(), CourseDTO.class);
        Assertions.assertEquals(course.getName(), responseBody.getName());
    }

    @AfterEach
    public void cleanUp() throws IOException, InterruptedException {
        courseController.deleteCourse(courseNumber);
    }
}
