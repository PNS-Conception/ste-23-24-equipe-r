package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.exceptions.order.ClosedGroupOrderException;
import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.NonExistentGroupOrder;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.exceptions.restaurant.InsufficientTimeSlotCapacity;
import fr.unice.polytech.steats.exceptions.restaurant.NonExistentTimeSlot;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderRegistry;
import fr.unice.polytech.steats.order.OrderVolume;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GroupOrderService {
    GroupOrderRegistry groupOrderRegistry;
    OrderRegistry orderRegistry;
    public GroupOrderService(GroupOrderRegistry groupOrderRegistry, OrderRegistry orderRegistry){
        this.groupOrderRegistry = groupOrderRegistry;
        this.orderRegistry = orderRegistry;
    }

    public void addSubOrder(String groupOrderCode, Restaurant restaurant,
                            CampusUser customer, Map<Menu, Integer> menusOrdered)
            throws NonExistentGroupOrder, ClosedGroupOrderException, EmptyCartException, PaymentException, DeliveryDateNotAvailable {
        GroupOrder groupOrder = validateAndGetGroupOrder(groupOrderCode);
        Order order = orderRegistry.register(restaurant, customer, menusOrdered,groupOrder.getDeliveryTime(), groupOrder.getDeliveryLocation());
        groupOrder.getSubOrders().add(order);
        OrderVolume.getInstance().addOrder(order);
    }
    private GroupOrder validateAndGetGroupOrder(String groupOrderCode)
            throws NonExistentGroupOrder, ClosedGroupOrderException {
        Optional<GroupOrder> optionalGroupOrder = groupOrderRegistry.findByCode(groupOrderCode);
        if (optionalGroupOrder.isEmpty()) {
            throw new NonExistentGroupOrder(groupOrderCode);
        }
        GroupOrder groupOrder = optionalGroupOrder.get();
        if (!groupOrder.isOpen()) {
            throw new ClosedGroupOrderException(groupOrderCode);
        }
        return groupOrder;
    }
    public Optional<Order> locateOrder(GroupOrder groupOrder, CampusUser customer) {
        List<Order> ordersByCustomer = groupOrder.getSubOrders().stream()
                .filter(order -> order.getCustomer().equals(customer))
                .toList();
        if (!ordersByCustomer.isEmpty()) {
            return Optional.of(ordersByCustomer.get(ordersByCustomer.size() - 1));
        } else {
            return Optional.empty();
        }
    }

}
