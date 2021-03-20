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
    private UserGenerator() {
    }

    private static final Faker faker = new Faker();

    @Value
    public static class AuthInfo {
        String login;
        String password;
        String status;
    }

    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public static AuthInfo getNewUser(String status) {
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

    public static String getInvalidLogin() {
        return faker.name().fullName();
    }

    public static String getInvalidPassword() {
        return faker.internet().password(1, 2);
    }
}
