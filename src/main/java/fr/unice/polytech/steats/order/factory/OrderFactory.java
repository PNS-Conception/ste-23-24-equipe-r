package fr.unice.polytech.steats.order.factory;

import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderDetails;

public interface OrderFactory {
    Order createOrder(OrderDetails orderDetails);
}
