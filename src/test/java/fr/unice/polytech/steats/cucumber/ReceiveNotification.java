package fr.unice.polytech.steats.cucumber;
import fr.unice.polytech.steats.cart.Cart;

import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.delivery.DeliveryRepository;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderRegistry;
import fr.unice.polytech.steats.order.OrderRepository;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.payment.ExternalPaymentMock;
import fr.unice.polytech.steats.payment.PaymentManager;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;

import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;
import io.cucumber.java.en.*;

import java.time.LocalTime;

import java.util.Optional;

import static fr.unice.polytech.steats.delivery.DeliveryLocation.LIBRARY;
import static org.junit.jupiter.api.Assertions.*;
public class ReceiveNotification {
    CampusUser CU ;
    Order order ;
    OrderRepository orderRepository = new OrderRepository();
    OrderRegistry orderRegistry = new OrderRegistry(orderRepository,new PaymentManager(new ExternalPaymentMock()),new DeliveryRegistry(new DeliveryRepository()));


    @Given("a logged-in Campus user as the order owner")
    public void a_logged_in_campus_user_as_the_order_owner() {
        CU = new CampusUser("john");
    }
    @Given("an order with the status PREPARING")
    public void an_order_with_the_status() throws EmptyCartException, PaymentException, NonExistentTimeSlot, InsufficientTimeSlotCapacity, DeliveryDateNotAvailable {

        Cart cart = new Cart();
        cart.addMenu(new Menu("MaxBurger",12));
        cart.addMenu(new Menu("CheeseBurger",13));
        order = orderRegistry.register(new Restaurant("R1"), CU, cart.getMenuMap(), LocalTime.of(12, 0), LIBRARY);
    }



    @When("the order registry sets the order status to READY_FOR_DELIVERY")
    public void the_order_registry_sets_the_order_status_to() {
        orderRegistry.MarkOrderAsReady(order.getId());


    }
    @Then("the user is notified")
    public void the_user_is_notified() {
        Optional<Order> od=orderRepository.findById(order.getId());
        if (od.isPresent()){
            Order TestOrder = od.get();
            assertEquals(TestOrder.getStatus(),OrderStatus.READY_FOR_DELIVERY);
        }
    }
}
