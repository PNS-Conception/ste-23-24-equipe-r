package fr.unice.polytech.steats.steatspico.interfaces.order;

import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

import java.util.List;

public interface UserOrderHistory {
    List<Order> getPreviousOrders(CampusUser user);
}
