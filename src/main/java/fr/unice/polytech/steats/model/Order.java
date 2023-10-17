package fr.unice.polytech.steats.model;


import fr.unice.polytech.steats.enumeration.OrderStatus;

import java.util.UUID;

public class Order {

    private double price;
    private OrderStatus orderStatus;
    private UUID orderID;

    private int totalMenus;
    private CampusUser customer;

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

    public Object getCustomer() {
        return customer;
    }
}
