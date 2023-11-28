package fr.unice.polytech.steats.order.factory;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalDateTime;
import java.util.Map;

public class SimpleOrderFactory implements OrderFactory{
    final Restaurant restaurant;
    final CampusUser customer;
    final Map<Menu, Integer> menusOrdered;
    final DeliveryLocation deliveryLocation;
    final LocalDateTime deliveryDate;


    public SimpleOrderFactory(Restaurant restaurant, CampusUser customer, Map<Menu, Integer> menusOrdered,
                              DeliveryLocation deliveryLocation, LocalDateTime deliveryDate){
        this.restaurant = restaurant;
        this.customer= customer;
        this.menusOrdered = menusOrdered;
        this.deliveryLocation = deliveryLocation;
        this.deliveryDate = deliveryDate;
    }
    @Override
    public SimpleOrder createOrder() {
        return new SimpleOrder(restaurant, customer, menusOrdered,deliveryDate, deliveryLocation);
    }
}
