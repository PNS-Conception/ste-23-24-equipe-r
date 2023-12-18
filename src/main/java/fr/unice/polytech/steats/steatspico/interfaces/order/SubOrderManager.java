package fr.unice.polytech.steats.steatspico.interfaces.order;

import fr.unice.polytech.steats.steatspico.exceptions.order.ClosedGroupOrderException;
import fr.unice.polytech.steats.steatspico.exceptions.order.EmptyCartException;
import fr.unice.polytech.steats.steatspico.exceptions.order.NonExistentGroupOrder;
import fr.unice.polytech.steats.steatspico.exceptions.order.PaymentException;
import fr.unice.polytech.steats.steatspico.exceptions.restaurant.DeliveryDateNotAvailable;
import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.SimpleOrder;
import fr.unice.polytech.steats.steatspico.entities.order.GroupOrder;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;

import java.util.Optional;

public interface SubOrderManager {
    void addSubOrder(String groupOrderCode, OrderDetails orderDetails)
            throws NonExistentGroupOrder, ClosedGroupOrderException, EmptyCartException, PaymentException, DeliveryDateNotAvailable;

    Optional<SimpleOrder> locateSubOrder(GroupOrder groupOrder, CampusUser customer);
}
