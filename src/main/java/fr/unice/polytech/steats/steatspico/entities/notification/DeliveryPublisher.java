package fr.unice.polytech.steats.steatspico.entities.notification;

import fr.unice.polytech.steats.steatspico.entities.delivery.Delivery;
import fr.unice.polytech.steats.steatspico.interfaces.users.DeliverySubscriber;

import java.util.ArrayList;
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
