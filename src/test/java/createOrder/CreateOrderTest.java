package createOrder;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.order.Order;
import site.stellarburgers.order.OrderClient;
import site.stellarburgers.order.OrderGenerator;
import site.stellarburgers.user.User;
import site.stellarburgers.user.UserClient;
import site.stellarburgers.user.UserGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;


public class CreateOrderTest {

    private UserClient userClient;
    private User user;
    private OrderClient orderClient;
    private Order order;
    private String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getDefaultUser();
        orderClient = new OrderClient();
        order = OrderGenerator.getDefaultOrder();
    }


    @Test
    @DisplayName("Создание заказа с авторизацией")
    @Description("Проверяем возможность создать заказ с пользователем прошедшим регистрацию/авторизацию")
    public void createNewOrderWithAuthorization() {
        ValidatableResponse responseRegistration = userClient.registrationUser(user);
        accessToken = responseRegistration.extract().path("accessToken");
        ValidatableResponse responseCreateOrder = orderClient.createNewOrderWithAuthorization(accessToken, order);
        responseCreateOrder.assertThat().body("success", equalTo(true)).and().statusCode(200);
        System.out.println("Номер вашего заказа:" + "\n" + responseCreateOrder.extract().path("order.number").toString());
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    @Description("Проверяем возможность создать заказ без авторизации в системе")
    public void createNewOrderWithoutAuthorization() {
        ValidatableResponse responseCreateOrder = orderClient.createNewOrderWithoutAuthorization(order);
        responseCreateOrder.assertThat().body("success", equalTo(true)).and().statusCode(200);
        System.out.println("Номер вашего заказа:" + "\n" + responseCreateOrder.extract().path("order.number").toString());
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Проверяем что в системе отсутствует возможность создать заказ без добваления ингредиентов")
    public void createOrderWithoutIngredients() {
        order = OrderGenerator.emptyList();
        ValidatableResponse responseCreateOrder = orderClient.createNewOrderWithoutAuthorization(order);
        responseCreateOrder.assertThat().body("success", equalTo(false)).and().statusCode(400);
        System.out.println("Заказ не создан:" + "\n" + responseCreateOrder.extract().path("message"));
    }

    @Test
    @DisplayName("Cоздание заказа с неверным хешем ингредиентов")
    @Description("Проверяем возможность создать заказ, если хотя бы у одного из ингредиентов неверно указан хеш код")
    public void createOrderWithHeshError() {
        List<String> errorHeshList = order.getIngredients();
        errorHeshList.set(1, RandomStringUtils.randomAlphanumeric(24));
        ValidatableResponse responseCreateOrder = orderClient.createNewOrderWithoutAuthorization(order);
        responseCreateOrder.assertThat().statusCode(500);
    }
}



