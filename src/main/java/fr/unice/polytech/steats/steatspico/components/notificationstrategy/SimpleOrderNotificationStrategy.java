package fr.unice.polytech.steats.steatspico.components.notificationstrategy;


import fr.unice.polytech.steats.steatspico.components.notificationstrategy.NotificationStrategy;
import fr.unice.polytech.steats.steatspico.entities.order.Order;

import java.util.HashMap;
import java.util.Map;

public class SimpleOrderNotificationStrategy implements NotificationStrategy {

    @Override
    public Map<String, Object> sendNotification(Order order) {
        Map<String,Object> event = new HashMap<>();
        event.put("Order Id", order.getId());
        event.put("Delivery date", order.getDeliveryTime());
        event.put("Delivery location", order.getOrderDetails().getDeliveryLocation());
        return event;
    }
}
