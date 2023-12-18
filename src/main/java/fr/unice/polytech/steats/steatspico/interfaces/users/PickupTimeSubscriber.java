package fr.unice.polytech.steats.steatspico.interfaces.users;

import fr.unice.polytech.steats.steatspico.entities.order.Order;

public interface PickupTimeSubscriber {

    void update(Order order);
}
