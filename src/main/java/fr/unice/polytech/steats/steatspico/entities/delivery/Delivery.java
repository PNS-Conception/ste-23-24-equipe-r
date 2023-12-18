package fr.unice.polytech.steats.steatspico.entities.delivery;

import fr.unice.polytech.steats.steatspico.entities.notification.DeliveryPublisher;
import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.users.DeliveryPerson;

import java.util.*;

import static fr.unice.polytech.steats.steatspico.entities.delivery.DeliveryStatus.READY;

public class Delivery {
    Order order;
    DeliveryPerson deliveryPerson;
    final UUID id;
    DeliveryStatus status;

    public DeliveryPublisher deliveryPublisher;



    public Delivery(Order order) {
        id = UUID.randomUUID();
        this.order = order;
        status = DeliveryStatus.WAITING;
        deliveryPublisher = new DeliveryPublisher();
    }

    public void setReady(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        setStatus(READY);
        deliveryPublisher.subscribe(deliveryPerson);
        deliveryPublisher.notifySubscribers(this);
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
