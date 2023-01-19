package getOrders;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
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
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;

@DisplayName("Получение списка заказов")
public class GetingOrdersTest {

    public UserClient userClient;
    public User user;
    public OrderClient orderClient;
    public Order order;
    public String accessToken;

    @Before
    public void setUp() {
        userClient = new UserClient();
        user = UserGenerator.getDefaultUser();
        orderClient = new OrderClient();
        order = OrderGenerator.getDefaultOrder();
    }

    @After
    public void deleteUser() {
        userClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Получение заказов авторизованного пользователя")
    @Description("Проверяем возможность получения заказа у пользователя успешно прошедшего регистрацию/авторизацию")
    public void getOrdersUserWithAuthorization() {
        ValidatableResponse responseRegistration = userClient.registrationUser(user);
        accessToken = responseRegistration.extract().path("accessToken");
        orderClient.createNewOrderWithAuthorization(accessToken, order);
        ValidatableResponse responseOrder = orderClient.getUserOrder(accessToken);
        responseOrder.assertThat().body("success", equalTo(true)).and().statusCode(200);
        System.out.println("Ваш номер заказа:" + "\n" + responseOrder.extract().path("orders.number").toString());
    }

    @Test
    @DisplayName("Получение заказов неавторизированного пользователя")
    @Description("Проверяем возможность получения заказа у неавторизированного в системе пользователя")
    public void getOrdersUserWithoutAuthorization() {
        ValidatableResponse responseRegistration = userClient.registrationUser(user);
        accessToken = responseRegistration.extract().path("accessToken");
        orderClient.createNewOrderWithAuthorization(accessToken, order);
        ValidatableResponse responseOrder = orderClient.getUserOrder("");
        responseOrder.assertThat().body("success", equalTo(false)).and().statusCode(401);
        System.out.println(responseOrder.extract().path("message").toString());
    }
}
