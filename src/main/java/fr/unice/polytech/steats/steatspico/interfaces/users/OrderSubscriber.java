package fr.unice.polytech.steats.steatspico.interfaces.users;

import fr.unice.polytech.steats.steatspico.entities.order.Order;

public interface OrderSubscriber {
    void update(Order order);
}
