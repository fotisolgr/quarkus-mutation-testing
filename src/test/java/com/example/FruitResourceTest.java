package com.example;

import com.example.model.Fruit;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response.Status;

import static io.restassured.RestAssured.given;

@QuarkusTest
class FruitResourceTest {

    @InjectMock
    FruitService fruitService;

    @BeforeEach
    void enableRestAssuredLoggingIfValidationFails() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void shouldReturn200WhenGet() {
        given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(Status.OK.getStatusCode());
    }

    @Test
    void shouldReturn500UponExceptionThrownWhenGet() {
        final var dummyRuntimeException = new RuntimeException("dummyRuntimeException");

        Mockito.doThrow(dummyRuntimeException).when(fruitService).getAllFruits();

        given()
                .when()
                .get("/fruits")
                .then()
                .statusCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }

    @Test
    void shouldReturn201UponValidRequestWhenPost() {
        final var fruit = new Fruit("banana", "It's yellow");

        given()
                .contentType(ContentType.JSON)
                .body(fruit)
                .when()
                .post("/fruits")
                .then()
                .statusCode(Status.CREATED.getStatusCode());
    }

    @Test
    void shouldReturn400UponInvalidRequestWhenPost() {
        final var x = 2;
        given()
                .contentType(ContentType.JSON)
                .body(x)
                .when()
                .post("/fruits")
                .then()
                .statusCode(Status.BAD_REQUEST.getStatusCode());
    }

    @Test
    void shouldReturn500UponExceptionThrownWhenPost() {
        final var dummyRuntimeException = new RuntimeException("dummyRuntimeException");

        Mockito.doThrow(dummyRuntimeException).when(fruitService).addFruit(Mockito.any());

        given()
                .contentType(ContentType.JSON)
                .when()
                .post("/fruits")
                .then()
                .statusCode(Status.INTERNAL_SERVER_ERROR.getStatusCode());
    }


}