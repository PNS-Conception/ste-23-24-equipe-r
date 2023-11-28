package fr.unice.polytech.steats.notification;

import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Timeslot;

import java.util.Map;
import java.util.UUID;

public class DeliveryNotification extends Notification{

    Timeslot timeSlot;
    String restaurantName;
    DeliveryLocation deliveryLocation;
    String customerName ;


    public DeliveryNotification(Delivery delivery) {
        Order order = delivery.getOrder();
        //this.timeSlot = order.getTimeSlot();
        this.restaurantName = order.getRestaurant().getRestaurantName();
        this.deliveryLocation = order.getDeliveryLocation() ;
        this.customerName = order.getCustomer().getName();
        this.setUserId(delivery.getDeliveryPerson().getId());
    }

    @Override
    public String toString() {
        return "DeliveryNotification{" +
                "timeSlot=" + timeSlot +
                ", restaurantName='" + restaurantName + '\'' +
                ", deliveryLocation=" + deliveryLocation +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
