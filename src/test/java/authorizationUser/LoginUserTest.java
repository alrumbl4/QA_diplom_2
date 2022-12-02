package authorizationUser;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.user.Credentials;
import site.stellarburgers.user.User;
import site.stellarburgers.user.UserClient;
import site.stellarburgers.user.UserGenerator;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginUserTest {

    private UserClient userClient;
    private User user;
    private Credentials credentials;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getDefaultUser();
        userClient.registrationUser(user);
        credentials = Credentials.from(user);
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Авторизация под существующим пользователем")
    @Description("Проверяем возможность авторизации зарегистрированного ранее пользователя")
    public void authorizationForRegisteredUser() {
        ValidatableResponse responseAuthorization = userClient.authorizationUser(credentials);
        responseAuthorization.assertThat().body("success", equalTo(true)).and().statusCode(200);
        accessToken = responseAuthorization.extract().path("accessToken");
        System.out.println("Успешная авторизация пользователя с веденными данными: " + "\n" + responseAuthorization.extract().path("user").toString());
    }

    @Test
    @DisplayName("Авторизация с неверными данными")
    @Description("Проверяем возможность авторизоваться используя неверный 'email'")
    public void authorizationWithInvalidEmail() {
        ValidatableResponse responseAuthorization = userClient.authorizationUser(credentials);
        accessToken = responseAuthorization.extract().path("accessToken");
        credentials.setEmail("qwerty12@mail.ru");
        ValidatableResponse responseAuthorizationError = userClient.authorizationUser(credentials);
        responseAuthorizationError.assertThat().body("success", equalTo(false)).and().statusCode(401);
        System.out.println("Авторизация не выполнена:" + "\n" + responseAuthorizationError.extract().path("message"));
    }
}
