package createUser;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.user.User;
import site.stellarburgers.user.UserClient;
import site.stellarburgers.user.UserGenerator;

import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Создание пользователя")
public class CreateUserTest {

    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getDefaultUser();
    }


    @Test
    @DisplayName("Создание уникального пользователя")
    @Description("Проверяем возможность создания в системе нового уникального пользователя")
    public void createUser() {
        ValidatableResponse responseRegistration = userClient.registrationUser(user);
        responseRegistration.assertThat().body("success", equalTo(true)).and().statusCode(200);
        accessToken = responseRegistration.extract().path("accessToken");
        System.out.println("Пользователь успено создан.");
        System.out.println("Токен для дальнейших действий:" + "\n" + accessToken);
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Повторная регистрация уже созданного пользователя")
    @Description("Проверяем, что в системе невозможно зарегистрировать двух пользователей с одинаковыми данными")
    public void creatingAnAlreadyRegisteredUser() {
        ValidatableResponse firstRegistrationResponse = userClient.registrationUser(user);
        firstRegistrationResponse.assertThat().body("success", equalTo(true)).and().statusCode(200);
        accessToken = firstRegistrationResponse.extract().path("accessToken");
        System.out.println("Пользователь успено создан.");
        ValidatableResponse secondRegistrationResponse = userClient.registrationUser(user);
        secondRegistrationResponse.assertThat().body("success", equalTo(false)).and().statusCode(403);
        System.out.println(secondRegistrationResponse.extract().path("message").toString());
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Создание пользователя без ввода данных в одно из полей")
    @Description("Проверяем возможность создания нового пользователя без ввода данных в поле 'name'")
    public void creatingUserWithoutEnteringDataInFieldName() {
        user.setName(null);
        ValidatableResponse responseRegistration = userClient.registrationUser(user);
        responseRegistration.assertThat().body("success", equalTo(false)).and().statusCode(403);
        System.out.println("Пользователь не создан:" + "\n" + responseRegistration.extract().path("message").toString());
    }

    @Test
    @DisplayName("Создание пользователя без ввода данных в одно из полей")
    @Description("Проверяем возможность создания нового пользователя без ввода данных в поле 'email'")
    public void creatingUserWithoutEnteringDataInFieldEmail() {
        user.setEmail(null);
        ValidatableResponse responseRegistration = userClient.registrationUser(user);
        responseRegistration.assertThat().body("success", equalTo(false)).and().statusCode(403);
        System.out.println("Пользователь не создан " + "\n" + responseRegistration.extract().path("message").toString());
    }

    @Test
    @DisplayName("Создание пользователя без ввода данных в одно из полей")
    @Description("Проверяем возможность создания нового пользователя без ввода данных в поле 'password'")
    public void creatingUserWithoutEnteringDataInFieldPassword() {
        user.setPassword(null);
        ValidatableResponse responseRegistration = userClient.registrationUser(user);
        responseRegistration.assertThat().body("success", equalTo(false)).and().statusCode(403);
        System.out.println("Пользователь не создан " + "\n" + responseRegistration.extract().path("message").toString());
    }

}
