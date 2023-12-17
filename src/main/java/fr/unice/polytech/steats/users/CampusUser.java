package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.notification.NotificationRegistry;
import fr.unice.polytech.steats.notification.delivery.DeliverySubscriber;
import fr.unice.polytech.steats.notification.order.OrderSubscriber;
import fr.unice.polytech.steats.notification.strategy.NotificationStrategy;
import fr.unice.polytech.steats.notification.strategy.SimpleOrderNotificationStrategy;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Menu;

import java.util.HashMap;
import java.util.Map;

public class CampusUser extends User implements DeliverySubscriber, OrderSubscriber {
    private Cart cart;

    private CampusUserStatus status;
    private NotificationRegistry notificationRegistry = NotificationRegistry.getInstance();

    private NotificationStrategy notificationStrategy = new SimpleOrderNotificationStrategy();



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


    public void addMenuToCart(Menu mn){
        this.cart.addMenu(mn);
    }

    public CampusUserStatus getStatus() {
        return status;
    }
    public void setStatus(CampusUserStatus status) {
        this.status = status;
    }

    public void setNotificationStrategy(NotificationStrategy notificationStrategy){
        this.notificationStrategy = notificationStrategy;
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
        if (notificationStrategy != null) {
            Map<String, Object> event = notificationStrategy.sendNotification(order);
            Notification notification = new Notification(event, this);
            notificationRegistry.add(notification);
        } else {
            System.out.println("No notification strategy set for user: " + this.getName()+ " : "+this.getId());
        }

    }
}
