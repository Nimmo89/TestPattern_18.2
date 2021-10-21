import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import java.util.Locale;
import static io.restassured.RestAssured.*;

public class DataGenerator {
    static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {}

    private static void sendRequest(RegistrationDto user) {
        // сам запрос
        given() // "дано"
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(new RegistrationDto("vasya", "password", "active")) // передаём в теле объект, который будет преобразован в JSON
                .when() // "когда"
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then() // "тогда ожидаем"
                .statusCode(200); // код 200 OK
        // TODO: отправить запрос на указанный в требованиях path, передав в body запроса объект user
        //  и не забудьте передать подготовленную спецификацию requestSpec.
        //  Пример реализации метода показан в условии к задаче.
    }

    public static String getRandomLogin() {
        String login = faker.name().username();
        //  добавить логику для объявления переменной login и задания её значения, для генерации
        //  случайного логина используйте faker
        return login;
    }

    public static String getRandomPassword() {
        String password = faker.internet().password();
        // добавить логику для объявления переменной password и задания её значения, для генерации
        //  случайного пароля используйте faker
        return password;
    }

    public static class Registration {
        private Registration() {
        }

        public static String getUser(String status) {
            String user = getRandomLogin();
            // TODO: создать пользователя user используя методы getRandomLogin(), getRandomPassword() и параметр status
            return user;
        }

        public static String getRegisteredUser(String status) {
            String registeredUser = getUser(status);
            // объявить переменную registeredUser и присвоить ей значение возвращённое getUser(status).
            // Послать запрос на регистрацию пользователя с помощью вызова sendRequest(registeredUser)
            return registeredUser;
        }
    }

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }
}