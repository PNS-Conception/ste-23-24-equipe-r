package fr.unice.polytech.steats.notification.strategy.order;

import fr.unice.polytech.steats.order.Order;

import java.util.Map;

public interface NotificationStrategy {
    Map<String, Object> sendNotification(Order order);
}
