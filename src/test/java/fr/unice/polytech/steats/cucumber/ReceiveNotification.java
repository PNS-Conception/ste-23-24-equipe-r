package fr.unice.polytech.steats.cucumber;
import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cucumber.ordering.FacadeContainer;
import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.delivery.DeliveryStatus;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.AlreadyExistingRestaurantException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.notification.NotificationRegistry;
import fr.unice.polytech.steats.order.*;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.RestaurantRegistration;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
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
    final OrderProcessing orderProcessing ;
    Delivery delivery;
    List<DeliveryPerson> deliveryPeople;
    NotificationRegistry notificationRegistry = NotificationRegistry.getInstance();
    final RestaurantRegistry restaurantRegistration;



    public ReceiveNotification(FacadeContainer container){
        orderProcessing=container.orderProcessing;
        restaurantRegistration=container.restaurantRegistration;
    }

    @Given("a logged-in Campus user as the order owner")
    public void a_logged_in_campus_user_as_the_order_owner() {
        campusUser = new CampusUser("john");

    }
    @Given("an order with the status PREPARING")
    public void an_order_with_the_status() throws EmptyCartException, PaymentException, DeliveryDateNotAvailable {

        Cart cart = campusUser.getCart();
        cart.addMenu(new Menu("MaxBurger",12));
        cart.addMenu(new Menu("CheeseBurger",13));
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(new Restaurant("R1"))
                .orderOwner(campusUser)
                .deliveryTime(LocalDate.now().atTime(LocalTime.of(12, 0)))
                .deliveryLocation(LIBRARY)
                .build();
        simpleOrder = orderProcessing.process(orderDetails);
        delivery = new Delivery(simpleOrder);
    }



    @When("the order registry sets the order status to READY_FOR_DELIVERY")
    public void the_order_registry_sets_the_order_status_to() {
        delivery.setReady(deliveryPeople.get(0));
    }

    @Then("a delivery person is notified")
    public void a_delivery_person_is_notified() {
        assertEquals(DeliveryStatus.READY, delivery.getStatus());
        assertEquals(1, notificationRegistry.findByRecipient(deliveryPeople.get(0)).size());
    }

    @When("{string} creates an order")
    public void creates_an_order(String customerName) throws EmptyCartException, PaymentException, DeliveryDateNotAvailable {
        campusUser = new CampusUser(customerName);
        Cart cart = campusUser.getCart();
        cart.addMenu(new Menu("MaxBurger",12));
        cart.addMenu(new Menu("CheeseBurger",13));
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(new Restaurant("R1"))
                .orderOwner(campusUser)
                .deliveryTime(LocalDate.now().atTime(LocalTime.of(12, 0)))
                .deliveryLocation(LIBRARY)
                .build();
        simpleOrder = orderProcessing.process(orderDetails);
    }

    @Then("{string} is notified")
    public void the_order_owners_are_notified(String userName) {
        assertEquals(1, notificationRegistry.findByRecipient(campusUser).size());
    }

    @And("delivery people ready to pick an order")
    public void deliveryPeopleReadyToPickAnOrder() {
        deliveryPeople = Arrays.asList(new DeliveryPerson("John"), new DeliveryPerson("Jack"));
    }

    @Then("{string} is notified about the planned pickup time")
    public void isNotifiedAboutThePlannedPickupTime(String restautanName) throws AlreadyExistingRestaurantException {
        Restaurant restaurant = restaurantRegistration.findByName(restautanName).orElseThrow(() -> new AlreadyExistingRestaurantException("Element not found"));
        assertEquals(1, notificationRegistry.findByRecipient(restaurant).size());
    }
}
