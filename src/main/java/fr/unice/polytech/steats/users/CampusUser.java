package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Timeslot;

import java.util.*;

public class CampusUser extends User {
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
    public String toString() {
        return "CampusUser{" +
                "cart=" + cart +
                ", balance=" + balance +
                ", status=" + status +
                ", PreviousOrders=" + PreviousOrders +
                '}';
    }
}
