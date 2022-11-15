package edu.javeriana.abetapptests.integration;

import com.google.common.reflect.TypeToken;
import edu.javeriana.abetapptests.common.Mapper;
import edu.javeriana.abetapptests.controllers.CourseController;
import edu.javeriana.abetapptests.controllers.SectionController;
import edu.javeriana.abetapptests.entities.CourseDTO;
import edu.javeriana.abetapptests.entities.SectionDTO;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class SectionTests {

    private static final CourseController courseController = new CourseController();
    private static final SectionController sectionController = new SectionController();
    private static final Integer courseNumber = 123;
    private static final String courseName = "Test course";
    private static final Integer sectionNumber = 423;
    private static final String professor = "Daniel Monsalve";
    private static final Integer semester = 2230;

    @BeforeAll
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
    public void shouldNotCreateSection() throws IOException, InterruptedException{
        //Arrange
        SectionDTO section = new SectionDTO();
        section.setNumber(sectionNumber);
        section.setSemester(semester);
        section.setProfessor(professor);
        section.setTotalStudents(10);
        Integer wrongCourse = 0;
        String json = section.parseToJson();

        //Act
        HttpResponse<String> response = sectionController.createSection(wrongCourse,json);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
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

    @Test
    public void shouldNotGetSection() throws IOException, InterruptedException{
        //Act
        HttpResponse<String> response = sectionController.getSection(courseNumber,0, semester);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    }

    @Test
    public void shouldNotDeleteSection() throws IOException, InterruptedException{
        //Act
        HttpResponse<String> response1 = sectionController.deleteSection(courseNumber,sectionNumber , 0);
        HttpResponse<String> response2 = sectionController.deleteSection(courseNumber,0 , semester);
        HttpResponse<String> response3 = sectionController.deleteSection(0,sectionNumber , semester);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response1.statusCode());
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response2.statusCode());
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response3.statusCode());
    }

    @AfterAll
    public static void cleanUp() throws IOException, InterruptedException {
        courseController.deleteCourse(courseNumber);
    }
}
