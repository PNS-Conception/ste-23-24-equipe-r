package fr.unice.polytech.steats.steatspico.entities.order;

import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;
import fr.unice.polytech.steats.steatspico.entities.restaurant.Restaurant;
import java.util.List;
import java.util.Map;

public class MultipleOrder extends Order{
    private OrderDetails orderDetails;
    private Map<Restaurant, Map<Menu, Integer>> menusOrdered;
    public MultipleOrder(OrderDetails orderDetails) {
        super(orderDetails);
        this.orderDetails = orderDetails;
        this.menusOrdered = orderDetails.menusOrdered();
    }
    public List<Restaurant> getRestaurants(){
        return orderDetails.getRestaurants();
    }
    public Map<Restaurant, Map<Menu, Integer>> getMenusOrdered() {
        return menusOrdered;
    }

    public void setMenusOrdered(Map<Restaurant, Map<Menu, Integer>> menusOrdered) {
        this.menusOrdered = menusOrdered;
    }

}
