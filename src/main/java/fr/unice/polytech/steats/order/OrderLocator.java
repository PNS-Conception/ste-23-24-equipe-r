package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.restaurant.Restaurant;

import java.util.List;

public interface OrderLocator {
    List<Order> getOrdersByStatus(Restaurant restaurant, OrderStatus orderStatus);
}
