package fr.unice.polytech.steats.cucumber.delivery;

import fr.unice.polytech.steats.cucumber.picosetup.FacadeContainer;
import fr.unice.polytech.steats.steatspico.entities.delivery.Delivery;
import fr.unice.polytech.steats.steatspico.components.DeliveryRegistry;
import fr.unice.polytech.steats.steatspico.entities.delivery.DeliveryStatus;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetailsBuilder;
import fr.unice.polytech.steats.steatspico.entities.order.SimpleOrder;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;
import fr.unice.polytech.steats.steatspico.entities.users.DeliveryPerson;
import fr.unice.polytech.steats.steatspico.entities.users.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static fr.unice.polytech.steats.steatspico.entities.order.OrderStatus.READY_FOR_DELIVERY;
import static fr.unice.polytech.steats.steatspico.entities.users.UserRole.DELIVERY_PERSON;
import static org.junit.jupiter.api.Assertions.*;

public class DeliverySteps {
    final DeliveryRegistry deliveryRegistry;
    User deliveryPerson;
    SimpleOrder simpleOrder;
    Delivery delivery;



    public DeliverySteps(FacadeContainer container){
        deliveryRegistry = container.deliveryRegistry;

    }
    @Given("an order is ready to be delivered")
    public void an_order_is_ready_to_be_delivered() {
        Restaurant restaurant = new Restaurant("restaurant");
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurants(new ArrayList<>(List.of(restaurant)))
                .orderOwner(new CampusUser("user"))
                .build();
        simpleOrder = new SimpleOrder(orderDetails);
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
