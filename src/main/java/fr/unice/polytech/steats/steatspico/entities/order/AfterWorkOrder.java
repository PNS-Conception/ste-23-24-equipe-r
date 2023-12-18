package fr.unice.polytech.steats.steatspico.entities.order;

import fr.unice.polytech.steats.steatspico.entities.restaurant.Menu;

import java.util.Map;

public class AfterWorkOrder extends Order{
    private final Map<Menu, Integer> menusOrdered;

    public AfterWorkOrder(OrderDetails orderDetails) {
        super(orderDetails);
        menusOrdered = orderDetails.menusOrdered().get(orderDetails.getRestaurant());
    }

    public Map<Menu, Integer> getMenusOrdered() {
        return menusOrdered;
    }
}
