package fr.unice.polytech.steats.unit.orderTest;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.order.*;
import fr.unice.polytech.steats.payment.Payment;

import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;

import fr.unice.polytech.steats.restaurant.Schedule;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderManagerTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private Payment payment;

    @Mock
    private DeliveryRegistry deliveryRegistry;

    @InjectMocks
    private OrderManager orderManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess() throws EmptyCartException, PaymentException, DeliveryDateNotAvailable {
        // Given
        CampusUser campusUser = new CampusUser();
        campusUser.addMenuToCart(new Menu("fish",55));
        LocalDateTime deliveryTime = LocalDateTime.now();
        DeliveryLocation deliveryLocation = DeliveryLocation.LIBRARY;
        Restaurant restaurant = new Restaurant("R1"); // Initialize Schedule here
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(campusUser)
                .deliveryTime(deliveryTime)
                .deliveryLocation(deliveryLocation)
                .build();

// Fix: use doNothing() for void method
        doNothing().when(orderRepository).save(any(SimpleOrder.class), any(UUID.class));
        doNothing().when(deliveryRegistry).register(any(SimpleOrder.class));

// When
        SimpleOrder resultOrder = orderManager.process(orderDetails);


        // Then
        assertNotNull(resultOrder);
        assertEquals(OrderStatus.PREPARING, resultOrder.getStatus());
        verify(payment, times(1)).completePayment(campusUser);
        verify(orderRepository, times(1)).save(any(SimpleOrder.class), any(UUID.class));
        verify(deliveryRegistry, times(1)).register(any(SimpleOrder.class));
        assertTrue(campusUser.getCart().getMenuMap().isEmpty());
    }

    @Test
    void testCalculateTimeslot() throws EmptyCartException, DeliveryDateNotAvailable {
        // Given
        CampusUser campusUser = new CampusUser(/* Initialize CampusUser object here */);
        LocalDateTime deliveryTime = LocalDateTime.now();
        DeliveryLocation deliveryLocation = DeliveryLocation.LIBRARY;
        Restaurant restaurant = new Restaurant("R1"); // Initialize Schedule here
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(campusUser)
                .deliveryTime(deliveryTime)
                .deliveryLocation(deliveryLocation)
                .build();

        // When
        Optional<TimeSlot> resultTimeSlot = orderManager.calculateTimeslot(restaurant.getSchedule(), orderDetails.getDeliveryTime(), 1);

        // Then
        assertTrue(resultTimeSlot.isPresent());
        LocalTime deliveryTimeOnly = orderDetails.getDeliveryTime().toLocalTime();
        LocalTime openingTime = restaurant.getSchedule().getOpeningTime();
        openingTime.isBefore(deliveryTimeOnly);
    }

    @Test
    void testCalculateTimeslotEmptyCartException() {
        // Given
        DeliveryLocation deliveryLocation = DeliveryLocation.LIBRARY;
        Restaurant restaurant = new Restaurant("Restaurant");
        CampusUser campusUser = new CampusUser(/* Initialize CampusUser object here */);
        LocalDateTime deliveryTime = LocalDateTime.now();
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(campusUser)
                .deliveryTime(deliveryTime)
                .deliveryLocation(deliveryLocation)
                .build();
        // When, Then
        assertThrows(EmptyCartException.class, () -> orderManager.calculateTimeslot(restaurant.getSchedule(), orderDetails.getDeliveryTime(), 0));
    }

    @Test
    void testCalculateTimeslotDeliveryDateNotAvailable() {
        // Given
        DeliveryLocation deliveryLocation = DeliveryLocation.LIBRARY;
        Restaurant restaurant = new Restaurant("Restaurant",new Schedule(LocalTime.of(16, 30, 45),LocalTime.of(18, 30, 45),3));
        CampusUser campusUser = new CampusUser(/* Initialize CampusUser object here */);
        LocalDateTime deliveryTime = LocalDateTime.now();
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(campusUser)
                .deliveryTime(deliveryTime)
                .deliveryLocation(deliveryLocation)
                .build();
        // When, Then
        assertThrows(DeliveryDateNotAvailable.class, () -> orderManager.calculateTimeslot(restaurant.getSchedule(), orderDetails.getDeliveryTime(), 1));
    }

    @Test
    void testGetPreviousOrders() {
        // Given
        CampusUser user = new CampusUser();
        LocalDateTime deliveryTime = LocalDateTime.now();
        DeliveryLocation deliveryLocation = DeliveryLocation.LIBRARY;
        Restaurant restaurant = new Restaurant("R1"); // Initialize Schedule here
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .restaurant(restaurant)
                .orderOwner(user)
                .deliveryTime(deliveryTime)
                .deliveryLocation(deliveryLocation)
                .build();
        SimpleOrder order1 = new SimpleOrder(orderDetails);
        SimpleOrder order2 = new SimpleOrder(orderDetails);
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        // When
        List<SimpleOrder> previousOrders = orderManager.getPreviousOrders(user);

        // Then
        assertEquals(2, previousOrders.size());
    }

    @Test
    void testGetOrdersByStatus() {
        // Given
        CampusUser user = new CampusUser();
        Restaurant restaurant = new Restaurant("R1");
        OrderDetails orderDetails = new OrderDetailsBuilder()
                .orderOwner(user)
                .restaurant(restaurant)
                .build();
        SimpleOrder order1 = new SimpleOrder(orderDetails);
        SimpleOrder order2 = new SimpleOrder(orderDetails);
        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1, order2));

        // When
        List<SimpleOrder> ordersByStatus = orderManager.getOrdersByStatus(restaurant, OrderStatus.PREPARING);

        // Then
        assertEquals(0, ordersByStatus.size()); // Assuming none of these orders have the status PREPARING
    }
}
