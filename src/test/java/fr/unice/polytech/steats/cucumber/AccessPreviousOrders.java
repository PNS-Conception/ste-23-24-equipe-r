package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderRegistry;
import fr.unice.polytech.steats.order.OrderRepository;
import fr.unice.polytech.steats.payment.ExternalPaymentMock;
import fr.unice.polytech.steats.payment.PaymentManager;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static fr.unice.polytech.steats.delivery.DeliveryLocation.LIBRARY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class AccessPreviousOrders {
    CampusUser campusUser;
    OrderRepository orderRepository = new OrderRepository();
    OrderRegistry orderRegistry = new OrderRegistry(orderRepository,new PaymentManager(new ExternalPaymentMock()));

    List<Order> previousOrders = new ArrayList<>();

    @Given("a logged-in Campus user {string} and a list of previous orders")
    public void a_logged_in_Campus_user_and_a_list_of_previous_orders (String name) throws EmptyCartException, PaymentException, NonExistentTimeSlot, InsufficientTimeSlotCapacity {
        campusUser = new CampusUser(name);
        TimeSlot timeSlot = new TimeSlot(LocalTime.of(12, 0), 6);
        Cart cart = new Cart();
        cart.addMenu(new Menu("MaxBurger",12));
        cart.addMenu(new Menu("CheeseBurger",13));
        orderRegistry.register(new Restaurant("R1"), campusUser, cart.getMenuMap(), timeSlot, LIBRARY);
        cart.addMenu(new Menu("DoubleBurger",17));;
        orderRegistry.register(new Restaurant("R1"), campusUser, cart.getMenuMap(), timeSlot, LIBRARY);
    }
    @Given("a logged-in Campus user {string}")
    public void a_logged_in_campus_user(String name) {
        campusUser = new CampusUser(name);
    }

    @When("the campus user Karim clicks on show previous orders")
    public void the_campus_user_karim_clicks_on_show_previous_orders() {
        previousOrders = orderRegistry.getPreviousOrders(campusUser);
    }

    @Then("he should get a list of all his previous orders")
    public void a_list_of_all_his_previous_orders_in_reverse_chronological_order() {
        assertEquals(2, previousOrders.size());
    }

    @When("there is no previous orders")
    public void there_is_no_previous_orders() {
    }
    @Then("the list of previous orders should be empty")
    public void karim_should_see_a_message_saying() {
        assertEquals(0, previousOrders.size());
    }
}
