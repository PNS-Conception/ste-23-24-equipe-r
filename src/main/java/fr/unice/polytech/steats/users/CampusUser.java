package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderSubscriber;
import fr.unice.polytech.steats.rating.RatingSystem;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.TimeSlot;

import java.util.*;

public class CampusUser extends User implements OrderSubscriber {
    private Cart cart;
    private double balance;

    private CampusUserStatus status;

    private List<Order> PreviousOrders ;

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

    public void setCart(Cart cart) {
        this.cart = cart;
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
    public void update(Map<String, Object> event) {
        // Handle the event in a way that makes sense for this subscriber (user)
        UUID orderID = (UUID)event.get("orderId");
        DeliveryLocation deliveryLocation = (DeliveryLocation)event.get("deliveryDate");
        TimeSlot timeSlot =(TimeSlot)event.get("location");
        System.out.println("The order : " + orderID);
        System.out.println("delivery location : "+deliveryLocation);
        System.out.println("delivery date : "+timeSlot);
    }
}
