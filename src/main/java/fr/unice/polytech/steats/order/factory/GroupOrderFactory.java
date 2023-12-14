package fr.unice.polytech.steats.order.factory;

import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderDetails;
import fr.unice.polytech.steats.order.grouporder.GroupOrder;

public class GroupOrderFactory implements OrderFactory {

    @Override
    public Order createOrder(OrderDetails orderDetails) {
        return new GroupOrder(orderDetails);
    }
}
