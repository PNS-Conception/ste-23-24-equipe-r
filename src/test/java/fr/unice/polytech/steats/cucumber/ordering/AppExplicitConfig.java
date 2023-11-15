package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.delivery.DeliveryRegistry;
import fr.unice.polytech.steats.delivery.DeliveryRepository;
import fr.unice.polytech.steats.order.OrderRegistry;
import fr.unice.polytech.steats.order.OrderRepository;
import fr.unice.polytech.steats.order.grouporder.GroupOrderRegistry;
import fr.unice.polytech.steats.order.grouporder.GroupOrderRepository;
import fr.unice.polytech.steats.order.grouporder.GroupOrderService;
import fr.unice.polytech.steats.payment.ExternalPaymentMock;
import fr.unice.polytech.steats.payment.PaymentManager;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import fr.unice.polytech.steats.users.UserRepository;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;

public class AppExplicitConfig {
    private MutablePicoContainer container;
    public MutablePicoContainer getContainer(){
        if (container == null){
            container = new DefaultPicoContainer(new Caching());

            // Register your components
            container.addComponent(RestaurantRepository.class);
            container.addComponent(RestaurantRegistry.class);
            container.addComponent(UserRepository.class);
            container.addComponent(CampusUserRegistry.class);
            container.addComponent(OrderRepository.class);
            container.addComponent(OrderRegistry.class);
            container.addComponent(PaymentManager.class);
            container.addComponent(ExternalPaymentMock.class);
            container.addComponent(GroupOrderRepository.class);
            container.addComponent(GroupOrderRegistry.class);
            container.addComponent(GroupOrderService.class);
            container.addComponent(DeliveryRegistry.class);
            container.addComponent(DeliveryRepository.class);
        }
        return container;
    }
}
