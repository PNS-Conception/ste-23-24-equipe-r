package fr.unice.polytech.steats.payment;

import fr.unice.polytech.steats.order.Subscriber;
import fr.unice.polytech.steats.users.CampusUser;

public class ExternalPaymentMock{
    public boolean executePayment(CampusUser user, double amount) {
        return true;
    }
}
