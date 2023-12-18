package fr.unice.polytech.steats.steatspico.components;

import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.OrderStatus;
import fr.unice.polytech.steats.steatspico.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.steatspico.interfaces.order.OrderLocator;
import fr.unice.polytech.steats.steatspico.interfaces.order.OrderProcessing;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.TimeslotHandler;
import fr.unice.polytech.steats.steatspico.interfaces.order.UserOrderHistory;
import fr.unice.polytech.steats.steatspico.entities.notification.PickupTimePublisher;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.OrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.exceptions.order.PaymentException;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.SimpleOrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.repositories.OrderRepository;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Schedule;
import fr.unice.polytech.steats.steatspico.entities.restaurant.TimeSlot;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

import java.time.LocalDateTime;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class OrderManager implements OrderLocator, UserOrderHistory, OrderProcessing, TimeslotHandler {

    private PickupTimePublisher pickupTimePublisher = new PickupTimePublisher();
    final OrderRepository orderRepository;
    private OrderProcessingStrategy orderProcessingStrategy;
    public OrderManager(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
;
    }

    @Override
    public Order process(OrderDetails orderDetails)
            throws EmptyCartException, PaymentException, DeliveryDateNotAvailable,NoSuchElementException {
        Order order = orderProcessingStrategy.process(orderDetails);
        orderRepository.save(order, order.getId());
        orderDetails.getOrderOwner().getCart().emptyCart();
        pickupTimePublisher.subscribe(orderDetails.getRestaurant());
        pickupTimePublisher.notifySubscribers(order);
        return order;
    }


    @Override
    public List<Order> getPreviousOrders(CampusUser user) {
        List<Order> previousOrders = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            if (order.getCustomer().equals(user)) {
                previousOrders.add(order);
            }
        }
        return previousOrders;
    }
    @Override
    public List<Order> getOrdersByStatus(Restaurant restaurant, OrderStatus orderStatus) {
        List<Order> orders = new ArrayList<>();
        for (Order order : orderRepository.findAll()) {
            if (order.getStatus()!=null && order.getOrderDetails().menusOrdered().containsKey(restaurant) && order.getStatus().equals(orderStatus)) {
                orders.add(order);
            }
        }
        return orders;
    }
    @Override
    public void calculateTimeslot(Schedule schedule, LocalDateTime deliveryTime, Map<Menu, Integer> menusOrdered)
            throws DeliveryDateNotAvailable, EmptyCartException {

        int totalMenusNeeded = menusOrdered.values().stream().mapToInt(Integer::intValue).sum();
        if (totalMenusNeeded == 0){
            throw new EmptyCartException();
        }
        LocalDateTime currentTimeSlotStart = deliveryTime.minusHours(2);
        while (currentTimeSlotStart.isAfter(LocalDateTime.of(deliveryTime.toLocalDate(), schedule.getOpeningTime())) && totalMenusNeeded > 0) {
            Optional<TimeSlot> foundTimeslot = schedule.findTimeSlotByStartTime(currentTimeSlotStart);
            if (foundTimeslot.isPresent()) {
                TimeSlot timeslot = foundTimeslot.get();
                int availableCapacity = timeslot.getCapacity();
                if (availableCapacity > 0) {
                    int menusToAllocate = Math.min(totalMenusNeeded, availableCapacity);
                    timeslot.subtractCapacity(menusToAllocate);
                    totalMenusNeeded -= menusToAllocate;
                }
            } else {
                TimeSlot newTimeslot = new TimeSlot(currentTimeSlotStart, schedule.getMaxCapacity());
                schedule.getTimeSlots().add(newTimeslot);
                int menusToAllocate = Math.min(totalMenusNeeded, newTimeslot.getCapacity());
                newTimeslot.subtractCapacity(menusToAllocate);
                totalMenusNeeded -= menusToAllocate;
            }
            currentTimeSlotStart = currentTimeSlotStart.minusMinutes(Schedule.SLOT_DURATION_IN_MINUTES);
        }

        if (totalMenusNeeded > 0) {
            throw new DeliveryDateNotAvailable(deliveryTime);
        }
    }


    public DeliveryRegistry getDeliveryRegistry() {
        return ((SimpleOrderProcessingStrategy)this.orderProcessingStrategy).getDeliveryRegistry();
    }
    @Override
    public void setOrderProcessingStrategy(OrderProcessingStrategy orderProcessingStrategy) {
        this.orderProcessingStrategy = orderProcessingStrategy;
    }
}
