package fr.unice.polytech.steats.order.factory;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.OrderDetails;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalDateTime;
import java.util.Map;

import static java.util.Collections.singletonList;

public class SimpleOrderFactory implements OrderFactory{
    @Override
    public SimpleOrder createOrder(OrderDetails orderDetails) {
        return new SimpleOrder(orderDetails);
    }
}
