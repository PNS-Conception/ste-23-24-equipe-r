package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.users.User;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Order {
    OrderStatus getStatus();

    void setStatus(OrderStatus orderStatus);
    User getCustomer();

    UUID getId();

    // --Commented out by Inspection (28/11/2023 22:38):void setDeliveryLocation(DeliveryLocation deliveryLocation);
    DeliveryLocation getDeliveryLocation();
    LocalDateTime getDeliveryTime();
}
