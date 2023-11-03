package fr.unice.polytech.steats.Payment;

import fr.unice.polytech.steats.exceptions.PaymentException;
import fr.unice.polytech.steats.order.CartService;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderRepository;
import fr.unice.polytech.steats.users.CampusUser;

public class PaymentManager {
    private ExternalPaymentMock externalPaymentMock;
    public PaymentManager(ExternalPaymentMock externalPaymentMock, OrderRepository orderRepository){
        this.externalPaymentMock = externalPaymentMock;
    }

    public void completePayment(CampusUser user) throws PaymentException {
        double totalPrice = user.getCart().getPrice();
        if (!externalPaymentMock.executePayment(user, totalPrice)){
            throw new PaymentException();
        }
    }

}
