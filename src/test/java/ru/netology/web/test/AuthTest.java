package ru.netology.web.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.UserGenerator;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class AuthTest {

    UserGenerator.AuthInfo newActiveUser = UserGenerator.getNewUser("active");
    UserGenerator.AuthInfo newBlockedUser = UserGenerator.getNewUser("blocked");
    Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessAuthIfActiveUser() {
        $("[data-test-id='login'] .input__control").setValue(newActiveUser.getLogin());
        $("[data-test-id='password'] .input__control").setValue(newActiveUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("h2")
                .shouldBe(visible)
                .shouldHave(text("Личный кабинет"));
    }

    @Test
    void shouldGetErrorIfEmptyForm() {
        $("[data-test-id='action-login']").click();
        $("[data-test-id='login'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
        $("[data-test-id='password'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetErrorIfActiveUserAndIncorrectPassword() {
        $("[data-test-id='login'] .input__control").setValue(newActiveUser.getLogin());
        $("[data-test-id='password'] .input__control").setValue(faker.internet().password(1,2));
        $("[data-test-id='action-login']").click();
        $("[data-test-id=error-notification]")
                .shouldBe(visible)
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldGetErrorIfActiveUserAndIncorrectLogin() {
        $("[data-test-id='login'] .input__control").setValue(faker.name().fullName());
        $("[data-test-id='password'] .input__control").setValue(newActiveUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id=error-notification]")
                .shouldBe(visible)
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldGetErrorIfActiveUserAndNoneLogin() {
        $("[data-test-id='password'] .input__control").setValue(newActiveUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='login'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetErrorIfActiveUserAndNonePassword() {
        $("[data-test-id='login'] .input__control").setValue(newActiveUser.getLogin());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='password'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }


    @Test
    void shouldGetErrorIfBlockedUser() {
        $("[data-test-id='login'] .input__control").setValue(newBlockedUser.getLogin());
        $("[data-test-id='password'] .input__control").setValue(newBlockedUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id=error-notification]")
                .shouldBe(visible)
                .shouldHave(text("Ошибка! Пользователь заблокирован"));
    }

    @Test
    void shouldGetErrorIfBlockedUserAndIncorrectLogin() {
        $("[data-test-id='login'] .input__control").setValue(faker.name().fullName());
        $("[data-test-id='password'] .input__control").setValue(newBlockedUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id=error-notification]")
                .shouldBe(visible)
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldGetErrorIfBlockedUserAndIncorrectPassword() {
        $("[data-test-id='login'] .input__control").setValue(newBlockedUser.getLogin());
        $("[data-test-id='password'] .input__control").setValue(faker.internet().password(1,2));
        $("[data-test-id='action-login']").click();
        $("[data-test-id=error-notification]")
                .shouldBe(visible)
                .shouldHave(text("Ошибка! Неверно указан логин или пароль"));
    }

    @Test
    void shouldGetErrorIfBlockedUserAndNoneLogin() {
        $("[data-test-id='password'] .input__control").setValue(newBlockedUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='login'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldGetErrorIfBlockedUserAndNonePassword() {
        $("[data-test-id='login'] .input__control").setValue(newBlockedUser.getLogin());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='password'] .input__sub")
                .shouldBe(visible)
                .shouldHave(text("Поле обязательно для заполнения"));
    }
}
