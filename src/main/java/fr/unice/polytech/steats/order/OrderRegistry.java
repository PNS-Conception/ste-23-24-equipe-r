package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.payment.PaymentManager;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderRegistry {
    PaymentManager paymentManager;
    OrderRepository orderRepository;
    public OrderRegistry(OrderRepository orderRepository, PaymentManager paymentManager){
        this.orderRepository = orderRepository;
        this.paymentManager = paymentManager;
    }
    public Order register(Restaurant restaurant, CampusUser customer, Map<Menu, Integer> menusOrdered,
                          TimeSlot timeSlot, DeliveryLocation deliveryLocation)
            throws InsufficientTimeSlotCapacity, NonExistentTimeSlot, PaymentException, EmptyCartException {
        isValidOrder(restaurant, timeSlot, menusOrdered);
        Map<Menu, Integer> menusOrderedCopy = menusOrdered.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> new Menu(entry.getKey()),  // Using the copy constructor
                        Map.Entry::getValue
                ));
        Order order = new Order(restaurant, customer, menusOrderedCopy, deliveryLocation, timeSlot);
        order.setStatus(OrderStatus.PREPARING);
        paymentManager.completePayment(customer);
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
            } else {
                timeslot.setCapacity(timeslot.getCapacity() - menusNumber);
            }
        }else {
            throw new NonExistentTimeSlot(timeslot);
        }
    }
}