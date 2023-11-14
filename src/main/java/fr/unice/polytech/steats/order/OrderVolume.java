package fr.unice.polytech.steats.order;

import java.util.ArrayList;
import java.util.List;

public class OrderVolume {
    private static OrderVolume instance = null;
    private List<Order> orderVolume;

    // Private constructor to prevent instantiation
    private OrderVolume() {
        this.orderVolume = new ArrayList<>();
    }

    // Static method to get the singleton instance
    public static OrderVolume getInstance() {
        if (instance == null) {
            instance = new OrderVolume();
        }
        return instance;
    }

    public List<Order> getOrderVolume() {
        return orderVolume;
    }

    public void addOrder(Order order) {
        orderVolume.add(order);
    }
}
