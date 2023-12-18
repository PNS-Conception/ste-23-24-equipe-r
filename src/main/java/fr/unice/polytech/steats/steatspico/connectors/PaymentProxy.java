package fr.unice.polytech.steats.steatspico.connectors;

import fr.unice.polytech.steats.steatspico.interfaces.payment.Bank;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

public class PaymentProxy implements Bank {

    @Override
    public boolean executePayment(CampusUser user, double amount) {
        return true;
    }
}
