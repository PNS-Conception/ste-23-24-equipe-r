package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.notification.order.OrderPublisher;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Order {
    private final UUID orderID;
    private OrderStatus orderStatus;
    private Map<Menu, Integer> menusOrdered;
    private DeliveryLocation deliveryLocation;
    private double discount = 0.1;
    List<Restaurant> restaurants;
    List<CampusUser> customers;
    LocalDateTime deliveryDate;
    private OrderPublisher orderPublisher;

    protected Order(List<Restaurant> restaurants, List<CampusUser> customers, Map<Menu, Integer> menusOrdered, LocalDateTime deliveryDate,
                    DeliveryLocation deliveryLocation){
        this.orderID = UUID.randomUUID();
        this.menusOrdered = menusOrdered;
        this.deliveryLocation = deliveryLocation;
        this.orderStatus = OrderStatus.WAITING_FOR_PREPARATION;
        this.restaurants = restaurants;
        this.customers = customers;
        this.deliveryDate = deliveryDate;
        OrderVolume.getInstance().addOrder((SimpleOrder) this);
        this.orderPublisher = new OrderPublisher();
        for(CampusUser user : customers){
            this.orderPublisher.subscribe(user);
        }
    }



    public LocalDateTime getDeliveryTime(){
        return deliveryDate;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }
    public List<CampusUser> getCustomers() {
        return customers;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.orderPublisher.notifySubscribers(this);
    }

    public UUID getId() {
        return orderID;
    }

    public void setDeliveryLocation(DeliveryLocation deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
        this.orderPublisher.notifySubscribers(this);
    }
    public DeliveryLocation getDeliveryLocation(){
        return this.deliveryLocation;
    }
    public int getTotalMenus(){
        int sum = 0;
        for (int value : menusOrdered.values()) {
            sum += value;
        }
        return sum;
    }

    public void setDiscount(double discount){
        this.discount = discount;
    }

    public double getPrice(){
        double total = 0;
        for (Map.Entry<Menu, Integer> entry : menusOrdered.entrySet()) {
            Menu menu = entry.getKey();
            int quantity = entry.getValue();
            total += menu.getBasePrice() * quantity;
        }
        if(getTotalMenus()>=10){
            return total-(total*discount);
        }
        return total;
    }
    public Menu getMenu(){
        return menusOrdered.keySet().iterator().next();
    }

}
