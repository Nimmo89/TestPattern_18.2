import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

class AuthTest {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("[data-test-id=password] input").setValue(registeredUser.getPassword());
        $("[data-test-id=action-login]").click();
        $("h2.heading").shouldHave(exactText("  Личный кабинет"));
        // добавить логику теста, в рамках которого будет выполнена попытка входа в личный кабинет с учётными
        // данными зарегистрированного активного пользователя, для заполнения полей формы используйте
        // пользователя registeredUser
    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        var notRegisteredUser = DataGenerator.Registration.getUser("active");
        $("[data-test-id=login] input").setValue(notRegisteredUser.getLogin());
        $("[data-test-id=password] input").setValue(notRegisteredUser.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Ошибка")).shouldBe(visible);
        $("[data-test-id=error-notification] .notification__content").shouldHave(ownText("Неверно указан логин или пароль"));
// добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет
// незарегистрированного пользователя, для заполнения полей формы используйте пользователя notRegisteredUser
    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        var blockedUser = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id=login] input").setValue(blockedUser.getStatus());
        $("[data-test-id=password] input").setValue(DataGenerator.getRandomPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Ошибка")).shouldBe(visible);
        $("[data-test-id=error-notification] .notification__content").shouldHave(ownText("Неверно указан логин или пароль"));
// добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет,
// заблокированного пользователя, для заполнения полей формы используйте пользователя blockedUser
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        var wrongLogin = DataGenerator.getRandomLogin();
        $("[data-test-id=login] input").setValue(wrongLogin);
        $("[data-test-id=password] input").setValue(registeredUser.getPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Ошибка")).shouldBe(visible);
        $("[data-test-id=error-notification] .notification__content").shouldHave(ownText("Неверно указан логин или пароль"));
        // добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
        // логином, для заполнения поля формы "Логин" используйте переменную wrongLogin,
        // "Пароль" - пользователя registeredUser
    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        var wrongPassword = DataGenerator.getRandomPassword();
        $("[data-test-id=login] input").setValue(registeredUser.getLogin());
        $("[data-test-id=password] input").setValue(wrongPassword);
        $("[data-test-id=action-login]").click();
        $(withText("Ошибка")).shouldBe(visible);
        $("[data-test-id=error-notification] .notification__content").shouldHave(ownText("Неверно указан логин или пароль"));
//  добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
//  паролем, для заполнения поля формы "Логин" используйте пользователя registeredUser,
//  "Пароль" - переменную wrongPassword
    }
}
