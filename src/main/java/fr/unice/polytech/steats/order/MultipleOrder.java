package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;

public class MultipleOrder extends Order{
    public MultipleOrder(List<Restaurant> restaurants, CampusUser customer, Map<Menu, Integer> menusOrdered, LocalDateTime deliveryDate,
                         DeliveryLocation deliveryLocation) {
        super(restaurants, singletonList(customer), menusOrdered, deliveryDate, deliveryLocation);
    }
}
