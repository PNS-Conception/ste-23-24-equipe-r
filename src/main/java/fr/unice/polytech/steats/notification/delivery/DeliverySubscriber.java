package fr.unice.polytech.steats.notification.delivery;

import fr.unice.polytech.steats.delivery.Delivery;

public interface DeliverySubscriber {
    void update(Delivery delivery);
}
