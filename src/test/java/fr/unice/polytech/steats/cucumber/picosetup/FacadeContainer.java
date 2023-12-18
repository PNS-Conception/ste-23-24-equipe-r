package fr.unice.polytech.steats.cucumber.picosetup;

import fr.unice.polytech.steats.steatspico.components.DeliveryRegistry;
import fr.unice.polytech.steats.steatspico.components.OrderManager;
import fr.unice.polytech.steats.steatspico.interfaces.order.OrderLocator;
import fr.unice.polytech.steats.steatspico.interfaces.order.OrderProcessing;
import fr.unice.polytech.steats.steatspico.interfaces.order.UserOrderHistory;
import fr.unice.polytech.steats.steatspico.interfaces.order.GroupOrderFinder;
import fr.unice.polytech.steats.steatspico.interfaces.order.GroupOrderRegistration;
import fr.unice.polytech.steats.steatspico.components.GroupOrderService;
import fr.unice.polytech.steats.steatspico.interfaces.order.SubOrderManager;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.BuffetOrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.SimpleOrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.repositories.OrderRepository;
import fr.unice.polytech.steats.steatspico.entities.restaurant.MenuComments.CommentsRegistry;
import fr.unice.polytech.steats.steatspico.interfaces.restaurant.RestaurantLocator;
import fr.unice.polytech.steats.steatspico.components.RestaurantRegistry;
import fr.unice.polytech.steats.steatspico.repositories.RestaurantRepository;
import fr.unice.polytech.steats.steatspico.interfaces.users.CampusUserFinder;
import fr.unice.polytech.steats.steatspico.components.CampusUserRegistry;
import fr.unice.polytech.steats.steatspico.repositories.UserRepository;
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
    public SimpleOrderProcessingStrategy simpleOrderProcessingStrategy;
    public BuffetOrderProcessingStrategy buffetOrderProcessingStrategy;

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
        this.simpleOrderProcessingStrategy = container.getComponent(SimpleOrderProcessingStrategy.class);
        this.buffetOrderProcessingStrategy = container.getComponent(BuffetOrderProcessingStrategy.class);
    }
}
