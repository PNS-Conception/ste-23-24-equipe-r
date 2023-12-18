package fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy;

import fr.unice.polytech.steats.steatspico.components.DeliveryRegistry;
import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.OrderStatus;
import fr.unice.polytech.steats.steatspico.entities.order.SimpleOrder;
import fr.unice.polytech.steats.steatspico.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.steatspico.exceptions.order.PaymentException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.TimeslotHandler;
import fr.unice.polytech.steats.steatspico.entities.order.factory.SimpleOrderFactory;
import fr.unice.polytech.steats.steatspico.interfaces.payment.Payment;

public class SimpleOrderProcessingStrategy implements OrderProcessingStrategy{
    final Payment payment;
    final DeliveryRegistry deliveryRegistry;
    SimpleOrderFactory simpleOrderFactory = new SimpleOrderFactory();
    TimeslotHandler timeslotHandler;

    public SimpleOrderProcessingStrategy(Payment payment, DeliveryRegistry deliveryRegistry, TimeslotHandler timeslotHandler) {
        this.payment = payment;
        this.deliveryRegistry = deliveryRegistry;
        this.timeslotHandler = timeslotHandler;
    }

    @Override
    public Order process(OrderDetails orderDetails) throws PaymentException, EmptyCartException, DeliveryDateNotAvailable {
        timeslotHandler.calculateTimeslot(orderDetails.getRestaurant().getSchedule(), orderDetails.getDeliveryTime(), orderDetails.menusOrdered().get(orderDetails.getRestaurant()));
        SimpleOrder order = simpleOrderFactory.createOrder(orderDetails);
        order.setStatus(OrderStatus.PREPARING);
        payment.completePayment(orderDetails.getOrderOwner());
        deliveryRegistry.register(order);
        orderDetails.getOrderOwner().getCart().emptyCart();
        return order;
    }
    public DeliveryRegistry getDeliveryRegistry(){
        return this.deliveryRegistry;
    }
}
