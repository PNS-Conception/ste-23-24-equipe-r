package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.model.Menu;
import fr.unice.polytech.steats.model.Restaurant;
import fr.unice.polytech.steats.model.RestaurantStaff;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class orderAcceptanceSteps {

    RestaurantStaff restaurantStaff = new RestaurantStaff("1", "restaurantStaff1@email.fr","password", "restaurantStaff1");
    Restaurant restaurant = new Restaurant("12", "");


    @Given("{string} member is logged into the {string} system")
    public void member_is_logged_into_the(String string, String string2) {
        restaurantStaff.setName(string);
        restaurant = new Restaurant(restaurant.getRestaurantID(), string2);
    }
    @Given("there is no order awaiting acceptance")
    public void there_is_awaiting_acceptance() {
        List<Menu> emptyOrderList = new ArrayList<>();
        restaurant.setOrderList(emptyOrderList);
    }
    @When("the {string} attempts to accept an order")
    public void the_attempts_to_accept_an_order(String string) {
        restaurant.getEmployees().add(restaurantStaff);
        assertEquals(restaurantStaff.getName(),string);
        assertTrue(restaurant.getEmployees().contains(restaurantStaff));
    }
    @Then("the {string} system should display an error message: {string}")
    public void the_should_display_an_error_message(String string, String string2) {
        assertEquals(restaurant.getRestaurantName(), string);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            restaurant.getOrderList();
        });

        assertEquals(exception.getMessage(), string2);
    }

}
