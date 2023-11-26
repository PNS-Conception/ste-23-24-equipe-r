package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.notification.*;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.order.Subscriber;
import fr.unice.polytech.steats.users.DeliveryPerson;

import java.util.*;

import static fr.unice.polytech.steats.delivery.DeliveryStatus.IN_PROGRESS;

public class Delivery {
    SimpleOrder simpleOrder;
    DeliveryPerson deliveryPerson;
    UUID id;
    DeliveryStatus status;
    private Subscriber subscriber ;



    public Delivery(SimpleOrder simpleOrder) {
        id = UUID.randomUUID();
        this.simpleOrder = simpleOrder;
        status = DeliveryStatus.WAITING;
    }

    public UUID getId() {
        return id;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
        if(status.equals(IN_PROGRESS)){
            notifySubscribers();
        }
    }

    public SimpleOrder getOrder() {
        return simpleOrder;
    }



    public void subscribe(Subscriber subscriber) {
        this.subscriber=subscriber;
    }

    public Subscriber getSubscriber() {
        return subscriber;
    }


    public void notifySubscribers() {

        if(this.getSubscriber()==null){
            this.subscriber = new NotificationRegistry(new NotificationRepository());
        }

        Notification deliveryNotification = new DeliveryNotification(this);
        subscriber.update(deliveryNotification);
        Notification userNotification = new UserDeliveryNotification(this);
        subscriber.update(userNotification);


    }


}
