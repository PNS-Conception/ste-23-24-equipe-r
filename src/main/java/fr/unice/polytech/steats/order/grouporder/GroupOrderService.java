package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.exceptions.order.*;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.order.OrderProcessing;
import fr.unice.polytech.steats.order.OrderVolume;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class GroupOrderService {
    final GroupOrderRepository groupOrderRepository;
    final OrderProcessing orderProcessing;
    public GroupOrderService(GroupOrderRepository groupOrderRepository, OrderProcessing orderProcessing){
        this.groupOrderRepository = groupOrderRepository;
        this.orderProcessing = orderProcessing;
    }



    public GroupOrder register(CampusUser campusUser, LocalDateTime deliveryTime, DeliveryLocation deliveryLocation){
        GroupOrder groupOrder = new GroupOrder(campusUser, deliveryTime, deliveryLocation);
        groupOrderRepository.save(groupOrder, groupOrder.getId());
        return groupOrder;
    }
    public Optional<GroupOrder> findByCode(String code) {
        return StreamSupport.stream(groupOrderRepository.findAll().spliterator(), false)
                .filter(groupOrder -> code.equals(groupOrder.getGroupOrderCode())).findAny();
    }

    public void addSubOrder(String groupOrderCode, Restaurant restaurant,
                            CampusUser customer, Map<Menu, Integer> menusOrdered)
            throws NonExistentGroupOrder, ClosedGroupOrderException, EmptyCartException, PaymentException, DeliveryDateNotAvailable {
        GroupOrder groupOrder = validateAndGetGroupOrder(groupOrderCode);
        SimpleOrder order = orderProcessing.process(restaurant, customer, menusOrdered,groupOrder.getDeliveryTime(), groupOrder.getDeliveryLocation());
        groupOrder.getSubOrders().add(order);
        OrderVolume.getInstance().addOrder(order);
    }
    private GroupOrder validateAndGetGroupOrder(String groupOrderCode)
            throws NonExistentGroupOrder, ClosedGroupOrderException {
        Optional<GroupOrder> optionalGroupOrder = findByCode(groupOrderCode);
        if (optionalGroupOrder.isEmpty()) {
            throw new NonExistentGroupOrder(groupOrderCode);
        }
        GroupOrder groupOrder = optionalGroupOrder.get();
        if (!groupOrder.isOpen()) {
            throw new ClosedGroupOrderException(groupOrderCode);
        }
        return groupOrder;
    }
    public Optional<SimpleOrder> locateSubOrder(GroupOrder groupOrder, CampusUser customer) {
        List<SimpleOrder> ordersByCustomer = groupOrder.getSubOrders().stream()
                .filter(order -> order.getCustomers().contains(customer))
                .toList();
        if (!ordersByCustomer.isEmpty()) {
            return Optional.of(ordersByCustomer.get(ordersByCustomer.size() - 1));
        } else {
            return Optional.empty();
        }
    }

}
