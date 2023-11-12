package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;

import java.util.Optional;
import java.util.stream.StreamSupport;

public class GroupOrderRegistry {
    GroupOrderRepository groupOrderRepository;
    public GroupOrderRegistry(GroupOrderRepository groupOrderRepository){
        this.groupOrderRepository = groupOrderRepository;
    }

    public GroupOrder register(CampusUser campusUser, TimeSlot timeSlot, DeliveryLocation deliveryLocation){
        GroupOrder groupOrder = new GroupOrder(campusUser, timeSlot, deliveryLocation);
        groupOrderRepository.save(groupOrder, groupOrder.getId());
        return groupOrder;
    }
    public Optional<GroupOrder> findByCode(String code) {
        return StreamSupport.stream(groupOrderRepository.findAll().spliterator(), false)
                .filter(groupOrder -> code.equals(groupOrder.getGroupOrderCode())).findAny();
    }

}
