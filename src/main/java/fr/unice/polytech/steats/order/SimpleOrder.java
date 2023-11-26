package fr.unice.polytech.steats.order;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.TimeSlot;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class SimpleOrder implements Order {
    private final UUID orderID;
    private OrderStatus orderStatus;
    private Map<Menu, Integer> menusOrdered;
    private DeliveryLocation deliveryLocation;
    private TimeSlot timeSlot;
    private double discount = 0.1;
    Restaurant restaurant;
    CampusUser customer;
    LocalTime deliveryDate;
    private final List<Subscriber> subscribers = new ArrayList<>();


    public SimpleOrder(Restaurant restaurant, CampusUser customer, Map<Menu, Integer> menusOrdered, LocalTime deliveryDate,
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
        if(orderStatus.equals(OrderStatus.READY_FOR_DELIVERY)){
            notifySubscribers();
        }
    }

    public UUID getId() {
        return orderID;
    }

    public void setDeliveryLocation(DeliveryLocation deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }
    public DeliveryLocation getDeliveryLocation(){
        return this.deliveryLocation;
    }
    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }
    public TimeSlot getTimeSlot() {
        return this.timeSlot;
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

    public void subscribe(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void unsubscribe(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers() {
        Map<String, Object> event = new HashMap<>();
        event.put("orderId", orderID);
        event.put("deliveryDate", deliveryDate);
        event.put("location", deliveryLocation);

        for (Subscriber subscriber : subscribers) {
            subscriber.update(new Notification());
        }
    }


}