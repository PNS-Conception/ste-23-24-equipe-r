package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cucumber.ordering.FacadeContainer;
import fr.unice.polytech.steats.delivery.*;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.notification.NotificationRegistry;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderManager;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.DeliveryPerson;
import fr.unice.polytech.steats.users.UserRole;
import io.cucumber.java.en.*;


import java.time.LocalTime;

import static fr.unice.polytech.steats.delivery.DeliveryLocation.LIBRARY;
import static fr.unice.polytech.steats.delivery.DeliveryStatus.IN_PROGRESS;
import static fr.unice.polytech.steats.delivery.DeliveryStatus.WAITING;
import static org.junit.jupiter.api.Assertions.*;
public class DeliveryNotificationSteps {

    Delivery delivery;
    CampusUser campusUser;
    DeliveryPerson deliveryPerson;

    OrderManager orderManager;

    Order order;

    DeliveryRegistry deliveryRegistry;
    NotificationRegistry notificationRegistry;


    public DeliveryNotificationSteps(FacadeContainer container) {
        orderManager = container.orderManager;
        deliveryRegistry = orderManager.getDeliveryRegistry();
        orderManager = container.orderManager;
        deliveryRegistry = orderManager.getDeliveryRegistry();
        notificationRegistry =container.notificationRegistry;





    }

    @Given("a user named {string}")
    public void a_user(String name) {
        campusUser = new CampusUser(name);
    }

    @Given("{string} a delivery person")
    public void a_delivery_person(String name) {
        deliveryPerson = new DeliveryPerson(name, UserRole.DELIVERY_PERSON);
        deliveryPerson.setPhoneNumber("789456123");

    }
    @Given("a delivery with the status WAITING")
    public void a_delivery_with_the_status_waiting() throws EmptyCartException, PaymentException, DeliveryDateNotAvailable {
        Cart cart = new Cart();
        cart.addMenu(new Menu("MaxBurger", 12));
        order = orderManager.register(new Restaurant("R1"), campusUser, cart.getMenuMap(), LocalTime.of(12, 0), LIBRARY);
        delivery = new Delivery(order);
        delivery.subscribe(notificationRegistry);
        deliveryRegistry.getDeliveryRepository().save(delivery, delivery.getId());
    }



    @When("the delivery status is set as IN_PROGRESS")
    public void the_delivery_status_is_set_as_in_progress() {
        delivery.setDeliveryPerson(deliveryPerson);
        delivery.setStatus(IN_PROGRESS);
    }

    @Then("a notification is sent to the delivery person and the campus user")
    public void a_notification_is_sent_to_the_delivery_person_and_the_campus_user() {
        assertEquals(delivery.getStatus(), DeliveryStatus.IN_PROGRESS);
        assertEquals(notificationRegistry.findByUser(campusUser).size(),1 );
        assertEquals(notificationRegistry.findByUser(deliveryPerson).size(),1);
    }
}
