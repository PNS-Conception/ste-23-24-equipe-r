package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.notification.DeliveryNotification;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.notification.NotificationRegistry;
import fr.unice.polytech.steats.notification.UserDeliveryNotification;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.Subscriber;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.DeliveryPerson;

import java.util.*;

import static fr.unice.polytech.steats.delivery.DeliveryStatus.IN_PROGRESS;

public class Delivery {
    Order order;
    DeliveryPerson deliveryPerson;
    UUID id;
    DeliveryStatus status;
    private Subscriber subscriber ;



    public Delivery(Order order) {
        id = UUID.randomUUID();
        this.order = order;
        status = DeliveryStatus.WAITING;
    }

    public UUID getId() {
        return id;
    }

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        status = IN_PROGRESS;
        notifySubscribers();
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

    public Order getOrder() {
        return order;
    }



    public void subscribe(Subscriber subscriber) {
        this.subscriber=subscriber;
    }


    public void notifySubscribers() {

        Notification deliveryNotification = new DeliveryNotification(this);
        subscriber.update(deliveryNotification);
        Notification userNotification = new UserDeliveryNotification(this);
        subscriber.update(userNotification);


    }


}
