package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.payment.ExternalPaymentMock;
import fr.unice.polytech.steats.payment.PaymentManager;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class OrderRegistry {
    PaymentManager paymentManager;
    OrderRepository orderRepository;
    public OrderRegistry(OrderRepository orderRepository, PaymentManager paymentManager){
        this.orderRepository = orderRepository;
        this.paymentManager = paymentManager;
    }

    public OrderRegistry() {
        this.orderRepository = new OrderRepository();
        this.paymentManager = new PaymentManager(new ExternalPaymentMock());
    }

    public OrderRegistry(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.paymentManager = new PaymentManager(new ExternalPaymentMock());
    }

    public Order register(Restaurant restaurant, CampusUser customer, Map<Menu, Integer> menusOrdered,
                          TimeSlot timeSlot, DeliveryLocation deliveryLocation)
            throws InsufficientTimeSlotCapacity, NonExistentTimeSlot, EmptyCartException, PaymentException {
        isValidOrder(restaurant, timeSlot, menusOrdered);
        Map<Menu, Integer> menusOrderedCopy = menusOrdered.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> new Menu(entry.getKey()),  // Using the copy constructor
                        Map.Entry::getValue
                ));
        Order order = new Order(restaurant, customer, menusOrderedCopy, deliveryLocation, timeSlot);
        order.setStatus(OrderStatus.PREPARING);
        paymentManager.completePayment(customer);
        int menusNumber = menusOrdered.values().stream().mapToInt(Integer::intValue).sum();
        timeSlot.subtractCapacity(menusNumber);
        orderRepository.save(order, order.getId());
        customer.getCart().emptyCart();
        return order;
    }


    public void isValidOrder(Restaurant restaurant, TimeSlot timeslot, Map<Menu, Integer> menus)
            throws InsufficientTimeSlotCapacity, NonExistentTimeSlot, EmptyCartException {
        if (menus.isEmpty()){
            throw new EmptyCartException();
        }
        if (restaurant.getSchedule().getTimeSlots().contains(timeslot)){
            int menusNumber = menus.values().stream().mapToInt(Integer::intValue).sum();
            if (menusNumber > restaurant.getTimeSlotCapacity(timeslot)){
                throw new InsufficientTimeSlotCapacity();
            }
        }else {
            throw new NonExistentTimeSlot(timeslot);
        }
    }

    public List<Order> getPreviousOrders(CampusUser user) {
        List<Order> previousOrders = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            if (order.getCustomer().equals(user)) {
                previousOrders.add(order);
            }
        }
        return previousOrders;
    }

    public List<Order> getOrdersWaitingForPreparation(Restaurant restaurant) {
        List<Order> previousOrders = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            if (order.getRestaurant().equals(restaurant) && order.getStatus().equals(OrderStatus.WAITING_FOR_PREPARATION)) {
                previousOrders.add(order);
            }
        }
        return previousOrders;
    }
}
