package fr.unice.polytech.steats.steatspico.entities.order.factory;

import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;

public interface OrderFactory {
    Order createOrder(OrderDetails orderDetails);
}
