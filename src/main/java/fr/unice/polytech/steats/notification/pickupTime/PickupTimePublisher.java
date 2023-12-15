package fr.unice.polytech.steats.notification.pickupTime;

import fr.unice.polytech.steats.notification.order.OrderSubscriber;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Menu;

import java.util.ArrayList;
import java.util.List;

public class PickupTimePublisher {
    List<PickupTimeSubscriber> observers = new ArrayList<>();

    public void subscribe(PickupTimeSubscriber observer) {
        observers.add(observer);
    }

    public void unsubscribe(PickupTimeSubscriber observer) {
        observers.remove(observer);
    }

    public void notifySubscribers(Order order) {
        for (PickupTimeSubscriber observer : observers) {
            observer.update(order);
        }
    }

    public List<PickupTimeSubscriber> getObservers() {
        return observers;
    }
}
