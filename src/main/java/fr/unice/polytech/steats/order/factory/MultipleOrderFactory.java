package fr.unice.polytech.steats.order.factory;


import fr.unice.polytech.steats.order.MultipleOrder;
import fr.unice.polytech.steats.order.OrderDetails;



public class MultipleOrderFactory implements OrderFactory{
    @Override
    public MultipleOrder createOrder(OrderDetails orderDetails) {
        return new MultipleOrder(orderDetails);
    }
}
