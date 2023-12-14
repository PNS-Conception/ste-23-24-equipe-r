package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.delivery.Delivery;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.notification.NotificationRegistry;
import fr.unice.polytech.steats.notification.delivery.DeliverySubscriber;
import fr.unice.polytech.steats.notification.order.OrderSubscriber;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampusUser extends User implements DeliverySubscriber, OrderSubscriber {
    private Cart cart;
    // --Commented out by Inspection (28/11/2023 22:22):private double balance;

    private CampusUserStatus status;
    private NotificationRegistry notificationRegistry = NotificationRegistry.getInstance();

    // --Commented out by Inspection (28/11/2023 22:21):private List<SimpleOrder> previousSimpleOrders;



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
