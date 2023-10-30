package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.order.Cart;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.users.CampusUser;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CreateOrder {
    CampusUser campusUser;
    Order order;
    Cart cart;

    @Given("{string} is an authenticated CampusUser")
    public void iAmAnAuthenticatedCampusUser(String username) {
        campusUser = new CampusUser(username);
        cart = campusUser.getCart();
    }

    @And("he has the following items in my cart")
    public void iHaveTheFollowingItemsInMyCart(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            String menuName = columns.get("menuName");
            double price = Double.parseDouble(columns.get("price"));
            Menu menu = new Menu(menuName, price);
            campusUser.addMenuToCart(menu);
        }
    }

    @When("he chooses the available delivery location {string}")
    public void iChooseTheDeliveryLocation(String locationName) {
        order = new Order(campusUser);
        order.setDeliveryLocation(DeliveryLocation.LIBRARY);
    }

    @And("I choose the available delivery time {string}")
    public void iChooseTheAvailableDeliveryTime(String deliveryTime) {
        TimeSlot timeSlot = new TimeSlot(deliveryTime);
        order.setTimeSlot(timeSlot);
    }


    @And("I confirm the order")
    public void iConfirmTheOrder() {
        order.setOrderStatus(OrderStatus.CONFIRMED);
    }

    @Then("an order gets created using the cart")
    public void anOrderGetsCreatedUsingTheCart() {
        assertNotNull(order);
        assertEquals(order.getCustomer(), campusUser);
        assertEquals(OrderStatus.CONFIRMED, order.getOrderStatus());
    }
}
