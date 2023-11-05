package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupOrder {
    private CampusUser groupOrderOwner;
    private Duration groupOrderOpenDuration;
    private boolean isOpen = true ;
    private UUID groupOrderID;
    private String groupOrderCode;
    private List<Order> subOrders ;
    private TimeSlot timeslot;
    private DeliveryLocation deliveryLocation;



    public GroupOrder() {
        this.subOrders = new ArrayList<>();
        this.groupOrderID = UUID.randomUUID();
    }

    public UUID getGroupOrderID() {
        return groupOrderID;
    }

    public void setGroupOrderID(UUID groupOrderID) {
        this.groupOrderID = groupOrderID;
    }

    public List<Order> getSubOrders() {
        return subOrders;
    }

    public void setSubOrders(List<Order> subOrders) {
        this.subOrders = subOrders;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public DeliveryLocation getDeliveryLocation() {
        return deliveryLocation;
    }

    public TimeSlot getTimeSlot() {
        return timeslot;
    }
}
