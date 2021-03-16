package ru.netology.web.data;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import static io.restassured.RestAssured.given;

public class UserGenerator {

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
        private String status;

//        public static AuthInfo user(String status) {
//            Faker faker = new Faker();
//
//            return new AuthInfo(faker.name().username(), faker.internet().password(), status);
//        }
    }

    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static AuthInfo getNewUser(String status) {
        Faker faker = new Faker();
        Gson gson = new Gson();
        AuthInfo user = new AuthInfo(faker.name().username(), faker.internet().password(), status);

        given()
                .spec(requestSpec)
                .body(gson.toJson(user))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
        return user;
    }
}
