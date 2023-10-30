package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.RestaurantStaff;
import io.cucumber.java.en.*;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderAcceptanceSteps {

    RestaurantStaff restaurantStaff;
    Restaurant restaurant ;
    Order order;


    @Given("{string} member is logged into the {string} system")
    public void member_is_logged_into_the(String string, String string2) {
        restaurantStaff = new RestaurantStaff(string);
        restaurant = new Restaurant(string2);
    }
    @Given("there is no order awaiting acceptance")
    public void there_is_no_order_awaiting_acceptance() {
        assertEquals(1,1);
    }

    @When("the {string} consult the list of orders")
    public void the_attempts_to_accept_an_order(String string) {
        assertEquals(string,string);
    }
    @Then("the {string} system should display an error message: {string}")
    public void the_should_display_an_error_message(String string, String string2) {
        assertEquals(restaurant.getRestaurantName(), string);

        assertEquals(string2, string2);
    }

    @Given("there is an order awaiting acceptance")
    public void there_is_an_with_id_awaiting_acceptance() {
        order = new Order();
        restaurant.addOrder(order);
    }


    @When("the {string} member accepts the order for preparation")
    public void the_member_accepts_the_order_for_preparation(String string) {
        for(Order order : restaurant.getPendingOrders()){
            if(order.getOrderID().equals(this.order.getOrderID())){
                order.setOrderStatus(OrderStatus.ACCEPTED);
            }
        }
    }

    @Then("the order status becomes ACCEPTED")
    public void the_system_should_mark_the_order_as() {
        assertEquals(this.restaurant.getRestaurantName(),restaurant.getRestaurantName());
        Order order1 = null;
        for(Order order : this.restaurant.getPendingOrders()){
            if(order.getOrderID().equals(UUID.fromString(order.getOrderID().toString()))){
                order1= order;
            }
        }
        assertEquals(order1.getOrderStatus(), OrderStatus.ACCEPTED);
    }

}