package fr.unice.polytech.steats.order;

import java.util.List;
import java.util.UUID;

public interface IOrderService {
    List<Order> getOrdersForRestaurant(UUID restaurantId);
}
