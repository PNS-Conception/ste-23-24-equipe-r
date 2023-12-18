package fr.unice.polytech.steats.steatspico.entities.notification;

import fr.unice.polytech.steats.steatspico.interfaces.users.PickupTimeSubscriber;
import fr.unice.polytech.steats.steatspico.entities.order.Order;

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
