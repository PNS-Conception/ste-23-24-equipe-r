package fr.unice.polytech.steats.payment;

import fr.unice.polytech.steats.exceptions.order.PaymentException;
import fr.unice.polytech.steats.users.CampusUser;

public interface Payment {
    void completePayment(CampusUser user) throws PaymentException;
}
