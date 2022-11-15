package edu.javeriana.abetapptests.integration;

import edu.javeriana.abetapptests.common.Mapper;
import edu.javeriana.abetapptests.controllers.CourseController;
import edu.javeriana.abetapptests.controllers.SectionController;
import edu.javeriana.abetapptests.entities.CourseDTO;
import edu.javeriana.abetapptests.entities.SectionDTO;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.net.http.HttpResponse;

public class SectionTests {

    private static final CourseController courseController = new CourseController();
    private static final SectionController sectionController = new SectionController();
    private static final Integer courseNumber = 123;
    private static final String courseName = "Test course";
    private static final Integer sectionNumber = 423;
    private static final String professor = "Daniel Monsalve";
    private static final Integer semester = 2230;

    @BeforeClass
    public static void setUp() throws IOException, InterruptedException {
        CourseDTO course = new CourseDTO();
        course.setNumber(courseNumber);
        course.setName(courseName);
        String json = course.parseToJsonRequiredFields();

        courseController.createCourse(json);
    }

    @Test
    public void shouldCreateSection() throws IOException, InterruptedException{
        //Arrange
        SectionDTO section = new SectionDTO();
        section.setNumber(sectionNumber);
        section.setSemester(semester);
        section.setProfessor(professor);
        section.setTotalStudents(10);
        String json = section.parseToJson();

        //Act
        HttpResponse<String> response = sectionController.createSection(courseNumber,json);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.statusCode());
        SectionDTO responseBody = (SectionDTO) Mapper.parseToObject(response.body(), SectionDTO.class);
        Assertions.assertEquals(section.getProfessor(), responseBody.getProfessor());
        Assertions.assertEquals(section.getNumber(), responseBody.getNumber());
        Assertions.assertEquals(section.getTotalStudents(), responseBody.getTotalStudents());
        Assertions.assertEquals(section.getSemester(), responseBody.getSemester());
        Assertions.assertTrue(responseBody.getCourse().containsKey(courseNumber));
    }

    @Test
    public void shouldGetSection() throws IOException, InterruptedException{
        //Arrange
        SectionDTO section = new SectionDTO();
        section.setNumber(sectionNumber);
        section.setSemester(semester);
        section.setProfessor(professor);
        section.setTotalStudents(10);

        //Act
        HttpResponse<String> response = sectionController.getSection(courseNumber,sectionNumber, semester);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        SectionDTO responseBody = (SectionDTO) Mapper.parseToObject(response.body(), SectionDTO.class);
        Assertions.assertEquals(section.getProfessor(), responseBody.getProfessor());
        Assertions.assertEquals(section.getNumber(), responseBody.getNumber());
        Assertions.assertEquals(section.getTotalStudents(), responseBody.getTotalStudents());
        Assertions.assertEquals(section.getSemester(), responseBody.getSemester());
        Assertions.assertTrue(responseBody.getCourse().containsKey(courseNumber));
    }

    @AfterClass
    public static void cleanUp() throws IOException, InterruptedException {
        courseController.deleteCourse(courseNumber);
    }
}
