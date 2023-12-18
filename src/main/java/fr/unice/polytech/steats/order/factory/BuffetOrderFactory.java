package fr.unice.polytech.steats.order.factory;

import fr.unice.polytech.steats.order.BuffetOrder;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderDetails;

public class BuffetOrderFactory implements OrderFactory{
    @Override
    public Order createOrder(OrderDetails orderDetails) {
        return new BuffetOrder(orderDetails);
    }
}
