package fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy;

import fr.unice.polytech.steats.steatspico.entities.order.AfterWorkOrder;
import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.OrderStatus;
import fr.unice.polytech.steats.steatspico.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.steatspico.exceptions.order.PaymentException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.TimeslotHandler;
import fr.unice.polytech.steats.steatspico.entities.order.factory.AfterWorkOrderFactory;

public class AfterWorkOrderProcessingStrategy implements OrderProcessingStrategy{
    TimeslotHandler timeslotHandler;
    AfterWorkOrderFactory afterWorkOrderFactory = new AfterWorkOrderFactory();
    public AfterWorkOrderProcessingStrategy(TimeslotHandler timeslotHandler) {
        this.timeslotHandler = timeslotHandler;
    }

    @Override
    public Order process(OrderDetails orderDetails) throws PaymentException, EmptyCartException, DeliveryDateNotAvailable {
        timeslotHandler.calculateTimeslot(orderDetails.getRestaurant().getSchedule(), orderDetails.getDeliveryTime(), orderDetails.menusOrdered().get(orderDetails.getRestaurant()));
        AfterWorkOrder order = (AfterWorkOrder) afterWorkOrderFactory.createOrder(orderDetails);
        order.setStatus(OrderStatus.PREPARING);
        orderDetails.getOrderOwner().getCart().emptyCart();
        return order;
    }
}
