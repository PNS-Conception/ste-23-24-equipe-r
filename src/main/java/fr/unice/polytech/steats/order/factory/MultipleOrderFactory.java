package fr.unice.polytech.steats.order.factory;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.MultipleOrder;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


public class MultipleOrderFactory implements OrderFactory{
    final List<Restaurant> restaurants;
    final CampusUser customer;
    final Map<Menu, Integer> menusOrdered;
    final DeliveryLocation deliveryLocation;
    final LocalDateTime deliveryDate;


    public MultipleOrderFactory(List<Restaurant> restaurants, CampusUser customer, Map<Menu, Integer> menusOrdered, LocalDateTime deliveryDate,
                                DeliveryLocation deliveryLocation){
        this.restaurants = restaurants;
        this.customer= customer;
        this.menusOrdered = menusOrdered;
        this.deliveryLocation = deliveryLocation;
        this.deliveryDate = deliveryDate;
    }
    @Override
    public MultipleOrder createOrder() {
        return new MultipleOrder(restaurants, customer, menusOrdered,deliveryDate, deliveryLocation);
    }
}
