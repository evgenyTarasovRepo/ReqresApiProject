package com.tarasov.test;

import com.tarasov.helpers.HelperClass;
import com.tarasov.models.requestpojo.RegisterUser;
import com.tarasov.models.requestpojo.User;
import com.tarasov.models.responsepojo.RegisterUserSuccessResponse;
import com.tarasov.models.responsepojo.SingleUserResponse;
import com.tarasov.models.responsepojo.UnsuccessfulRegResponse;
import com.tarasov.models.responsepojo.UpdateUserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static com.tarasov.specs.Spec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ApiTestsLaunch {

    @Test
    @DisplayName("Get single user")
    @Tag("api_test")
    public void getAndCheckUser() {
        step("Get and check single use", () -> {
            SingleUserResponse userResponse = given().
                    spec(requestSpec)
                    .when()
                    .get("/users/2")
                    .then()
                    .spec(responseSpecificationWithCode200)
                    .extract()
                    .jsonPath()
                    .getObject("data", SingleUserResponse.class);


            assertEquals(userResponse.getId(), 2);
            assertEquals(userResponse.getEmail(), "janet.weaver@reqres.in");
            assertEquals(userResponse.getFirst_name(), "Janet");
            assertEquals(userResponse.getLast_name(), "Weaver");
        });
    }

    @Test
    @DisplayName("Successful registration")
    @Tag("api_test")
    public void successfulRegister() {
        step("Check successful registration", () -> {
            Integer id = 4;
            String token = "QpwL5tke4Pnpja7X4";

            RegisterUser registerUser = new RegisterUser("eve.holt@reqres.in", "pistol");

            RegisterUserSuccessResponse registerUserResponse = given()
                    .spec(requestSpec)
                    .body(registerUser)
                    .when()
                    .post("/register")
                    .then()
                    .spec(responseSpecificationWithCode200)
                    .extract().as(RegisterUserSuccessResponse.class);

            assertNotNull(registerUserResponse.getId());
            assertNotNull(registerUserResponse.getToken());

            assertEquals(id, registerUserResponse.getId());
            assertEquals(token, registerUserResponse.getToken());
        });
    }

    @Test
    @DisplayName("Unsuccessful registration")
    @Tag("api_test")
    public void unSuccessfulRegister() {
        step("Check error message", () -> {
            String error = "Missing password";
            RegisterUser registerUser = new RegisterUser("sydney@fife");

            UnsuccessfulRegResponse unsuccessfulRegResponse = given()
                    .spec(requestSpec)
                    .body(registerUser)
                    .when()
                    .post("/register")
                    .then()
                    .spec(responseSpecificationWithCode400)
                    .extract()
                    .as(UnsuccessfulRegResponse.class);

            assertEquals(error, unsuccessfulRegResponse.getError());
        });
    }

    @Test
    @DisplayName("Update user")
    @Tag("api_test")
    public void updateUser() {
        step("Update user and check name, job and updated date", () -> {
            User morpheus = new User("morpheus", "zion resident");

            UpdateUserResponse updateUserResponse = given()
                    .spec(requestSpec)
                    .body(morpheus)
                    .when()
                    .put("/users/2")
                    .then()
                    .spec(responseSpecificationWithCode200)
                    .extract().as(UpdateUserResponse.class);

            assertEquals(updateUserResponse.getName(), morpheus.getName());
            assertEquals(updateUserResponse.getJob(), morpheus.getJob());
            assertEquals(HelperClass.convertDateToString(updateUserResponse.getUpdatedAt()), HelperClass.convertDateToString(new Date()));

        });
    }

    @Test
    @DisplayName("Delete")
    @Tag("api_test")
    public void delete() {
        step("Delete user", () -> {
            given()
                    .spec(requestSpec)
                    .when()
                    .delete("/users/2")
                    .then()
                    .spec(responseSpecificationWithCode204);
        });

    }
}
