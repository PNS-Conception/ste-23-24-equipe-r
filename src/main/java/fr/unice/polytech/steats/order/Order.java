package fr.unice.polytech.steats.order;


import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.delivery.TimeSlot;

import java.util.UUID;

public class Order {

    private double price;
    private OrderStatus orderStatus;
    private UUID orderID;

    private int totalMenus;
    private CampusUser customer;
    private DeliveryLocation deliveryLocation;
    private TimeSlot timeSlot;

    public Order(){
        this.orderID = UUID.randomUUID();
        this.orderStatus = OrderStatus.CREATED;
        this.totalMenus = 0;
    }

    public Order(double price) {
        this.orderID = UUID.randomUUID();
        this.price = price;
        this.orderStatus = OrderStatus.CREATED;
        this.totalMenus = 0;
    }

    public Order(double price,CampusUser customer) {
        this.orderID = UUID.randomUUID();
        this.price = price;
        this.orderStatus = OrderStatus.CREATED;
        this.totalMenus = 0;
        this.customer = customer;
    }

    public Order(CampusUser customer) {
        this.orderID = UUID.randomUUID();
        this.orderStatus = OrderStatus.CREATED;
        this.totalMenus = 0;
        this.customer = customer;
    }

    public double getPrice() {
        return price;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UUID getOrderID() {
        return orderID;
    }

    public int getTotalMenus() {
        return totalMenus;
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
}
