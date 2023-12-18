package fr.unice.polytech.steats.unit.Delivery;

import fr.unice.polytech.steats.steatspico.entities.delivery.Delivery;
import fr.unice.polytech.steats.steatspico.entities.delivery.DeliveryLocation;
import fr.unice.polytech.steats.steatspico.components.DeliveryRegistry;
import fr.unice.polytech.steats.steatspico.repositories.DeliveryRepository;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetailsBuilder;
import fr.unice.polytech.steats.steatspico.entities.order.SimpleOrder;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;
import fr.unice.polytech.steats.steatspico.entities.users.DeliveryPerson;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.UUID;

import static fr.unice.polytech.steats.steatspico.entities.delivery.DeliveryStatus.IN_PROGRESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;



class DeliveryRegistryTest {

    @Mock
    private DeliveryRepository deliveryRepository;

    private DeliveryRegistry deliveryRegistry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deliveryRegistry = new DeliveryRegistry(deliveryRepository);
    }

    @Test
    void testRegister() {
        // Given
        CampusUser campusUser = new CampusUser();
        LocalDateTime deliveryTime = LocalDateTime.now();
        DeliveryLocation deliveryLocation = DeliveryLocation.LIBRARY;
        Restaurant restaurant = new Restaurant("R1");
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(campusUser)
                .deliveryTime(deliveryTime)
                .deliveryLocation(deliveryLocation)
                .build();
        SimpleOrder simpleOrder = new SimpleOrder(orderDetails );

        // When
        deliveryRegistry.register(simpleOrder);

        // Then
        verify(deliveryRepository, times(1)).save(any(Delivery.class), any(UUID.class));
    }

    @Test
    void testAssign() {
        // Given
        CampusUser campusUser = new CampusUser();
        LocalDateTime deliveryTime = LocalDateTime.now();
        DeliveryLocation deliveryLocation = DeliveryLocation.LIBRARY;
        Restaurant restaurant = new Restaurant("R1");
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(campusUser)
                .deliveryTime(deliveryTime)
                .deliveryLocation(deliveryLocation)
                .build();
        SimpleOrder simpleOrder = new SimpleOrder(orderDetails );
        Delivery delivery = new Delivery(simpleOrder);
        DeliveryPerson deliveryPerson = new DeliveryPerson("DP1");

        // When
        deliveryRegistry.assign(delivery, deliveryPerson);

        // Then
        assertEquals(IN_PROGRESS, delivery.getStatus());
        assertEquals(deliveryPerson, delivery.getDeliveryPerson());
        verify(deliveryRepository, times(1)).save(any(Delivery.class), any(UUID.class));
    }

    @Test
    void testGetDeliveryRepository() {
        assertEquals(deliveryRepository, deliveryRegistry.getDeliveryRepository());
    }

}