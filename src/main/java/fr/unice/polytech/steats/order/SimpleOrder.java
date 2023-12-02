
package fr.unice.polytech.steats.order;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.users.User;

import java.time.LocalDateTime;
import java.util.*;

public class SimpleOrder implements Order {
    private final UUID orderID;
    private OrderStatus orderStatus;
    private final Map<Menu, Integer> menusOrdered;
    private final DeliveryLocation deliveryLocation;
    private final double discount = 0.1;
    final Restaurant restaurant;
    final CampusUser customer;
    final LocalDateTime deliveryDate;
    private List<Subscriber> subscribers = new ArrayList<>();

    public LocalDateTime getDeliveryTime(){
        return deliveryDate;
    }


    public SimpleOrder(Restaurant restaurant, CampusUser customer, Map<Menu, Integer> menusOrdered, LocalDateTime deliveryDate,
                       DeliveryLocation deliveryLocation){
        this.orderID = UUID.randomUUID();
        this.menusOrdered = menusOrdered;
        this.deliveryLocation = deliveryLocation;
        this.orderStatus = OrderStatus.WAITING_FOR_PREPARATION;
        this.restaurant = restaurant;
        this.customer= customer;
        this.deliveryDate = deliveryDate;
        OrderVolume.getInstance().addOrder(this);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    public CampusUser getCustomer() {
        return customer;
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UUID getId() {
        return orderID;
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

    public void subscribe(Subscriber subscriber) {
        if(subscribers==null){
            subscribers = new ArrayList<>();
        }
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(Map<String, Object> event, List<User> users) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(new Notification(event, users));
        }
    }


}