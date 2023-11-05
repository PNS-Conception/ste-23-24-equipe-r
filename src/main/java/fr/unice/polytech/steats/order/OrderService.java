package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.exceptions.order.NonExistentOrder;

import java.util.Optional;
import java.util.UUID;

public class OrderService {
    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void process(Order order){
        order.setStatus(OrderStatus.PREPARING);
        orderRepository.save(order, order.getId());
    }

    public OrderStatus getOrderStatus(UUID id) throws NonExistentOrder {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()){
            throw new NonExistentOrder(id);
        }
        return order.get().getStatus();
    }
}
