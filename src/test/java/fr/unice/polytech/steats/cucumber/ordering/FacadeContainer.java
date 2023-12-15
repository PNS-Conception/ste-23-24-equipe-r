package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.order.*;
import fr.unice.polytech.steats.order.grouporder.GroupOrderFinder;
import fr.unice.polytech.steats.order.grouporder.GroupOrderRegistration;
import fr.unice.polytech.steats.order.grouporder.GroupOrderService;
import fr.unice.polytech.steats.order.grouporder.SubOrderManager;
import fr.unice.polytech.steats.restaurant.MenuComments.CommentsRegistry;
import fr.unice.polytech.steats.restaurant.RestaurantLocator;
import fr.unice.polytech.steats.restaurant.RestaurantRegistration;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;
import fr.unice.polytech.steats.users.CampusUserFinder;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import fr.unice.polytech.steats.users.UserRepository;
import org.picocontainer.MutablePicoContainer;

public class FacadeContainer {

    public MutablePicoContainer container;
    public OrderProcessing orderProcessing;
    public OrderLocator orderLocator;
    public UserOrderHistory userOrderHistory;
    public CampusUserFinder campusUserFinder;
    public OrderRepository orderRepository;
    public UserRepository userRepository;
    public RestaurantRepository restaurantRepository;
    public CampusUserRegistry campusUserRegistry;
    public RestaurantRegistry restaurantRegistration;
    public RestaurantLocator restaurantLocator;
    public GroupOrderService groupOrderService;
    public DeliveryRegistry deliveryRegistry;
    public OrderManager orderManager;
    public final CommentsRegistry commentsRegistry;
    public GroupOrderFinder groupOrderFinder;
    public GroupOrderRegistration groupOrderRegistration;
    public SubOrderManager subOrderManager;

    public FacadeContainer(AppExplicitConfig config){
        this.container = config.getContainer();
        this.restaurantRegistration = container.getComponent(RestaurantRegistry.class);
        this.restaurantLocator = container.getComponent(RestaurantLocator.class);
        this.campusUserRegistry = container.getComponent(CampusUserRegistry.class);
        this.orderProcessing = container.getComponent(OrderProcessing.class);
        this.orderLocator = container.getComponent(OrderLocator.class);
        this.userOrderHistory = container.getComponent(UserOrderHistory.class);
        this.groupOrderService = container.getComponent(GroupOrderService.class);
        this.deliveryRegistry = container.getComponent(DeliveryRegistry.class);
        this.commentsRegistry=container.getComponent(CommentsRegistry.class);
        this.orderRepository = container.getComponent(OrderRepository.class);
        this.restaurantRepository = container.getComponent(RestaurantRepository.class);
        this.userRepository = container.getComponent(UserRepository.class);
        this.orderManager = container.getComponent(OrderManager.class);
        this.campusUserFinder = container.getComponent(CampusUserFinder.class);
        this.groupOrderFinder = container.getComponent(GroupOrderFinder.class);
        this.groupOrderRegistration = container.getComponent(GroupOrderRegistration.class);
        this.subOrderManager = container.getComponent(SubOrderManager.class);
    }
}
