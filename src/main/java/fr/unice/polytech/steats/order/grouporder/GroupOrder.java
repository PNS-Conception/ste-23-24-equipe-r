package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.TimeSlot;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.util.GroupOrderCodeGenerator;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupOrder {
    private CampusUser groupOrderOwner;
    private Duration groupOrderOpenDuration;
    private boolean isOpen = true;
    private UUID groupOrderID;
    private String groupOrderCode;
    private List<Order> subOrders ;
    private TimeSlot timeslot;
    private DeliveryLocation deliveryLocation;
    public GroupOrder(CampusUser campusUser, TimeSlot timeSlot, DeliveryLocation deliveryLocation) {
        this.groupOrderID = UUID.randomUUID();
        this.groupOrderOwner = campusUser;
        this.groupOrderCode = GroupOrderCodeGenerator.generate();
        this.timeslot = timeSlot;
        this.deliveryLocation = deliveryLocation;
        subOrders = new ArrayList<>();
    }

    public List<Order> getSubOrders() {
        return subOrders;
    }
    public boolean isOpen() {
        return isOpen;
    }
    public DeliveryLocation getDeliveryLocation() {
        return deliveryLocation;
    }

    public TimeSlot getTimeSlot() {
        return timeslot;
    }
    public String getGroupOrderCode(){
        return this.groupOrderCode;
    }
    public UUID getId(){
        return groupOrderID;
    }
    public int getSize(){
        if (subOrders == null){
            return 0;
        }
        return subOrders.size();
    }
    public void setGroupOrderCode(String code){
        this.groupOrderCode = code;
    }
    public void closeGroupOrder(){this.isOpen=false;}
}
