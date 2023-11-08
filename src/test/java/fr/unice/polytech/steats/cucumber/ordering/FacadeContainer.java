package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.order.OrderRegistry;
import fr.unice.polytech.steats.order.OrderRepository;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import fr.unice.polytech.steats.users.CampusUserRepository;
import org.picocontainer.MutablePicoContainer;

public class FacadeContainer {
    MutablePicoContainer container;
    OrderRegistry orderRegistry;
    OrderRepository orderRepository;

    RestaurantRepository restaurantRepository;
    CampusUserRepository campusUserRepository;
    CampusUserRegistry campusUserRegistry;
    RestaurantRegistry restaurantRegistry;
    public FacadeContainer(AppExplicitConfig config){
        this.container = config.getContainer();
        this.restaurantRepository = container.getComponent(RestaurantRepository.class);
        this.restaurantRegistry = container.getComponent(RestaurantRegistry.class);
        this.campusUserRepository = container.getComponent(CampusUserRepository.class);
        this.campusUserRegistry = container.getComponent(CampusUserRegistry.class);
        this.orderRepository = container.getComponent(OrderRepository.class);
        this.orderRegistry = container.getComponent(OrderRegistry.class);
    }
}
