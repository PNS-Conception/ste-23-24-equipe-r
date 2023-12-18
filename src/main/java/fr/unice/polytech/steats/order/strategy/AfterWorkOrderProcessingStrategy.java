package fr.unice.polytech.steats.order.strategy;

import fr.unice.polytech.steats.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderDetails;

public class AfterWorkOrderProcessingStrategy implements OrderProcessingStrategy{
    @Override
    public Order process(OrderDetails orderDetails) throws PaymentException, EmptyCartException, DeliveryDateNotAvailable {
        return null;
    }
}
