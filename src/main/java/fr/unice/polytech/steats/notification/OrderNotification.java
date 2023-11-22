package fr.unice.polytech.steats.notification;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.TimeSlot;

import java.util.UUID;

public class OrderNotification extends Notification {

//            event.put("orderId", orderID);
//        event.put("deliveryDate", deliveryLocation);
//        event.put("location", timeSlot)

    UUID orderId;
    DeliveryLocation deliveryLocation;
    TimeSlot timeSlot ;

    public OrderNotification(Order order) {
        this.setUserId(order.getCustomer().getId());
        this.orderId = order.getId();
        this.deliveryLocation = order.getDeliveryLocation();
        //this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "OrderNotification{" +
                "orderId=" + orderId +
                ", deliveryLocation=" + deliveryLocation +
                ", timeSlot=" + timeSlot +
                '}';
    }
}
