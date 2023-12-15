package fr.unice.polytech.steats.notification.pickupTime;

import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Menu;

import javax.print.attribute.standard.MediaSize;

public interface PickupTimeSubscriber {

    void update(Order order);
}
