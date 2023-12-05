package fr.unice.polytech.steats.payment;

import fr.unice.polytech.steats.users.CampusUser;

public class PaymentProxyMock implements Bank{

    @Override
    public boolean executePayment(CampusUser user, double amount) {
        return true;
    }
}
