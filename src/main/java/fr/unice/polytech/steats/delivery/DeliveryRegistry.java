package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderStatus;
import fr.unice.polytech.steats.users.DeliveryPerson;

import java.util.Optional;
import java.util.UUID;

public class DeliveryRegistry {
    DeliveryRepository deliveryRepository;

    public DeliveryRegistry(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void register(Order order) {
        Delivery delivery = new Delivery(order);
        order.setDeliveryId(delivery.getId());
        deliveryRepository.save(delivery, delivery.getId());
    }

    public DeliveryRepository getDeliveryRepository() {
        return deliveryRepository;
    }

    public void MarkDeliveryAsReady(UUID id, DeliveryPerson deliveryPerson){
        Optional<Delivery> optionalDelivery = deliveryRepository.findById(id);

        if (optionalDelivery.isPresent()) {
            Delivery delivery = optionalDelivery.get();
            delivery.setDeliveryPerson(deliveryPerson);
            deliveryRepository.save(delivery,delivery.getId());
        }
    }
}
