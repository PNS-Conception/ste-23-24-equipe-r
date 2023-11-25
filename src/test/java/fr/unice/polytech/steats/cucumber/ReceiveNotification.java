package fr.unice.polytech.steats.cucumber;
import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.cucumber.ordering.FacadeContainer;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.order.SubscriberNotExistent;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.notification.NotificationRegistry;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderManager;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import io.cucumber.java.en.*;
import java.time.LocalTime;
import java.util.List;
import static fr.unice.polytech.steats.delivery.DeliveryLocation.LIBRARY;
import static org.junit.jupiter.api.Assertions.*;

public class ReceiveNotification {
    CampusUser CU ;
    Order order ;
    OrderManager orderManager ;


    NotificationRegistry notificationRegistry;

    public ReceiveNotification(FacadeContainer container){
        orderManager=container.orderManager;
        notificationRegistry = container.notificationRegistry;
    }

    @Given("a logged-in Campus user as the order owner")
    public void a_logged_in_campus_user_as_the_order_owner() {
        CU = new CampusUser("john");
    }
    @Given("an order with the status PREPARING")
    public void an_order_with_the_status() throws EmptyCartException, PaymentException, NonExistentTimeSlot, InsufficientTimeSlotCapacity, DeliveryDateNotAvailable {

        Cart cart = new Cart();
        cart.addMenu(new Menu("MaxBurger",12));
        cart.addMenu(new Menu("CheeseBurger",13));
        order = orderManager.register(new Restaurant("R1"), CU, cart.getMenuMap(), LocalTime.of(12, 0), LIBRARY);
        order = orderManager.register(new Restaurant("R1"), CU, cart.getMenuMap(), LocalTime.of(12, 0), LIBRARY);
        order.subscribe(notificationRegistry);
    }



    @When("the order registry sets the order status to READY_FOR_DELIVERY")
    public void the_order_registry_sets_the_order_status_to() {
        order.setStatus(OrderStatus.READY_FOR_DELIVERY);
    }
    @Then("the user is notified")
    public void the_user_is_notified() {
        assertEquals(order.getStatus(),OrderStatus.READY_FOR_DELIVERY);
        List<Notification> userNotifications = notificationRegistry.findByUser(CU);
        assertEquals(userNotifications.size(),1);
    }
}
