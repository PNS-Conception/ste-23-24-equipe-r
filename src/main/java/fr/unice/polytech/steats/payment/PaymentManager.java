package fr.unice.polytech.steats.payment;

import fr.unice.polytech.steats.cart.CartHandler;
import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.notification.pickupTime.PickupTimePublisher;
import fr.unice.polytech.steats.notification.pickupTime.PickupTimeSubscriber;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Menu;
import fr.unice.polytech.steats.users.CampusUser;

public class PaymentManager implements Payment {

    private final PaymentProxyMock paymentProxyMock;
    public PaymentManager(PaymentProxyMock paymentProxyMock){
        this.paymentProxyMock = paymentProxyMock;
    }

    @Override
    public void completePayment(CampusUser user) throws PaymentException {
        CartHandler cartHandler = new CartHandler(user.getCart());
        double totalPrice = cartHandler.getPriceForUser(user);
        if (!paymentProxyMock.executePayment(user, totalPrice)){
            throw new PaymentException();
        }
    }

}
