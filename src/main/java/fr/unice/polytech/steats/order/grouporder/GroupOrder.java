package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.util.GroupOrderCodeGenerator;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class GroupOrder {
    private CampusUser groupOrderOwner;
    private Duration groupOrderOpenDuration;
    private boolean isOpen = true;
    private UUID groupOrderID;
    private String groupOrderCode;
    private List<Order> subOrders ;
    private LocalTime deliveryTime;
    private DeliveryLocation deliveryLocation;
    public GroupOrder(CampusUser campusUser, LocalTime deliveryTime, DeliveryLocation deliveryLocation) {
        this.groupOrderID = UUID.randomUUID();
        this.groupOrderOwner = campusUser;
        this.groupOrderCode = GroupOrderCodeGenerator.generate();
        this.deliveryTime = deliveryTime;
        this.deliveryLocation = deliveryLocation;
        generateGroupOrderCode();
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

    public LocalTime getDeliveryTime() {
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
    public void generateGroupOrderCode(){
        // Generate a random 6-digit number
        int randomNumber = new Random().nextInt(900000) + 100000; // Ensures exactly 6 digits

        // Set the generated code
        this.groupOrderCode = String.valueOf(randomNumber);
    }
    public void closeGroupOrder(){this.isOpen=false;}
}
