package com.tarasov.test;

import com.tarasov.test.pojo.RegisterUser;
import com.tarasov.test.pojo.User;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class ApiTestsLaunch {
    private final String BASE_URL = "https://reqres.in";

    @Test
    @DisplayName("Get and check single user")
    public void getAndCheckUser() {
        given().
                when()
                .get(BASE_URL +"/api/users/2")
                .then()
                .log().all()
                .statusCode(200)
                .body(
                        "data.id", equalTo(2),
                        "data.first_name", equalTo("Janet"),
                        "data.last_name", equalTo("Weaver"));
    }

    @Test
    @DisplayName("Successful registration")
    public void successfulRegister() {
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        RegisterUser registerUser = new RegisterUser("eve.holt@reqres.in", "pistol");

        given()
                .body(registerUser)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/api/register").then().log().all()
                .statusCode(200)
                .body("id", equalTo(id), "token", equalTo(token));
    }

    @Test
    @DisplayName("Unsuccessful registration")
    public void unSuccessfulRegister() {
        String error = "Missing password";
        RegisterUser registerUser = new RegisterUser("sydney@fife");

        given()
                .body(registerUser)
                .contentType(ContentType.JSON)
                .when()
                .post(BASE_URL + "/api/register").then().log().all()
                .statusCode(400)
                .body("error", is(error));
    }

    @Test
    @DisplayName("Update user")
    public void updateUser() {
        User morpheus = new User("morpheus", "zion resident");

        given()
                .body(morpheus)
                .contentType(ContentType.JSON)
                .when()
                .put(BASE_URL + "/api/users/2").then().log().all()
                .statusCode(200)
                .body("name", is("morpheus"), "job", is("zion resident"),
                        "updatedAt", containsString(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now())));
    }

    @Test
    @DisplayName("Delete")
    public void delete() {
        given()
                .when()
                .delete(BASE_URL +"/api/users/2").then().log().all()
                .statusCode(204);
    }
}
