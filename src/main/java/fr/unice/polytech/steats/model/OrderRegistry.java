package fr.unice.polytech.steats.model;

import fr.unice.polytech.steats.enumeration.OrderStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRegistry implements Dao<Order>{

    List<Order> orders;

    public OrderRegistry(List<Order> orders) {
        this.orders = orders;
    }

    public OrderRegistry() {
        orders = new ArrayList<>();
    }

    public List<Order> getAll() {
        return orders;
    }

    public void save(Order order) {
        orders.add(order);
    }

    public void update(Order order, String[] params) {

    }

    public void delete(Order order) {
        orders.remove(order);
    }

    public Optional<Order> get(String orderID) {
        for (Order order : orders) {
            if (order.getOrderID().equals(orderID)) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    public OrderStatus getOrderStatus(String orderID, CampusUser user) {
        for (Order order : orders) {
            if (order.getOrderID().equals(orderID)) {
                if(order.getCustomer().equals(user)) {
                    return order.getOrderStatus();
                }
                else System.out.println("You are not the customer of this order");
            }
        }
        return null;
    }


}
