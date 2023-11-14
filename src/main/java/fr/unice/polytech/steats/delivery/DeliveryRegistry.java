package fr.unice.polytech.steats.delivery;

import fr.unice.polytech.steats.order.Order;

public class DeliveryRegistry {
    DeliveryRepository deliveryRepository;

    public DeliveryRegistry(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public void register(Order order) {
        Delivery delivery = new Delivery(order);
        deliveryRepository.save(delivery, delivery.getId());
    }
}
