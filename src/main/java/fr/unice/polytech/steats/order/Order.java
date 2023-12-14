package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.delivery.DeliveryLocation;
import fr.unice.polytech.steats.notification.NotificationRegistry;
import fr.unice.polytech.steats.notification.order.OrderPublisher;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.restaurant.Restaurant;
import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.CampusUserFinder;
import fr.unice.polytech.steats.users.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class Order {
    private final UUID orderID;
    private final OrderDetails orderDetails;
    private OrderStatus orderStatus;
    protected OrderPublisher orderPublisher;

    protected Order(OrderDetails orderDetails){
        this.orderID = UUID.randomUUID();
        this.orderStatus = OrderStatus.WAITING_FOR_PREPARATION;
        this.orderDetails = orderDetails;
        this.orderPublisher = new OrderPublisher();
        this.orderPublisher.subscribe(orderDetails.getOrderOwner());
    }
    public LocalDateTime getDeliveryTime(){
        return orderDetails.getDeliveryTime();
    }

    public OrderStatus getStatus() {
        return orderStatus;
    }

    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
        this.orderPublisher.notifySubscribers(this);
    }

    public UUID getId() {
        return orderID;
    }

    public CampusUser getCustomer() {
        return orderDetails.getOrderOwner();
    }
    public OrderDetails getOrderDetails(){
        return this.orderDetails;
    }

    public OrderPublisher getOrderPublisher() {
        return orderPublisher;
    }
}
