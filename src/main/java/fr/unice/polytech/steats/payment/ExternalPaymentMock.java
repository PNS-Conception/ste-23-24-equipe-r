package fr.unice.polytech.steats.payment;

import fr.unice.polytech.steats.exceptions.order.InsufficientBalanceException;
import fr.unice.polytech.steats.users.CampusUser;

public class ExternalPaymentMock{
    public boolean executePayment(CampusUser user, double amount) throws InsufficientBalanceException {
        user.pay(amount);
        return true;
    }
}
