package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.delivery.DeliveryStatus;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderRegistry;
import fr.unice.polytech.steats.order.OrderRepository;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.users.DeliveryPerson;
import fr.unice.polytech.steats.users.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static fr.unice.polytech.steats.order.OrderStatus.READY_FOR_DELIVERY;
import static fr.unice.polytech.steats.users.UserRole.DELIVERY_PERSON;
import static org.junit.jupiter.api.Assertions.*;

public class DeliverySteps {
    DeliveryRegistry deliveryRegistry;
    User deliveryPerson;
    Order order;
    Delivery delivery;

    public DeliverySteps(FacadeContainer container){
        deliveryRegistry = container.deliveryRegistry;
    }
    @Given("an order is ready to be delivered")
    public void an_order_is_ready_to_be_delivered() {
        order = new Order();
        order.setStatus(READY_FOR_DELIVERY);
        deliveryRegistry.register(order);
    }
    @When("a delivery person {string} want to deliver the order")
    public void a_delivery_person_want_to_deliver_the_order(String deliveryPersonName) {
        delivery = new Delivery(order);
        deliveryPerson = new DeliveryPerson(deliveryPersonName, DELIVERY_PERSON);
        assertSame(DELIVERY_PERSON, deliveryPerson.getRole());
        delivery.setDeliveryPerson((DeliveryPerson) deliveryPerson);
    }
    @Then("{string} should be assigned to deliver the order")
    public void should_be_assigned_to_deliver_the_order(String deliveryPersonName) {
        assertEquals(deliveryPersonName, delivery.getDeliveryPerson().getName());
    }
    @Then("the delivery status should be {string}")
    public void the_order_status_should_be(String status) {
        assertSame(DeliveryStatus.valueOf(status), delivery.getStatus());
    }
}
