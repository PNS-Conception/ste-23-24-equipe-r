package fr.unice.polytech.steats.service;

import fr.unice.polytech.steats.enumeration.OrderStatus;
import fr.unice.polytech.steats.model.CampusUser;
import fr.unice.polytech.steats.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderDao implements IDao<Order> {

    List<Order> orders;
    public OrderDao() {
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

    public OrderStatus getOrderStatus(UUID orderID, CampusUser user) {
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
