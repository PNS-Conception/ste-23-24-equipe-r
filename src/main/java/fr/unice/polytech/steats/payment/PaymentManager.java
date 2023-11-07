package fr.unice.polytech.steats.payment;

import fr.unice.polytech.steats.exceptions.order.InsufficientBalanceException;
import fr.unice.polytech.steats.users.CampusUser;

public class PaymentManager {
    private ExternalPaymentMock externalPaymentMock;
    public PaymentManager(ExternalPaymentMock externalPaymentMock){
        this.externalPaymentMock = externalPaymentMock;
    }

    public void completePayment(CampusUser user) throws InsufficientBalanceException {
        double totalPrice = user.getCart().getPrice();
        externalPaymentMock.executePayment(user, totalPrice);
    }
}
