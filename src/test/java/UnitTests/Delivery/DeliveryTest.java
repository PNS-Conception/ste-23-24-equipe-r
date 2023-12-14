package UnitTests.Delivery;
import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.delivery.DeliveryLocation;

import fr.unice.polytech.steats.order.OrderDetails;
import fr.unice.polytech.steats.order.OrderDetailsBuilder;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.DeliveryPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static fr.unice.polytech.steats.delivery.DeliveryStatus.*;
import static org.junit.jupiter.api.Assertions.*;
public class DeliveryTest {
    private SimpleOrder simpleOrder;
    private Delivery delivery;

    @BeforeEach
    void setUp() {
        CampusUser campusUser = new CampusUser(/* Initialize CampusUser object here */);
        LocalDateTime deliveryTime = LocalDateTime.now();
        DeliveryLocation deliveryLocation = DeliveryLocation.LIBRARY;
        Restaurant restaurant = new Restaurant("R1");
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(campusUser)
                .deliveryTime(deliveryTime)
                .deliveryLocation(deliveryLocation)
                .build();
        simpleOrder = new SimpleOrder(orderDetails );
        delivery = new Delivery(simpleOrder);
    }

    @Test
    void testDeliveryInitialization() {
        assertNotNull(delivery.getId());
        assertEquals(WAITING, delivery.getStatus());
        assertNotNull(delivery.getDeliveryPublisher());
        assertEquals(simpleOrder, delivery.getOrder());
        assertNull(delivery.getDeliveryPerson());
    }

    @Test
    void testSetReady() {
        DeliveryPerson deliveryPerson = new DeliveryPerson("DP1");
        delivery.setReady(deliveryPerson);

        assertEquals(READY, delivery.getStatus());
        assertEquals(deliveryPerson, delivery.getDeliveryPerson());
        assertNotNull(delivery.getDeliveryPublisher().getObservers());
        assertTrue(delivery.getDeliveryPublisher().getObservers().contains(deliveryPerson));
    }

    @Test
    void testSetDeliveryPerson() {
        DeliveryPerson deliveryPerson = new DeliveryPerson("DP2");
        delivery.setDeliveryPerson(deliveryPerson);

        assertEquals(deliveryPerson, delivery.getDeliveryPerson());
    }

    @Test
    void testSetStatus() {
        delivery.setStatus(READY);

        assertEquals(READY, delivery.getStatus());

    }
}
