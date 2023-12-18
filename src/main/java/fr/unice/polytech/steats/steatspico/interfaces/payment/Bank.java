package fr.unice.polytech.steats.steatspico.interfaces.payment;

import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

public interface Bank {
    boolean executePayment(CampusUser user, double amount);
}
