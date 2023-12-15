package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.notification.NotificationRegistry;
import fr.unice.polytech.steats.notification.delivery.DeliverySubscriber;
import fr.unice.polytech.steats.order.Order;

import java.util.HashMap;
import java.util.Map;

public class DeliveryPerson extends User implements DeliverySubscriber {
    String phoneNumber;
    NotificationRegistry notificationRegistry;


    public DeliveryPerson(String deliveryPersonName) {
        super(deliveryPersonName,UserRole.DELIVERY_PERSON);
        notificationRegistry = NotificationRegistry.getInstance();
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(Delivery delivery) {
        Order order = delivery.getOrder();
        Map<String, Object> event = new HashMap<>();
        event.put("Username", order.getCustomer());
        event.put("Delivery location", order.getOrderDetails().getDeliveryLocation());
        event.put("Involved restaurant", order.getOrderDetails().getRestaurant());
        Notification<User> notification = new Notification(event, this);
        notificationRegistry.add(notification);
    }
}
