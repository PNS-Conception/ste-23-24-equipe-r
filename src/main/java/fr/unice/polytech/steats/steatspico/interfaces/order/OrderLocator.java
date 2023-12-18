package fr.unice.polytech.steats.steatspico.interfaces.order;

import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderStatus;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;

import java.util.List;

public interface OrderLocator {
    List<Order> getOrdersByStatus(Restaurant restaurant, OrderStatus orderStatus);
}
