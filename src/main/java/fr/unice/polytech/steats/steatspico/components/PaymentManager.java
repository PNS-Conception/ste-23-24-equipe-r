package fr.unice.polytech.steats.steatspico.components;

import fr.unice.polytech.steats.steatspico.exceptions.order.PaymentException;
import fr.unice.polytech.steats.steatspico.interfaces.payment.Payment;
import fr.unice.polytech.steats.steatspico.connectors.PaymentProxy;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

public class PaymentManager implements Payment {

    private final PaymentProxy paymentProxy;
    public PaymentManager(PaymentProxy paymentProxy){
        this.paymentProxy = paymentProxy;
    }

    @Override
    public void completePayment(CampusUser user) throws PaymentException {
        CartHandler cartHandler = new CartHandler(user.getCart());
        double totalPrice = cartHandler.getPriceForUser(user);
        if (!paymentProxy.executePayment(user, totalPrice)){
            throw new PaymentException();
        }
    }

}
