package fr.unice.polytech.steats.order;
import fr.unice.polytech.steats.notification.Notification;
import fr.unice.polytech.steats.notification.OrderNotification;
import fr.unice.polytech.steats.order.grouporder.GroupOrder;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.TimeSlot;

import java.time.LocalDateTime;
import java.util.*;

public class Order {
    private UUID orderID;
    private OrderStatus orderStatus;
    private Restaurant restaurant;
    private CampusUser customer;
    private Map<Menu, Integer> menusOrdered;
    private DeliveryLocation deliveryLocation;
    private TimeSlot timeSlot;
    private GroupOrder groupOrder;
    private double discount = 0.1;


    LocalDateTime deliveryDate ;


    private Subscriber subscriber;





    public Order(Restaurant restaurant, CampusUser customer, Map<Menu, Integer> menusOrdered,
                 DeliveryLocation deliveryLocation, TimeSlot timeslot){
        this.orderID = UUID.randomUUID();
        this.restaurant = restaurant;
        this.customer = customer;
        this.menusOrdered = menusOrdered;
        this.deliveryLocation = deliveryLocation;
        this.timeSlot = timeslot;
        this.orderStatus = OrderStatus.WAITING_FOR_PREPARATION;
        OrderVolume.getInstance().addOrder(this);
    }

    public Order(Restaurant restaurant,Menu mn) {
        this.orderID = UUID.randomUUID();
        this.restaurant = restaurant;
        menusOrdered = new HashMap<>();
        menusOrdered.put(mn,1);
        this.orderStatus = OrderStatus.WAITING_FOR_PREPARATION;
        OrderVolume.getInstance().addOrder(this);
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
    public CampusUser getCustomer() {
        return customer;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }


    public Menu getMenu(){
        return menusOrdered.keySet().iterator().next();
    }

    public void subscribe(Subscriber subscriber) {
        this.subscriber=subscriber;
    }


    public void notifySubscribers() {
        Notification notification = new OrderNotification(this);

        subscriber.update(notification);

    }
}
