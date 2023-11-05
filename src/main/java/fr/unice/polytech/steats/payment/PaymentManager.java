package fr.unice.polytech.steats.payment;

import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.users.CampusUser;

public class PaymentManager {
    private ExternalPaymentMock externalPaymentMock;
    public PaymentManager(ExternalPaymentMock externalPaymentMock){
        this.externalPaymentMock = externalPaymentMock;
    }

    public void completePayment(CampusUser user) throws PaymentException {
        double totalPrice = user.getCart().getPrice();
        if (!externalPaymentMock.executePayment(user, totalPrice)){
            throw new PaymentException();
        }
    }

}
