package com.example.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostmanEchoTest {

    private static final String BASE_URL = "https://postman-echo.com";

    @Test
    void testGetRequest() {
        given()
                .baseUri(BASE_URL)
                .when()
                .get("/get")
                .then()
                .statusCode(200)
                .body("url", equalTo("https://postman-echo.com/get"));
    }

    @Test
    void testPostRequest() {
        String body = "{ \"test\": \"body\" }";
        given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/post")
                .then()
                .statusCode(200)
                .body("json.test", equalTo("body"))
                .body("url", equalTo("https://postman-echo.com/post"));
    }

    @Test
    void testPutRequest() {
        String body = "{ \"test\": \"body\" }";
        given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .put("/put")
                .then()
                .statusCode(200)
                .body("json.test", equalTo("body"))
                .body("url", equalTo("https://postman-echo.com/put"));
    }

    @Test
    void testPatchRequest() {
        String body = "{ \"test\": \"body\" }";
        given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .patch("/patch")
                .then()
                .statusCode(200)
                .body("json.test", equalTo("body"))
                .body("url", equalTo("https://postman-echo.com/patch"));
    }

    @Test
    void testDeleteRequest() {
        String body = "{ \"test\": \"body\" }";
        given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .delete("/delete")
                .then()
                .statusCode(200)
                .body("json.test", equalTo("body"))
                .body("url", equalTo("https://postman-echo.com/delete"));
    }
}