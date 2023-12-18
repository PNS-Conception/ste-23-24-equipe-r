package fr.unice.polytech.steats.steatspico.entities.order.factory;

import fr.unice.polytech.steats.steatspico.entities.order.BuffetOrder;
import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;

import java.util.Map;

public class BuffetOrderFactory implements OrderFactory{
    @Override
    public Order createOrder(OrderDetails orderDetails) {
        BuffetOrder buffetOrder = new BuffetOrder(orderDetails);
        Menu firstMenuOrdered = null;
        Restaurant restaurant = orderDetails.getRestaurant();
        Map<Menu, Integer> menuMap = orderDetails.menusOrdered().get(restaurant);
        if (menuMap != null && !menuMap.isEmpty()) {
            for (Map.Entry<Menu, Integer> menuEntry : menuMap.entrySet()) {
                if (menuEntry.getValue() > 0) {
                    firstMenuOrdered = menuEntry.getKey();
                    break;
                }
            }
        }
        buffetOrder.setBuffetUnit(firstMenuOrdered);
        return buffetOrder;
    }
}
