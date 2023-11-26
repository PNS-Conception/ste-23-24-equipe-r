package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.delivery.DeliveryLocation;

import java.util.UUID;

public interface Order {
    OrderStatus getStatus();

    void setStatus(OrderStatus orderStatus);

    UUID getId();

    void setDeliveryLocation(DeliveryLocation deliveryLocation);
    DeliveryLocation getDeliveryLocation();
}
