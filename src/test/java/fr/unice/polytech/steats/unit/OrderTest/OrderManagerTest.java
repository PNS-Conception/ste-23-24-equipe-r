package fr.unice.polytech.steats.unit.OrderTest;

import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.notification.pickupTime.PickupTimePublisher;
import fr.unice.polytech.steats.order.*;
import fr.unice.polytech.steats.order.factory.SimpleOrderFactory;
import fr.unice.polytech.steats.payment.Payment;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.Schedule;
import fr.unice.polytech.steats.users.CampusUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class OrderManagerTest {
/*
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private Payment payment;
    @Mock
    private DeliveryRegistry deliveryRegistry;
    @Mock
    private PickupTimePublisher pickupTimePublisher;
    @Mock
    private SimpleOrderFactory simpleOrderFactory;
    @Mock
    private CampusUser mockUser;
    @Mock
    private OrderDetails orderDetails;
    @Mock
    private Restaurant mockRestaurant;
    @Mock
    private Schedule mockSchedule;

    private OrderManager orderManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderManager = new OrderManager(orderRepository, payment, deliveryRegistry);
    }

    @Test
    void testProcessOrder() throws EmptyCartException, PaymentException, DeliveryDateNotAvailable {
        // Setup
        when(orderDetails.getOrderOwner()).thenReturn(mockUser);
        when(orderDetails.getRestaurant()).thenReturn(mockRestaurant);
        when(mockRestaurant.getSchedule()).thenReturn(mockSchedule);
        when(simpleOrderFactory.createOrder(orderDetails)).thenReturn(new SimpleOrder(orderDetails));
        when(orderDetails.menusOrdered().get(any())).thenReturn(Map.of(new Menu("Pizza", 12), 2));

        // Execute
        SimpleOrder result = orderManager.process(orderDetails);

        // Verify
        assertNotNull(result);
        verify(payment).completePayment(mockUser);
        verify(orderRepository).save(any(SimpleOrder.class), any());
        verify(deliveryRegistry).register(any(SimpleOrder.class));
        verify(pickupTimePublisher).subscribe(mockRestaurant);
        verify(pickupTimePublisher).notifySubscribers(any(SimpleOrder.class));
    }

    @Test
    void testCalculateTimeslot() {
        // Setup
        Map<Menu, Integer> menusOrdered = Map.of(new Menu("Burger", 10), 1);
        LocalDateTime deliveryTime = LocalDateTime.now();

        // Execute
        assertDoesNotThrow(() -> orderManager.calculateTimeslot(mockSchedule, deliveryTime, menusOrdered));
    }

    @Test
    void testGetPreviousOrders() {
        // Setup
        SimpleOrder mockOrder = mock(SimpleOrder.class);
        when(orderRepository.findAll()).thenReturn(List.of(mockOrder));
        when(mockOrder.getCustomer()).thenReturn(mockUser);

        // Execute
        List<SimpleOrder> orders = orderManager.getPreviousOrders(mockUser);

        // Verify
        assertFalse(orders.isEmpty());
        assertTrue(orders.contains(mockOrder));
    }

    @Test
    void testGetOrdersByStatus() {
        // Setup
        SimpleOrder mockOrder = mock(SimpleOrder.class);
        when(orderRepository.findAll()).thenReturn(List.of(mockOrder));
        when(mockOrder.getRestaurant()).thenReturn(mockRestaurant);
        when(mockOrder.getStatus()).thenReturn(OrderStatus.PREPARING);

        // Execute
        List<SimpleOrder> orders = orderManager.getOrdersByStatus(mockRestaurant, OrderStatus.PREPARING);

        // Verify
        assertFalse(orders.isEmpty());
        assertTrue(orders.contains(mockOrder));
    }

 */
}
