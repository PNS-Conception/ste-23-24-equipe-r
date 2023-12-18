package fr.unice.polytech.steats.cucumber.picosetup;

import fr.unice.polytech.steats.steatspico.components.DeliveryRegistry;
import fr.unice.polytech.steats.steatspico.repositories.DeliveryRepository;
import fr.unice.polytech.steats.steatspico.components.OrderManager;
import fr.unice.polytech.steats.steatspico.repositories.OrderRepository;
import fr.unice.polytech.steats.steatspico.repositories.GroupOrderRepository;
import fr.unice.polytech.steats.steatspico.components.GroupOrderService;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.AfterWorkOrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.BuffetOrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.MultipleOrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.SimpleOrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.connectors.PaymentProxy;
import fr.unice.polytech.steats.steatspico.components.PaymentManager;
import fr.unice.polytech.steats.steatspico.entities.restaurant.MenuComments.CommentsRegistry;
import fr.unice.polytech.steats.steatspico.repositories.CommentsRepository;
import fr.unice.polytech.steats.steatspico.components.RestaurantRegistry;
import fr.unice.polytech.steats.steatspico.repositories.RestaurantRepository;
import fr.unice.polytech.steats.steatspico.components.CampusUserRegistry;
import fr.unice.polytech.steats.steatspico.repositories.UserRepository;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;

public class AppExplicitConfig {
    private MutablePicoContainer container;
    public MutablePicoContainer getContainer(){
        if (container == null){
            container = new DefaultPicoContainer(new Caching());

            container.addComponent(RestaurantRepository.class);
            container.addComponent(RestaurantRegistry.class);
            container.addComponent(UserRepository.class);
            container.addComponent(CampusUserRegistry.class);
            container.addComponent(OrderRepository.class);
            container.addComponent(OrderManager.class);
            container.addComponent(PaymentManager.class);
            container.addComponent(PaymentProxy.class);
            container.addComponent(GroupOrderRepository.class);
            container.addComponent(GroupOrderService.class);
            container.addComponent(DeliveryRegistry.class);
            container.addComponent(DeliveryRepository.class);
            container.addComponent(CommentsRepository.class);
            container.addComponent(CommentsRegistry.class);
            container.addComponent(SimpleOrderProcessingStrategy.class);
            container.addComponent(MultipleOrderProcessingStrategy.class);
            container.addComponent(BuffetOrderProcessingStrategy.class);
            container.addComponent(AfterWorkOrderProcessingStrategy.class);

        }
        return container;
    }
}
