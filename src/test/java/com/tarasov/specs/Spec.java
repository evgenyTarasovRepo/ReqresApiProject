package com.tarasov.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static com.tarasov.helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class Spec {
    public static RequestSpecification requestSpec = with()
            .filter(withCustomTemplates())
            .baseUri("https://reqres.in")
            .basePath("/api")
            .contentType(JSON)
            .log().headers()
            .log().body();

    public static ResponseSpecification responseSpecificationWithCode200 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification responseSpecificationWithCode400 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(400)
            .build();

    public static ResponseSpecification responseSpecificationWithCode204 = new ResponseSpecBuilder()
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .expectStatusCode(204)
            .build();
}




