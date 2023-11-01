package fr.unice.polytech.steats.groupOrder;

import fr.unice.polytech.steats.order.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupOrder {
    private boolean isOpen = true ;
    private UUID groupOrderID;
    private List<Order> subOrders ;



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

}
