package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.LocalTime;
import java.util.Optional;
import java.util.stream.StreamSupport;

public class GroupOrderRegistry {
    GroupOrderRepository groupOrderRepository;
    public GroupOrderRegistry(GroupOrderRepository groupOrderRepository){
        this.groupOrderRepository = groupOrderRepository;
    }

    public GroupOrder register(CampusUser campusUser, LocalTime deliveryTime, DeliveryLocation deliveryLocation){
        GroupOrder groupOrder = new GroupOrder(campusUser, deliveryTime, deliveryLocation);
        groupOrderRepository.save(groupOrder, groupOrder.getId());
        return groupOrder;
    }
    public Optional<GroupOrder> findByCode(String code) {
        return StreamSupport.stream(groupOrderRepository.findAll().spliterator(), false)
                .filter(groupOrder -> code.equals(groupOrder.getGroupOrderCode())).findAny();
    }

}
