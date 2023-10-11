package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.enumeration.OrderStatus;
import fr.unice.polytech.steats.model.Menu;
import fr.unice.polytech.steats.model.Order;
import fr.unice.polytech.steats.model.Restaurant;
import fr.unice.polytech.steats.model.RestaurantStaff;
import io.cucumber.java.en.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class OrderAcceptanceSteps {

    RestaurantStaff restaurantStaff = new RestaurantStaff("1", "restaurantStaff1@email.fr","password", "restaurantStaff1");
    Restaurant restaurant = new Restaurant("12", "");


    @Given("{string} member is logged into the {string} system")
    public void member_is_logged_into_the(String string, String string2) {
        restaurantStaff.setName(string);
        restaurant = new Restaurant(restaurant.getRestaurantID(), string2);
    }
    @Given("there is no order awaiting acceptance")
    public void there_is_no_order_awaiting_acceptance() {
        // Initialize an empty order list for the restaurant
        restaurant.setOrderList(new ArrayList<>());
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

    @Given("there is an {string} with ID {string} awaiting acceptance")
    public void there_is_an_with_id_awaiting_acceptance(String string, String string2) {
        restaurant.addOrder(new Order(string2, 10.0));
    }


    @When("the {string} member accepts the order with ID {string} for preparation")
    public void the_member_accepts_the_order_for_preparation(String string, String string2) {
        assertEquals(restaurantStaff.getName(),string);
        restaurant.getEmployees().add(restaurantStaff);
        assertTrue(restaurant.getEmployees().contains(restaurantStaff));
        for(Order order : restaurant.getOrderList()){
            if(order.getOrderID().equals(string2)){
                order.setOrderStatus(OrderStatus.ACCEPTED);
            }
        }
    }

    @Then("the {string} system should mark the order with ID {string} as {string}")
    public void the_system_should_mark_the_order_as(String string, String string2, String string3) {
        assertEquals(restaurant.getRestaurantName(),string);
        Order order1 = null;
        for(Order order : restaurant.getOrderList()){
            if(order.getOrderID().equals(string2)){
                order1= order;
            }
        }
        assertEquals(order1.getOrderStatus().toString(), string3);

    }

}
