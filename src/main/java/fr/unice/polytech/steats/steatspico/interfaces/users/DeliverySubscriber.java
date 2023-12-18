package fr.unice.polytech.steats.steatspico.interfaces.users;

import fr.unice.polytech.steats.steatspico.entities.delivery.Delivery;

public interface DeliverySubscriber {
    void update(Delivery delivery);
}
