package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.enumeration.OrderStatus;
import fr.unice.polytech.steats.model.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class DeliveryConfirmation {
    Order order;
    DeliveryPerson deliveryPerson;

    @Given("there is an order with status {string}")
    public void thereIsAnOrderWithStatus(String status) {
        order = new Order(20.0, new CampusUser("John"));
        order.setOrderStatus(OrderStatus.valueOf(status));
    }

    @When("the delivery person delivers the order")
    public void theDeliveryPersonDeliversTheOrder() {
        deliveryPerson = new DeliveryPerson("Jane");
        order.setOrderStatus(OrderStatus.DELIVERED);
    }

    @Then("the order status should be {string}")
    public void theOrderStatusShouldBe(String expectedStatus) {
        assertEquals(OrderStatus.valueOf(expectedStatus), order.getOrderStatus());
    }
}
