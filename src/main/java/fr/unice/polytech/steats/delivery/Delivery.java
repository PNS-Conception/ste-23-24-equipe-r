package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.notification.delivery.DeliveryPublisher;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.users.DeliveryPerson;

import java.util.*;

import static fr.unice.polytech.steats.delivery.DeliveryStatus.READY;

public class Delivery {
    Order order;
    DeliveryPerson deliveryPerson;
    final UUID id;
    DeliveryStatus status;

    DeliveryPublisher deliveryPublisher;



    public Delivery(SimpleOrder order) {
        id = UUID.randomUUID();
        this.order = order;
        status = DeliveryStatus.WAITING;
        deliveryPublisher = new DeliveryPublisher();
    }

    public void setReady(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        setStatus(READY);
        deliveryPublisher.notifySubscribers(this);
        deliveryPublisher.subscribe(deliveryPerson);
    }


    public DeliveryPublisher getDeliveryPublisher() {
        return deliveryPublisher;
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
        deliveryPublisher.notifySubscribers(this);
    }

    public Order getOrder() {
        return order;
    }


}
