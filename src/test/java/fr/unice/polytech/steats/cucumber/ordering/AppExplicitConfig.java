package fr.unice.polytech.steats.cucumber.ordering;

import fr.unice.polytech.steats.order.OrderRegistry;
import fr.unice.polytech.steats.order.OrderRepository;
import fr.unice.polytech.steats.payment.ExternalPaymentMock;
import fr.unice.polytech.steats.payment.PaymentManager;
import fr.unice.polytech.steats.restaurant.RestaurantRegistry;
import fr.unice.polytech.steats.restaurant.RestaurantRepository;
import fr.unice.polytech.steats.users.CampusUserRegistry;
import fr.unice.polytech.steats.users.CampusUserRepository;
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
            container.addComponent(CampusUserRepository.class);
            container.addComponent(CampusUserRegistry.class);
            container.addComponent(OrderRepository.class);
            container.addComponent(OrderRegistry.class);
            container.addComponent(PaymentManager.class);
            container.addComponent(ExternalPaymentMock.class);
        }
        return container;
    }
}
