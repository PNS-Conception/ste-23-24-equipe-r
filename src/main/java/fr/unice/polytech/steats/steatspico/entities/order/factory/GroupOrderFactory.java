package fr.unice.polytech.steats.steatspico.entities.order.factory;

import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.GroupOrder;

public class GroupOrderFactory implements OrderFactory {

    @Override
    public Order createOrder(OrderDetails orderDetails) {
        return new GroupOrder(orderDetails);
    }
}
