package fr.unice.polytech.steats.model;


import fr.unice.polytech.steats.enumeration.OrderStatus;

public class Order {

    private double price;
    private OrderStatus orderStatus;
    private String orderID;

    private int totalMenus;

    public Order(String orderID, double price) {
        this.orderID = orderID;
        this.price = price;
        this.orderStatus = OrderStatus.CREATED;
        this.totalMenus = 0;
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
}
