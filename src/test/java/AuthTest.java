import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

//    static void setUpAll() {
//        // сам запрос
//        given() // "дано"
//                .spec(DataGenerator.requestSpec) // указываем, какую спецификацию используем
//                .body(new DataGenerator.RegistrationDto("vasya", "password", "active")) // передаём в теле объект, который будет преобразован в JSON
//                .when() // "когда"
//                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
//                .then() // "тогда ожидаем"
//                .statusCode(200); // код 200 OK
//    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id=login] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id=password] input").setValue(DataGenerator.getRandomPassword());
        $("data-test-id=action-login").click();
        // TODO: добавить логику теста, в рамках которого будет выполнена попытка входа в личный кабинет с учётными
        //  данными зарегистрированного активного пользователя, для заполнения полей формы используйте
        //  пользователя registeredUser
    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        var notRegisteredUser = DataGenerator.Registration.getUser("active");
        $("[data-test-id=login] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id=password] input").setValue(DataGenerator.getRandomPassword());
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
        $("[data-test-id=login] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id=password] input").setValue(DataGenerator.getRandomPassword());
        $("[data-test-id=action-login]").click();
        $(withText("Ошибка")).shouldBe(visible);
        $("[data-test-id=error-notification] .notification__content").shouldHave(ownText("Неверно указан логин или пароль"));
// TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет,
// заблокированного пользователя, для заполнения полей формы используйте пользователя blockedUser
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        var wrongLogin = DataGenerator.getRandomLogin();
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
        //  логином, для заполнения поля формы "Логин" используйте переменную wrongLogin,
        //  "Пароль" - пользователя registeredUser
    }

//    @Test
//    @DisplayName("Should get error message if login with wrong password")
//    void shouldGetErrorIfWrongPassword() {
//        var registeredUser = getRegisteredUser("active");
//        var wrongPassword = getRandomPassword();
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
        //  паролем, для заполнения поля формы "Логин" используйте пользователя registeredUser,
        //  "Пароль" - переменную wrongPassword
//    }

//    @Test
//    void shouldDeliveryCardOrder() {
//        open("http://localhost:9999");

//        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
//        String dateOfMeeting = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        $("[data-test-id=date] input").setValue(dateOfMeeting);
//        $("[data-test-id=name] input").setValue("Павел-К");
//        $("[data-test-id=phone] input").setValue("+79238456245");

//        $$("button").find(exactText("Запланировать")).click();
//    }
//
//    @Test
//    void shouldDeliveryCardOrderOnOtherDate() {
//        open("http://localhost:9999");
//        $("[data-test-id=city] input").setValue("Кемерово");
//        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
//        String dateOfMeeting = LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        $("[data-test-id=date] input").setValue(dateOfMeeting);
//        $("[data-test-id=name] input").setValue("Павел-К");
//        $("[data-test-id=phone] input").setValue("+79238456245");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Запланировать")).click();
//        $(withText("Необходимо подтверждение")).shouldBe(visible);
//        $("[data-test-id=replan-notification] .notification__content").shouldHave(ownText("У вас уже запланирована встреча на другую дату. Перепланировать?"));
//        $("[data-test-id=replan-notification] .button").click();
//        $("[data-test-id=success-notification] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + dateOfMeeting));
//    }
}
