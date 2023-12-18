package fr.unice.polytech.steats.steatspico.components.notificationstrategy;


import fr.unice.polytech.steats.steatspico.entities.order.Order;

import java.util.Map;

public interface NotificationStrategy {
    Map<String, Object> sendNotification(Order order);
}
