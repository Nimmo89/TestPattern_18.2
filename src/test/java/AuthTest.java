import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
//import static DataGenerator.Registration.getRegisteredUser;
import static DataGenerator.Registration.getUser;
//import static DataGenerator.getRandomLogin;
//import static DataGenerator.getRandomPassword;
import static io.restassured.RestAssured.given;

class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    static void setUpAll() {
        // сам запрос
        given() // "дано"
                .spec(DataGenerator.requestSpec) // указываем, какую спецификацию используем
                .body(new DataGenerator.RegistrationDto("vasya", "password", "active")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        // TODO: добавить логику теста, в рамках которого будет выполнена попытка входа в личный кабинет с учётными
        //  данными зарегистрированного активного пользователя, для заполнения полей формы используйте
        //  пользователя registeredUser
    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        var notRegisteredUser = getUser("active");
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет
        //  незарегистрированного пользователя, для заполнения полей формы используйте пользователя notRegisteredUser
    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        var blockedUser = getRegisteredUser("blocked");
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет,
        //  заблокированного пользователя, для заполнения полей формы используйте пользователя blockedUser
    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getRandomLogin();
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
        //  логином, для заполнения поля формы "Логин" используйте переменную wrongLogin,
        //  "Пароль" - пользователя registeredUser
    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getRandomPassword();
        // TODO: добавить логику теста в рамках которого будет выполнена попытка входа в личный кабинет с неверным
        //  паролем, для заполнения поля формы "Логин" используйте пользователя registeredUser,
        //  "Пароль" - переменную wrongPassword
    }

//    @Test
//    void shouldDeliveryCardOrder() {
//        open("http://localhost:9999");
//        $("[data-test-id=city] input").setValue("Кемерово");
//        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
//        String dateOfMeeting = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
//        $("[data-test-id=date] input").setValue(dateOfMeeting);
//        $("[data-test-id=name] input").setValue("Павел-К");
//        $("[data-test-id=phone] input").setValue("+79238456245");
//        $("[data-test-id=agreement]").click();
//        $$("button").find(exactText("Запланировать")).click();
//        $(withText("Успешно!")).shouldBe(visible);
//        $("[data-test-id=success-notification] .notification__content").shouldHave(exactText("Встреча успешно запланирована на " + dateOfMeeting));
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
