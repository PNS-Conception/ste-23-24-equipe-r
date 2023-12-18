package fr.unice.polytech.steats.steatspico.entities.notification;

import fr.unice.polytech.steats.steatspico.interfaces.users.OrderSubscriber;
import fr.unice.polytech.steats.steatspico.entities.order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderPublisher {
    private List<OrderSubscriber> observers = new ArrayList<>();

    public void subscribe(OrderSubscriber observer) {
        observers.add(observer);
    }

    public void unsubscribe(OrderSubscriber observer) {
        observers.remove(observer);
    }

    public void notifySubscribers(Order order) {
        for (OrderSubscriber observer : observers) {
            observer.update(order);
        }
    }

    public List<OrderSubscriber> getObservers() {
        return observers;
    }
}
