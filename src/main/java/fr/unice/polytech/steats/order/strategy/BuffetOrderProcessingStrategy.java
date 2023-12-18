package fr.unice.polytech.steats.order.strategy;

import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.order.*;
import fr.unice.polytech.steats.order.factory.BuffetOrderFactory;
import fr.unice.polytech.steats.payment.Payment;

public class BuffetOrderProcessingStrategy implements OrderProcessingStrategy{
    final Payment payment;
    final DeliveryRegistry deliveryRegistry;
    TimeslotHandler timeslotHandler;
    BuffetOrderFactory buffetOrderFactory;
    public BuffetOrderProcessingStrategy(Payment payment, DeliveryRegistry deliveryRegistry, TimeslotHandler timeslotHandler) {
        this.payment = payment;
        this.deliveryRegistry = deliveryRegistry;
        this.timeslotHandler = timeslotHandler;
    }

    @Override
    public Order process(OrderDetails orderDetails) throws PaymentException, EmptyCartException, DeliveryDateNotAvailable {
        timeslotHandler.calculateTimeslot(orderDetails.getRestaurant().getSchedule(), orderDetails.getDeliveryTime(), orderDetails.menusOrdered().get(orderDetails.getRestaurant()));
        BuffetOrder order = (BuffetOrder) buffetOrderFactory.createOrder(orderDetails);
        order.setStatus(OrderStatus.PREPARING);
        payment.completePayment(orderDetails.getOrderOwner());
        deliveryRegistry.register(order);
        orderDetails.getOrderOwner().getCart().emptyCart();
        return order;
    }
}
