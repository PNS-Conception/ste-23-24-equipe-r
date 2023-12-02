package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.delivery.DeliveryStatus;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.order.OrderManager;
import fr.unice.polytech.steats.order.factory.SimpleOrderFactory;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
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
    SimpleOrder simpleOrder;
    Delivery delivery;

    OrderManager orderManager;

    public DeliverySteps(FacadeContainer container){
        deliveryRegistry = container.deliveryRegistry;
        orderManager = container.orderManager;
    }
    @Given("an order is ready to be delivered")
    public void an_order_is_ready_to_be_delivered() {
        SimpleOrderFactory simpleOrderFactory = new SimpleOrderFactory(new Restaurant("restaurant"), new CampusUser("user"), null, null, null);
        simpleOrder = simpleOrderFactory.createOrder();
        simpleOrder.setStatus(READY_FOR_DELIVERY);
        deliveryRegistry.register(simpleOrder);
    }
    @When("a delivery person {string} want to deliver the order")
    public void a_delivery_person_want_to_deliver_the_order(String deliveryPersonName) {
        delivery = new Delivery(simpleOrder);
        deliveryPerson = new DeliveryPerson(deliveryPersonName);
        assertSame(DELIVERY_PERSON, deliveryPerson.getRole());
        delivery.setDeliveryPerson((DeliveryPerson) deliveryPerson);
    }
    @Then("{string} should be assigned to deliver the order")
    public void should_be_assigned_to_deliver_the_order(String deliveryPersonName) {
        deliveryRegistry.assign(delivery, delivery.getDeliveryPerson());
        assertEquals(deliveryPersonName, delivery.getDeliveryPerson().getName());
    }
    @Then("the delivery status should be {string}")
    public void the_order_status_should_be(String status) {
        assertSame(DeliveryStatus.valueOf(status), delivery.getStatus());
    }
}
