package fr.unice.polytech.steats.payment;

import fr.unice.polytech.steats.cart.CartService;
import fr.unice.polytech.steats.exceptions.order.InsufficientBalanceException;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.users.CampusUser;

public class PaymentManager {
    private ExternalPaymentMock externalPaymentMock;
    public PaymentManager(ExternalPaymentMock externalPaymentMock){
        this.externalPaymentMock = externalPaymentMock;
    }

    public void completePayment(CampusUser user) throws PaymentException, InsufficientBalanceException {
        CartService cartService = new CartService(user.getCart());
        double totalPrice = cartService.getPriceForUserType(user);
        if (!externalPaymentMock.executePayment(user, totalPrice)){
            throw new PaymentException();
        }
    }

}
