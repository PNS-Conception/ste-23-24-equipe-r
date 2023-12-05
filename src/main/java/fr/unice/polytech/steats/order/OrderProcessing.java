package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.NoSuchElementException;

public interface OrderProcessing {
    SimpleOrder process(Restaurant restaurant, CampusUser customer, Map<Menu, Integer> menusOrdered,
                        LocalDateTime deliveryDateTime, DeliveryLocation deliveryLocation)
            throws EmptyCartException, PaymentException, DeliveryDateNotAvailable, NoSuchElementException;
}
