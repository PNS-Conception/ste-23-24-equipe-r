package fr.unice.polytech.steats.steatspico.entities.order.factory;


import fr.unice.polytech.steats.steatspico.entities.order.MultipleOrder;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;



public class MultipleOrderFactory implements OrderFactory{
    @Override
    public MultipleOrder createOrder(OrderDetails orderDetails) {
        return new MultipleOrder(orderDetails);
    }
}
