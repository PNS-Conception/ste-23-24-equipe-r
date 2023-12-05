package fr.unice.polytech.steats.stats;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.NoOrdersPlacedException;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.order.OrderVolume;
import fr.unice.polytech.steats.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class StatisticsManager {

    final OrderVolume orderVolume;

    public StatisticsManager() {
        this.orderVolume = OrderVolume.getInstance();
    }

    public List<Order> getOrderVolumesOverTime() throws NoOrdersPlacedException {
        if(orderVolume==null ||orderVolume.getOrderVolume().isEmpty() ) {
            throw new NoOrdersPlacedException();
        }
        for(Order order : orderVolume.getOrderVolume()) {
            if(order.getStatus() == OrderStatus.DELIVERED) {
                orderVolume.getOrderVolume().remove(order);
            }
        }

        return orderVolume.getOrderVolume();
    }

    public List<Order> getRestaurantOrderVolume(Restaurant restaurant) throws NoOrdersPlacedException {
        if(orderVolume==null || orderVolume.getOrderVolume().isEmpty() ) {
            throw new NoOrdersPlacedException();
        }
        List<Order> restaurantSimpleOrderVolume = new ArrayList<>();
        for(Order order : orderVolume.getOrderVolume()) {
            if(order.getStatus() == OrderStatus.DELIVERED) {
                orderVolume.getOrderVolume().remove(order);
            }
            if(order.getRestaurants().contains(restaurant)){
                restaurantSimpleOrderVolume.add(order);
            }
        }
        return restaurantSimpleOrderVolume;
    }


    public DeliveryLocation getDeliveryLocation(SimpleOrder simpleOrder1) throws NoOrdersPlacedException{
        if(orderVolume.getOrderVolume().contains(simpleOrder1) && !(simpleOrder1.getStatus().equals(OrderStatus.DELIVERED))){
            return simpleOrder1.getDeliveryLocation();
        }
        throw new NoOrdersPlacedException();
    }



}
