package fr.unice.polytech.steats.cucumber;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.delivery.*;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
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
import fr.unice.polytech.steats.users.DeliveryPerson;
import fr.unice.polytech.steats.users.UserRole;
import io.cucumber.java.en.*;


import java.time.LocalTime;
import java.util.Optional;

import static fr.unice.polytech.steats.delivery.DeliveryLocation.LIBRARY;
import static org.junit.jupiter.api.Assertions.*;
public class DeliveryNotificationSteps {

    Delivery delivery;
    CampusUser CU;
    DeliveryPerson DP;

    OrderRepository orderRepository = new OrderRepository();
    OrderRegistry orderRegistry = new OrderRegistry(orderRepository,new PaymentManager(new ExternalPaymentMock()),new DeliveryRegistry(new DeliveryRepository()));

    Order order;

    DeliveryRegistry DReg = orderRegistry.getDeliveryRegistry() ;
    DeliveryRepository DRep = DReg.getDeliveryRepository();

    @Given("a User")
    public void a_user() {
        CU = new CampusUser("Sami");
    }

    @Given("a delivery person")
    public void a_delivery_person() {
        DP = new DeliveryPerson("John", UserRole.DELIVERY_PERSON);
        DP.setPhoneNumber(789456123);
    }
    @Given("a delivery with the status WAITING")
    public void a_delivery_with_the_status_waiting() throws EmptyCartException, PaymentException, DeliveryDateNotAvailable {
        Cart cart = new Cart();
        cart.addMenu(new Menu("MaxBurger",12));
        order = orderRegistry.register(new Restaurant("R1"), CU, cart.getMenuMap(), LocalTime.of(12, 0), LIBRARY);
        Optional<Delivery> DL = DRep.findById(order.getDeliveryId());
        DL.ifPresent(value -> delivery = value);
    }



    @When("the delivery status is set as IN_PROGRESS")
    public void the_delivery_status_is_set_as_in_progress() {
        DReg.MarkDeliveryAsReady(delivery.getId(),DP);
    }

    @Then("a notification is sent to the delivery person and the campus user")
    public void a_notification_is_sent_to_the_delivery_person_and_the_campus_user() {
        Optional<Delivery> dl=DRep.findById(delivery.getId());
        if (dl.isPresent()){
            Delivery TestDelivery = dl.get();
            assertEquals(TestDelivery.getStatus(), DeliveryStatus.IN_PROGRESS);
        }
    }
}
