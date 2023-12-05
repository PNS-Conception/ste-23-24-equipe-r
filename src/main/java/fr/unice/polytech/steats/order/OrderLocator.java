package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.util.List;

public interface OrderLocator {
    List<SimpleOrder> getOrdersByStatus(Restaurant restaurant, OrderStatus orderStatus);
}
