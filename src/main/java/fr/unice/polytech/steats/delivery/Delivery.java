package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.users.User;

import java.util.UUID;

import static fr.unice.polytech.steats.delivery.DeliveryStatus.IN_PROGRESS;

public class Delivery {
    Order order;
    User deliveryPerson;
    UUID id;
    DeliveryStatus status;

    public Delivery(Order order) {
        id = UUID.randomUUID();
        this.order = order;
        status = DeliveryStatus.WAITING;
    }

    public UUID getId() {
        return id;
    }

    public void setDeliveryPerson(User deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
        status = IN_PROGRESS;
    }

    public User getDeliveryPerson() {
        return deliveryPerson;
    }

    public DeliveryStatus getStatus() {
        return status;
    }
}
