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
    private OrderDetails orderDetails;
    private double discount = 0.1;
    public MultipleOrder(OrderDetails orderDetails) {
        super(orderDetails);
        this.orderDetails = orderDetails;
    }
    public List<Restaurant> getRestaurants(){
        return orderDetails.getRestaurants();
    }
}
