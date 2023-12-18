package fr.unice.polytech.steats.steatspico.entities.order.factory;

import fr.unice.polytech.steats.steatspico.entities.order.AfterWorkOrder;
import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;

public class AfterWorkOrderFactory implements OrderFactory{
    @Override
    public Order createOrder(OrderDetails orderDetails) {
        return new AfterWorkOrder(orderDetails);
    }
}
