package fr.unice.polytech.steats.notification;

import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.restaurant.TimeSlot;

public class DeliveryNotification extends Notification{

    TimeSlot timeSlot;
    String restaurantName;
    DeliveryLocation deliveryLocation;
    String customerName ;


    public DeliveryNotification(Delivery delivery) {
        SimpleOrder simpleOrder = delivery.getOrder();
        //this.timeSlot = order.getTimeSlot();
        this.restaurantName = simpleOrder.getRestaurant().getRestaurantName();
        this.deliveryLocation = simpleOrder.getDeliveryLocation() ;
        this.customerName = simpleOrder.getCustomer().getName();
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
