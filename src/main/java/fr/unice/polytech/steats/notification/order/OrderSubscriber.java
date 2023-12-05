package fr.unice.polytech.steats.notification.order;

import fr.unice.polytech.steats.order.Order;

public interface OrderSubscriber {

    void update(Order order);
}
