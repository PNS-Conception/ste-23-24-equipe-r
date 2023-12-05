package fr.unice.polytech.steats.order.grouporder;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderDetails;
import fr.unice.polytech.steats.order.OrderDetailsBuilder;
import fr.unice.polytech.steats.order.SimpleOrder;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.util.GroupOrderCodeGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupOrder extends Order {
    private final OrderDetails orderDetails;
    private boolean isOpen = true;
    private String groupOrderCode;
    private final List<SimpleOrder> subOrders;
    public GroupOrder(OrderDetails orderDetails) {
        super(orderDetails);
        this.orderDetails = orderDetails;
        this.groupOrderCode = GroupOrderCodeGenerator.generate();
        subOrders = new ArrayList<>();
    }

    public List<SimpleOrder> getSubOrders() {
        return subOrders;
    }
    public boolean isOpen() {
        return isOpen;
    }
    public DeliveryLocation getDeliveryLocation() {
        return orderDetails.getDeliveryLocation();
    }
    public String getGroupOrderCode(){
        return this.groupOrderCode;
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
