package fr.unice.polytech.steats.delivery;

import java.util.Map;

public interface DeliverySubscriber {
    void updateDelivery(Map<String, Object> event);
}
