package fr.unice.polytech.steats.steatspico.entities.order.factory;

import fr.unice.polytech.steats.steatspico.entities.order.SimpleOrder;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;

public class SimpleOrderFactory implements OrderFactory{
    @Override
    public SimpleOrder createOrder(OrderDetails orderDetails) {
        return new SimpleOrder(orderDetails);
    }
}
