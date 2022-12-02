package editUser;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.user.User;
import site.stellarburgers.user.UserClient;
import site.stellarburgers.user.UserGenerator;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.AllOf.allOf;

public class EditUserTest {

    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getDefaultUser();
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Изменение данных пользователя с авторизацией")
    @Description("Проверяем возможность изменения данных пользователя, после успешной регистрации")
    public void editDataUserWithAuthorization() {
        ValidatableResponse registrationResponse = userClient.registrationUser(user);
        accessToken = registrationResponse.extract().path("accessToken");
        user.setName("Chakki");
        user.setPassword("48151623");
        user.setEmail("mamul@mail.ru");
        ValidatableResponse editResponse = userClient.editUser(accessToken, user);
        editResponse.assertThat().body("success", equalTo(true)).and().statusCode(200);
        String changedUserData = editResponse.extract().path("user").toString();
        String expected1 = user.getName();
        String extected2 = user.getEmail();
        MatcherAssert.assertThat(changedUserData, allOf(containsString(expected1), containsString(extected2)));
    }

    @Test
    @DisplayName("Изменение данных пользователя без авторизации")
    @Description("Проверяем возможность изменения данных пользователя без передачи токена авторизации")
    public void editDataUserWithoutAuthorization() {
        ValidatableResponse registrationResponse = userClient.registrationUser(user);
        accessToken = registrationResponse.extract().path("accessToken");
        user.setName("Chakki");
        user.setPassword("48151623");
        user.setEmail("mamul@mail.ru");
        ValidatableResponse editResponse = userClient.editUser("", user);
        editResponse.assertThat().body("success", equalTo(false)).and().statusCode(401);
        System.out.println(editResponse.extract().path("message").toString());
    }
}

