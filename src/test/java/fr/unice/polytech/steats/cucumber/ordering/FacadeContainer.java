package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.order.OrderManager;
import fr.unice.polytech.steats.order.OrderRepository;
import fr.unice.polytech.steats.order.grouporder.GroupOrderRegistry;
import fr.unice.polytech.steats.order.grouporder.GroupOrderService;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import fr.unice.polytech.steats.users.DeliveryPersonRegistry;
import fr.unice.polytech.steats.users.UserRepository;
import org.picocontainer.MutablePicoContainer;

public class FacadeContainer {
    public MutablePicoContainer container;
    public OrderManager orderManager;
    public OrderRepository orderRepository;
    public DeliveryPersonRegistry deliveryPersonRegistry;
    public RestaurantRepository restaurantRepository;
    public UserRepository userRepository;
    public CampusUserRegistry campusUserRegistry;
    public RestaurantRegistry restaurantRegistry;
    public GroupOrderRegistry groupOrderRegistry;
    public GroupOrderService groupOrderService;
    public DeliveryRegistry deliveryRegistry;


    public FacadeContainer(AppExplicitConfig config){
        this.container = config.getContainer();
        this.restaurantRepository = container.getComponent(RestaurantRepository.class);
        this.restaurantRegistry = container.getComponent(RestaurantRegistry.class);
        this.userRepository = container.getComponent(UserRepository.class);
        this.campusUserRegistry = container.getComponent(CampusUserRegistry.class);
        this.orderRepository = container.getComponent(OrderRepository.class);
        this.orderManager = container.getComponent(OrderManager.class);
        this.groupOrderRegistry = container.getComponent(GroupOrderRegistry.class);
        this.groupOrderService = container.getComponent(GroupOrderService.class);
        this.deliveryPersonRegistry = container.getComponent(DeliveryPersonRegistry.class);
        this.deliveryRegistry = container.getComponent(DeliveryRegistry.class);
    }
}
