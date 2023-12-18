package fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy;

import fr.unice.polytech.steats.steatspico.components.DeliveryRegistry;
import fr.unice.polytech.steats.steatspico.entities.order.BuffetOrder;
import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.OrderStatus;
import fr.unice.polytech.steats.steatspico.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.steatspico.exceptions.order.PaymentException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.TimeslotHandler;
import fr.unice.polytech.steats.steatspico.entities.order.factory.BuffetOrderFactory;
import fr.unice.polytech.steats.steatspico.interfaces.payment.Payment;

public class BuffetOrderProcessingStrategy implements OrderProcessingStrategy{
    final Payment payment;
    final DeliveryRegistry deliveryRegistry;
    TimeslotHandler timeslotHandler;
    BuffetOrderFactory buffetOrderFactory = new BuffetOrderFactory();
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
