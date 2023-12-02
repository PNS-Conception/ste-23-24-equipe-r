package fr.unice.polytech.steats.stats;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.NoOrdersPlacedException;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.order.OrderVolume;
import fr.unice.polytech.steats.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class StatisticsManager {

    OrderVolume orderVolume;

    public StatisticsManager() {
        this.orderVolume = OrderVolume.getInstance();
    }

    public List<SimpleOrder> getOrderVolumesOverTime() throws NoOrdersPlacedException {
        if(orderVolume.getOrderVolume().isEmpty() || orderVolume==null) {
            throw new NoOrdersPlacedException();
        }
        for(SimpleOrder simpleOrder : orderVolume.getOrderVolume()) {
            if(simpleOrder.getStatus() == OrderStatus.DELIVERED) {
                orderVolume.getOrderVolume().remove(simpleOrder);
            }
        }

        return orderVolume.getOrderVolume();
    }

    public List<SimpleOrder> getRestaurantOrderVolume(Restaurant restaurant) throws NoOrdersPlacedException {
        if(orderVolume.getOrderVolume().isEmpty() || orderVolume==null) {
            throw new NoOrdersPlacedException();
        }
        List<SimpleOrder> restaurantSimpleOrderVolume = new ArrayList<>();
        for(SimpleOrder simpleOrder : orderVolume.getOrderVolume()) {
            if(simpleOrder.getStatus() == OrderStatus.DELIVERED) {
                orderVolume.getOrderVolume().remove(simpleOrder);
            }
            if(simpleOrder.getRestaurants().contains(restaurant)){
                restaurantSimpleOrderVolume.add(simpleOrder);
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
