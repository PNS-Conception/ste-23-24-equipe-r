package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.users.CampusUser;

import java.util.List;

public interface UserOrderHistory {
    List<SimpleOrder> getPreviousOrders(CampusUser user);
}
