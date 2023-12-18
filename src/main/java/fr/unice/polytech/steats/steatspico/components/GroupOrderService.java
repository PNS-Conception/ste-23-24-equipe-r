package fr.unice.polytech.steats.steatspico.components;

import fr.unice.polytech.steats.steatspico.exceptions.order.ClosedGroupOrderException;
import fr.unice.polytech.steats.steatspico.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.steatspico.exceptions.order.NonExistentGroupOrder;
import fr.unice.polytech.steats.steatspico.exceptions.order.PaymentException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.SimpleOrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.OrderVolume;
import fr.unice.polytech.steats.steatspico.entities.order.SimpleOrder;
import fr.unice.polytech.steats.steatspico.entities.order.GroupOrder;
import fr.unice.polytech.steats.steatspico.interfaces.order.GroupOrderFinder;
import fr.unice.polytech.steats.steatspico.interfaces.order.GroupOrderRegistration;
import fr.unice.polytech.steats.steatspico.interfaces.order.SubOrderManager;
import fr.unice.polytech.steats.steatspico.repositories.GroupOrderRepository;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class GroupOrderService implements SubOrderManager, GroupOrderRegistration, GroupOrderFinder {
    final GroupOrderRepository groupOrderRepository;
    final SimpleOrderProcessingStrategy simpleOrderProcessingStrategy;
    public GroupOrderService(GroupOrderRepository groupOrderRepository, SimpleOrderProcessingStrategy simpleOrderProcessingStrategy){
        this.groupOrderRepository = groupOrderRepository;
        this.simpleOrderProcessingStrategy = simpleOrderProcessingStrategy;
    }
    @Override
    public GroupOrder register(OrderDetails orderDetails){
        GroupOrder groupOrder = new GroupOrder(orderDetails);
        groupOrderRepository.save(groupOrder, groupOrder.getId());
        return groupOrder;
    }
    @Override
    public Optional<GroupOrder> findByCode(String code) {
        return StreamSupport.stream(groupOrderRepository.findAll().spliterator(), false)
                .filter(groupOrder -> code.equals(groupOrder.getGroupOrderCode())).findAny();
    }

    @Override
    public void addSubOrder(String groupOrderCode, OrderDetails orderDetails)
            throws NonExistentGroupOrder, ClosedGroupOrderException, EmptyCartException, PaymentException, DeliveryDateNotAvailable {
        GroupOrder groupOrder = validateAndGetGroupOrder(groupOrderCode);
        SimpleOrder order = (SimpleOrder)simpleOrderProcessingStrategy.process(orderDetails);
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
    @Override
    public Optional<SimpleOrder> locateSubOrder(GroupOrder groupOrder, CampusUser customer) {
        List<SimpleOrder> ordersByCustomer = groupOrder.getSubOrders().stream()
                .filter(order -> order.getCustomer().equals(customer))
                .toList();
        if (!ordersByCustomer.isEmpty()) {
            return Optional.of(ordersByCustomer.get(ordersByCustomer.size() - 1));
        } else {
            return Optional.empty();
        }
    }

}
