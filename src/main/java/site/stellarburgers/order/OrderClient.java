package site.stellarburgers.order;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import site.stellarburgers.Client;

import static io.restassured.RestAssured.given;

public class OrderClient extends Client {

    protected static final String INGREDIENTS_PATH = "api/ingredients";
    protected static final String CREATE_AND_GET_ORDER_PATH = "api/orders";


    @Step("Получение данных об ингредиентах")
    public ValidatableResponse getDataIngredients() {
        return given()
                .spec(getSpec())
                .when()
                .get(INGREDIENTS_PATH)
                .then();
    }

    @Step("Создание заказа c авторизацией")
    public ValidatableResponse createNewOrderWithAuthorization(String accessToken, Order order) {
        return given()
                .spec(getSpec())
                .header("authorization", accessToken)
                .body(order)
                .when()
                .post(CREATE_AND_GET_ORDER_PATH)
                .then();
    }

    @Step("Создание заказа без авторизации")
    public ValidatableResponse createNewOrderWithoutAuthorization(Order order) {
        return given()
                .spec(getSpec())
                .body(order)
                .when()
                .post(CREATE_AND_GET_ORDER_PATH)
                .then();
    }

    @Step("Получение заказов пользователя")
    public ValidatableResponse getUserOrder(String accessToken) {
        return given()
                .spec(getSpec())
                .header("authorization", accessToken)
                .when()
                .get(CREATE_AND_GET_ORDER_PATH)
                .then();
    }
}
