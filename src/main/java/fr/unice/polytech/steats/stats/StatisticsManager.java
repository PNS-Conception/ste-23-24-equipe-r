package fr.unice.polytech.steats.stats;

import fr.unice.polytech.steats.exceptions.order.NoOrdersPlacedException;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.order.OrderVolume;
import fr.unice.polytech.steats.restaurant.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StatisticsManager {

    OrderVolume orderVolume;

    public StatisticsManager() {
        this.orderVolume = OrderVolume.getInstance();
    }

    public List<Order> getOrderVolumesOverTime() throws NoOrdersPlacedException {
        if(orderVolume.getOrderVolume().isEmpty() || orderVolume==null) {
            throw new NoOrdersPlacedException();
        }
        for(Order order : orderVolume.getOrderVolume()) {
            if(order.getStatus() == OrderStatus.DELIVERED) {
                orderVolume.getOrderVolume().remove(order);
            }
        }

        return orderVolume.getOrderVolume();
    }



}
