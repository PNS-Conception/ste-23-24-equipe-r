package fr.unice.polytech.steats.cucumber;
import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cucumber.ordering.FacadeContainer;
import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.delivery.DeliveryStatus;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.notification.NotificationRegistry;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.order.OrderManager;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.DeliveryPerson;
import io.cucumber.java.en.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import static fr.unice.polytech.steats.delivery.DeliveryLocation.LIBRARY;
import static org.junit.jupiter.api.Assertions.*;

public class ReceiveNotification {
    CampusUser campusUser;
    SimpleOrder simpleOrder;
    OrderManager orderManager ;
    Delivery delivery;
    List<DeliveryPerson> deliveryPeople;


    NotificationRegistry notificationRegistry;

    public ReceiveNotification(FacadeContainer container){
        orderManager=container.orderManager;
        notificationRegistry = container.notificationRegistry;
    }

    @Given("a logged-in Campus user as the order owner")
    public void a_logged_in_campus_user_as_the_order_owner() {
        campusUser = new CampusUser("john");
    }
    @Given("an order with the status PREPARING")
    public void an_order_with_the_status() throws EmptyCartException, PaymentException, DeliveryDateNotAvailable {

        Cart cart = new Cart();
        cart.addMenu(new Menu("MaxBurger",12));
        cart.addMenu(new Menu("CheeseBurger",13));
        simpleOrder = orderManager.process(new Restaurant("R1"), campusUser, cart.getMenuMap(), LocalDate.now().atTime(LocalTime.of(12, 0)), LIBRARY);
        delivery = new Delivery(simpleOrder);
        delivery.subscribe(notificationRegistry);
    }



    @When("the order registry sets the order status to READY_FOR_DELIVERY")
    public void the_order_registry_sets_the_order_status_to() {
        delivery.setReady(deliveryPeople.get(0));
    }
    @Then("the user is notified")
    public void the_user_is_notified() {
        assertEquals(DeliveryStatus.READY, delivery.getStatus());
        List<Notification> userNotifications = notificationRegistry.findByUser(campusUser);
        assertEquals(1, userNotifications.size());
    }

    @Then("a delivery person is notified")
    public void a_delivery_person_is_notified() {
        assertEquals(DeliveryStatus.READY, delivery.getStatus());
        List<Notification> userNotifications = notificationRegistry.findByUser(deliveryPeople.get(0));
        assertEquals(1, userNotifications.size());
    }

    @And("delivery people ready to pick an order")
    public void deliveryPeopleReadyToPickAnOrder() {
        deliveryPeople = Arrays.asList(new DeliveryPerson("John"), new DeliveryPerson("Jack"));
    }
}
