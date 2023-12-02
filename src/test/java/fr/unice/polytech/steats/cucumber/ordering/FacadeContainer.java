package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.order.OrderManager;
import fr.unice.polytech.steats.order.OrderRepository;
import fr.unice.polytech.steats.order.grouporder.GroupOrderRegistry;
import fr.unice.polytech.steats.order.grouporder.GroupOrderService;
import fr.unice.polytech.steats.restaurant.MenuComments.CommentsRegistry;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import fr.unice.polytech.steats.users.UserRepository;
import org.picocontainer.MutablePicoContainer;

public class FacadeContainer {
    public final MutablePicoContainer container;
    public final OrderManager orderManager;
    public final OrderRepository orderRepository;
    public final RestaurantRepository restaurantRepository;
    public final UserRepository userRepository;
    public final CampusUserRegistry campusUserRegistry;
    public final RestaurantRegistry restaurantRegistry;
    public final GroupOrderRegistry groupOrderRegistry;
    public final GroupOrderService groupOrderService;
    public final DeliveryRegistry deliveryRegistry;



    public final CommentsRegistry commentsRegistry;


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
        this.deliveryRegistry = container.getComponent(DeliveryRegistry.class);
        this.commentsRegistry=container.getComponent(CommentsRegistry.class);
    }
}
