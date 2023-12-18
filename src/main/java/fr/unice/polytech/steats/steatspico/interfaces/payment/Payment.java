package fr.unice.polytech.steats.steatspico.interfaces.payment;

import fr.unice.polytech.steats.steatspico.exceptions.order.PaymentException;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

public interface Payment {
    void completePayment(CampusUser user) throws PaymentException;
}
