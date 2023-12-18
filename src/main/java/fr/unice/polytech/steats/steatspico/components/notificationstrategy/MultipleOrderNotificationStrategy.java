package fr.unice.polytech.steats.steatspico.components.notificationstrategy;

import fr.unice.polytech.steats.steatspico.entities.order.Order;

import java.util.HashMap;
import java.util.Map;

public class MultipleOrderNotificationStrategy implements NotificationStrategy {
    @Override
    public Map<String, Object> sendNotification(Order order) {
        Map<String, Object> event = new HashMap<>();
        event.put("Order Id", order.getId());
        event.put("Order Owner", order.getOrderDetails().getOrderOwner());
        event.put("Number of Restaurants", order.getOrderDetails().getRestaurants().size());
        event.put("Restaurants", order.getOrderDetails().getRestaurants());
        event.put("Delivery date", order.getDeliveryTime());
        event.put("Delivery location", order.getOrderDetails().getDeliveryLocation());
        return event;
    }
}
