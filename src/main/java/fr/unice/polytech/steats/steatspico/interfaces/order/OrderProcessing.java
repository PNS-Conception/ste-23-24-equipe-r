package fr.unice.polytech.steats.steatspico.interfaces.order;

import fr.unice.polytech.steats.steatspico.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.steatspico.exceptions.order.PaymentException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.steatspico.components.orderprocessingstrategy.OrderProcessingStrategy;
import fr.unice.polytech.steats.steatspico.entities.order.Order;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;

import java.util.NoSuchElementException;

public interface OrderProcessing {
    Order process(OrderDetails orderDetails)
            throws EmptyCartException, PaymentException, DeliveryDateNotAvailable, NoSuchElementException;
    void setOrderProcessingStrategy(OrderProcessingStrategy orderProcessingStrategy);
}
