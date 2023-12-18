package fr.unice.polytech.steats.order.strategy;

import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.order.*;
import fr.unice.polytech.steats.order.factory.MultipleOrderFactory;
import fr.unice.polytech.steats.payment.Payment;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;

import java.util.Map;

public class MultipleOrderProcessingStrategy implements OrderProcessingStrategy{
    final Payment payment;
    final DeliveryRegistry deliveryRegistry;
    TimeslotHandler timeslotHandler;
    MultipleOrderFactory multipleOrderFactory;

    public MultipleOrderProcessingStrategy(Payment payment, DeliveryRegistry deliveryRegistry, TimeslotHandler timeslotHandler, MultipleOrderFactory multipleOrderFactory) {
        this.payment = payment;
        this.deliveryRegistry = deliveryRegistry;
        this.timeslotHandler = timeslotHandler;
        this.multipleOrderFactory = multipleOrderFactory;
    }

    @Override
    public Order process(OrderDetails orderDetails) throws PaymentException, EmptyCartException, DeliveryDateNotAvailable {
        for(Map.Entry<Restaurant, Map<Menu, Integer>> entry : orderDetails.menusOrdered().entrySet()) {
            Restaurant restaurant = entry.getKey();
            Map<Menu, Integer> menuIntegerMap = entry.getValue();
            timeslotHandler.calculateTimeslot(restaurant.getSchedule(), orderDetails.getDeliveryTime(), menuIntegerMap);
        }
        MultipleOrder multipleOrder = multipleOrderFactory.createOrder(orderDetails);
        multipleOrder.setStatus(OrderStatus.PREPARING);
        payment.completePayment(orderDetails.getOrderOwner());
        orderDetails.getOrderOwner().getCart().emptyCart();
        deliveryRegistry.register(multipleOrder);
        return multipleOrder;
    }
}
