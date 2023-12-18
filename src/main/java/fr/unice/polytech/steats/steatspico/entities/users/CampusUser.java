package fr.unice.polytech.steats.steatspico.entities.users;

import fr.unice.polytech.steats.steatspico.entities.cart.Cart;
import fr.unice.polytech.steats.steatspico.entities.delivery.Delivery;
import fr.unice.polytech.steats.steatspico.entities.notification.Notification;
import fr.unice.polytech.steats.steatspico.components.NotificationRegistry;
import fr.unice.polytech.steats.steatspico.interfaces.users.DeliverySubscriber;
import fr.unice.polytech.steats.steatspico.interfaces.users.OrderSubscriber;
import fr.unice.polytech.steats.steatspico.entities.order.Order;

import java.util.HashMap;
import java.util.Map;

public class CampusUser extends User implements DeliverySubscriber, OrderSubscriber {
    private Cart cart;

    private CampusUserStatus status;
    private NotificationRegistry notificationRegistry = NotificationRegistry.getInstance();



    public CampusUser() {
        super();
        this.cart = new Cart();
        this.status = CampusUserStatus.EXTERNAL;
    }

    public CampusUser(String name) {
        super(name);
        this.cart = new Cart();
        this.status = CampusUserStatus.EXTERNAL;
    }

    public CampusUser(String name, CampusUserStatus status) {
        super(name);
        this.cart = new Cart();
        this.status = status;
    }


    public Cart getCart() {
        return this.cart;
    }

    public CampusUserStatus getStatus() {
        return status;
    }
    public void setStatus(CampusUserStatus status) {
        this.status = status;
    }

    @Override
    public void update(Delivery delivery) {
        Map<String, Object> event = new HashMap<>();
        event.put("Delivery person Id", delivery.getDeliveryPerson().getId());
        event.put("Delivery person phone number", delivery.getDeliveryPerson().getPhoneNumber());
        Notification notification = new Notification(event, this);
        notificationRegistry.add(notification);
    }

    @Override
    public void update(Order order) {
        Map<String,Object> event = new HashMap<>();
        event.put("Order Id", order.getId());
        event.put("Delivery date", order.getDeliveryTime());
        event.put("Delivery location", order.getOrderDetails().getDeliveryLocation());
        Notification notification = new Notification(event, this);
        notificationRegistry.add(notification);
    }
}
