package edu.javeriana.abetapptests.integration;

import edu.javeriana.abetapptests.common.Mapper;
import edu.javeriana.abetapptests.controllers.CdioController;
import edu.javeriana.abetapptests.entities.CDIODTO;
import org.apache.hc.core5.http.HttpStatus;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.http.HttpResponse;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CdioTests {

    private static final CdioController cdioController = new CdioController();
    private static final Float cdioNumber = 1.1f;
    private static final String description = "This is a test CDIO";

    @Test
    @Order(1)
    void createCdio() throws IOException, InterruptedException {
        //Arrange
        CDIODTO cdio = new CDIODTO();
        cdio.setDescription(description);
        cdio.setNumber(cdioNumber);
        String json = cdio.createJsonRequiredFields();

        //Act
        HttpResponse<String> response = cdioController.createCdio(json);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_CREATED, response.statusCode());
        CDIODTO responseBody = (CDIODTO) Mapper.parseToObject(response.body(), CDIODTO.class);
        Assertions.assertEquals(cdio.getNumber(), responseBody.getNumber());
        Assertions.assertEquals(cdio.getDescription(), responseBody.getDescription());
    }

    @Test
    @Order(2)
    void getCdio() throws IOException, InterruptedException {
        //Arrange
        CDIODTO cdio = new CDIODTO();
        cdio.setDescription(description);
        cdio.setNumber(cdioNumber);

        //Act
        HttpResponse<String> response = cdioController.getCdio(cdioNumber);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode());
        CDIODTO responseBody = (CDIODTO) Mapper.parseToObject(response.body(), CDIODTO.class);
        Assertions.assertEquals(cdio.getNumber(), responseBody.getNumber());
        Assertions.assertEquals(cdio.getDescription(), responseBody.getDescription());
    }

    @Test
    @Order(3)
    void shouldNotCreateCdio() throws IOException, InterruptedException {
        //Arrange

        //Act
        HttpResponse<String> response = cdioController.createCdio("");

        //Assert
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, response.statusCode());
    }

    @Test
    @Order(4)
    void deleteCdio() throws IOException, InterruptedException {
        //Arrange
        CDIODTO cdio = new CDIODTO();
        cdio.setDescription(description);
        cdio.setNumber(cdioNumber);

        //Act
        HttpResponse<String> response = cdioController.deleteCdio(cdioNumber);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.statusCode());
    }

    @Test
    @Order(5)
    void shouldNotGetCdio() throws IOException, InterruptedException {
        //Arrange
        Float wrongNumber = 0f;

        //Act
        HttpResponse<String> response = cdioController.getCdio(wrongNumber);

        //Assert
        Assertions.assertEquals(HttpStatus.SC_NOT_FOUND, response.statusCode());
    }
}
