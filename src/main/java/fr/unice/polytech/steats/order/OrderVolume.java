package fr.unice.polytech.steats.order;

import java.util.ArrayList;
import java.util.List;

public class OrderVolume {
    private static OrderVolume instance = null;
    private final List<Order> simpleOrderVolume;

    // Private constructor to prevent instantiation
    private OrderVolume() {
        this.simpleOrderVolume = new ArrayList<>();
    }

    // Static method to get the singleton instance
    public static OrderVolume getInstance() {
        if (instance == null) {
            instance = new OrderVolume();
        }
        return instance;
    }

    public List<Order> getOrderVolume() {
        return simpleOrderVolume;
    }

    public void addOrder(Order Order) {
        simpleOrderVolume.add(Order);
    }
}
