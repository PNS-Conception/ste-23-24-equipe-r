package fr.unice.polytech.steats.order;
import fr.unice.polytech.steats.order.grouporder.GroupOrder;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.TimeSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    private UUID orderID;
    private OrderStatus orderStatus;
    private Restaurant restaurant;
    private CampusUser customer;
    private Map<Menu, Integer> menusOrdered;
    private DeliveryLocation deliveryLocation;
    private TimeSlot timeSlot;
    private GroupOrder groupOrder;

    public Order(){
        this.orderID = UUID.randomUUID();
        this.customer = new CampusUser();
        this.menusOrdered = new HashMap<>();
        this.deliveryLocation =DeliveryLocation.LIBRARY;
        this.timeSlot = null;
    }

    public Order(Restaurant restaurant, CampusUser customer, Map<Menu, Integer> menusOrdered,
                 DeliveryLocation deliveryLocation, TimeSlot timeslot){
        this.orderID = UUID.randomUUID();
        this.customer = customer;
        this.menusOrdered = menusOrdered;
        this.deliveryLocation = deliveryLocation;
        this.timeSlot = timeslot;
    }
    public Order(Restaurant restaurant, CampusUser customer, Map<Menu, Integer> menusOrdered,
                 GroupOrder groupOrder){
        this.orderID = UUID.randomUUID();
        this.customer = customer;
        this.menusOrdered = menusOrdered;
        this.groupOrder = groupOrder;
        this.deliveryLocation = groupOrder.getDeliveryLocation();
        this.timeSlot = groupOrder.getTimeSlot();
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
    public CampusUser getCustomer() {
        return customer;
    }

    public void setDeliveryLocation(DeliveryLocation deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
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
        return total;
    }
}
