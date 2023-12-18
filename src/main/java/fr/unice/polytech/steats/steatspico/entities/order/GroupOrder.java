package fr.unice.polytech.steats.steatspico.entities.order;

import fr.unice.polytech.steats.steatspico.entities.delivery.DeliveryLocation;
import fr.unice.polytech.steats.steatspico.util.GroupOrderCodeGenerator;
import java.util.ArrayList;
import java.util.List;

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
