package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.exceptions.order.ClosedGroupOrderException;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.NonExistentGroupOrder;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.util.Map;
import java.util.Optional;

public interface SubOrderManager {
    void addSubOrder(String groupOrderCode, Restaurant restaurant,
                     CampusUser customer, Map<Menu, Integer> menusOrdered)
            throws NonExistentGroupOrder, ClosedGroupOrderException, EmptyCartException, PaymentException, DeliveryDateNotAvailable;

    Optional<SimpleOrder> locateSubOrder(GroupOrder groupOrder, CampusUser customer);
}
