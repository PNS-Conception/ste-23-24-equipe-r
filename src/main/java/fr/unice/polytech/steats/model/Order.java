package fr.unice.polytech.steats.model;


import fr.unice.polytech.steats.enumeration.OrderStatus;

public class Order {

    private double price;
    private OrderStatus orderStatus;
    private String orderID;

    private int totalMenus;
    private CampusUser customer;

    public Order(String orderID, double price) {
        this.orderID = orderID;
        this.price = price;
        this.orderStatus = OrderStatus.CREATED;
        this.totalMenus = 0;
    }

    public Order(String orderID, double price,CampusUser customer) {
        this.orderID = orderID;
        this.price = price;
        this.orderStatus = OrderStatus.CREATED;
        this.totalMenus = 0;
        this.customer = customer;
    }

    public Order(String orderID,CampusUser customer) {
        this.orderID = orderID;
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

    public String getOrderID() {
        return orderID;
    }

    public int getTotalMenus() {
        return totalMenus;
    }

    public Object getCustomer() {
        return customer;
    }
}
