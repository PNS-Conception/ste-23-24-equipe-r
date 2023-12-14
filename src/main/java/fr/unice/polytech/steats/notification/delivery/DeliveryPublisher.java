package fr.unice.polytech.steats.notification.delivery;

import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.notification.order.OrderSubscriber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DeliveryPublisher {

    private List<DeliverySubscriber> observers = new ArrayList<>();


    public List<DeliverySubscriber> getObservers(){
        return observers;
    }


    public void subscribe(DeliverySubscriber observer) {
        observers.add(observer);
    }

    public void unsubscribe(DeliverySubscriber observer) {
        observers.remove(observer);
    }

    public void notifySubscribers(Delivery delivery) {
        for (DeliverySubscriber observer : observers) {
            observer.update(delivery);
        }
    }


}
