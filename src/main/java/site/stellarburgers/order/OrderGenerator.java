package site.stellarburgers.order;

import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderGenerator {
    public static Order getDefaultOrder() {
        OrderClient orderClient = new OrderClient();
        ValidatableResponse responseIngredients = orderClient.getDataIngredients();
        List<String> nameIngredients = responseIngredients.extract().path("data._id");
        List<String> myIngredientsList = new ArrayList<>();
        myIngredientsList.add(nameIngredients.get(8));
        myIngredientsList.add(nameIngredients.get(7));
        myIngredientsList.add(nameIngredients.get(2));
        myIngredientsList.add(nameIngredients.get(14));
        myIngredientsList.add(nameIngredients.get(13));
        myIngredientsList.add(nameIngredients.get(9));
        myIngredientsList.add(nameIngredients.get(8));
        return new Order(myIngredientsList);
    }

    public static Order emptyList() {
        OrderClient orderClient = new OrderClient();
        ValidatableResponse responseIngredients = orderClient.getDataIngredients();
        List<String> nameIngredients = responseIngredients.extract().path("data._id");
        nameIngredients.clear();
        return new Order(nameIngredients);
    }
}
