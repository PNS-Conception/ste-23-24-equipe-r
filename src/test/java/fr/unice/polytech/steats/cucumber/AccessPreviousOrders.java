package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cucumber.ordering.FacadeContainer;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.order.*;
import fr.unice.polytech.steats.order.strategy.OrderProcessingStrategy;
import fr.unice.polytech.steats.order.strategy.SimpleOrderProcessingStrategy;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.mockito.Mock;
import org.mockito.Mockito;

import static fr.unice.polytech.steats.delivery.DeliveryLocation.LIBRARY;
import static org.junit.Assert.assertEquals;

public class AccessPreviousOrders {
    CampusUser campusUser;
    final OrderProcessing orderProcessing;
    Restaurant restaurant;

    List<Order> previousSimpleOrders = new ArrayList<>();
    UserOrderHistory userOrderHistory;
    SimpleOrderProcessingStrategy simpleOrderProcessingStrategy;

    public AccessPreviousOrders(FacadeContainer container) {
        userOrderHistory = container.userOrderHistory;
        orderProcessing = container.orderProcessing;
        simpleOrderProcessingStrategy = container.simpleOrderProcessingStrategy;
    }

    @Given("a logged-in Campus user {string} and a list of previous orders")
    public void a_logged_in_Campus_user_and_a_list_of_previous_orders (String name) throws EmptyCartException, PaymentException, DeliveryDateNotAvailable {
        orderProcessing.setOrderProcessingStrategy(simpleOrderProcessingStrategy);
        restaurant = new Restaurant("restaurant");
        campusUser = new CampusUser(name);
        Cart cart = campusUser.getCart();
        cart.addMenu(restaurant, new Menu("MaxBurger", 12),1);
        cart.addMenu(restaurant, new Menu("CheeseBurger", 13),1);
        OrderDetailsBuilder builder = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(campusUser)
                .deliveryTime(LocalDate.now().atTime(LocalTime.NOON))
                .deliveryLocation(LIBRARY);
        OrderDetails orderDetails1 = builder.build();
        orderProcessing.process(orderDetails1);
        cart.addMenu(restaurant,new Menu("DoubleBurger", 17),1);
        OrderDetails orderDetails2 = builder.build();
        orderProcessing.process(orderDetails2);
    }
    @Given("a logged-in Campus user {string}")
    public void a_logged_in_campus_user(String name) {
        campusUser = new CampusUser(name);
    }

    @When("the campus user Karim clicks on show previous orders")
    public void the_campus_user_karim_clicks_on_show_previous_orders() {

        previousSimpleOrders = userOrderHistory.getPreviousOrders(campusUser);
    }

    @Then("he should get a list of all his previous orders")
    public void a_list_of_all_his_previous_orders_in_reverse_chronological_order() {
        assertEquals(2, previousSimpleOrders.size());
    }

    @When("there is no previous orders")
    public void there_is_no_previous_orders() {
    }
    @Then("the list of previous orders should be empty")
    public void karim_should_see_a_message_saying() {
        assertEquals(0, previousSimpleOrders.size());
    }
}
