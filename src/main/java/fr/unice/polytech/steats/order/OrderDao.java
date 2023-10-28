package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.repository.Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderDao implements Dao<Order> {

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
