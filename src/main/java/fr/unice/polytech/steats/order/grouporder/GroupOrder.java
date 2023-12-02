package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.util.GroupOrderCodeGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupOrder {
    private final CampusUser groupOrderOwner;
    // --Commented out by Inspection (28/11/2023 22:32):private Duration groupOrderOpenDuration;
    private boolean isOpen = true;
    private final UUID groupOrderID;
    private String groupOrderCode;
    private final List<SimpleOrder> subOrders;
    private final LocalDateTime deliveryTime;
    private final DeliveryLocation deliveryLocation;
    public GroupOrder(CampusUser campusUser, LocalDateTime deliveryTime, DeliveryLocation deliveryLocation) {
        this.groupOrderID = UUID.randomUUID();
        this.groupOrderOwner = campusUser;
        this.groupOrderCode = GroupOrderCodeGenerator.generate();
        this.deliveryTime = deliveryTime;
        this.deliveryLocation = deliveryLocation;
        subOrders = new ArrayList<>();
    }

    public List<SimpleOrder> getSubOrders() {
        return subOrders;
    }
    public boolean isOpen() {
        return isOpen;
    }
    public DeliveryLocation getDeliveryLocation() {
        return deliveryLocation;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
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
    public void closeGroupOrder(){this.isOpen=false;}

    public void setGroupOrderCode(String groupOrderCode) {
        this.groupOrderCode = groupOrderCode;
    }
}
