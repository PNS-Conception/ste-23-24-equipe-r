
package fr.unice.polytech.steats.order;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.delivery.DeliveryLocation;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.Collections.singletonList;

public class SimpleOrder extends Order {
        public SimpleOrder(Restaurant restaurant, CampusUser customer, Map<Menu, Integer> menusOrdered, LocalDateTime deliveryDate,
                           DeliveryLocation deliveryLocation) {
            super(singletonList(restaurant), singletonList(customer), menusOrdered, deliveryDate, deliveryLocation);
        }
}