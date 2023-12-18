package fr.unice.polytech.steats.order;

import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.order.strategy.OrderProcessingStrategy;

import java.util.NoSuchElementException;

public interface OrderProcessing {
    Order process(OrderDetails orderDetails)
            throws EmptyCartException, PaymentException, DeliveryDateNotAvailable, NoSuchElementException;
    void setOrderProcessingStrategy(OrderProcessingStrategy orderProcessingStrategy);
}
