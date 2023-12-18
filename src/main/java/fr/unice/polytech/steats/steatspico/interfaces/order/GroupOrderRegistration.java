package fr.unice.polytech.steats.steatspico.interfaces.order;

import fr.unice.polytech.steats.steatspico.entities.order.OrderDetails;
import fr.unice.polytech.steats.steatspico.entities.order.GroupOrder;

public interface GroupOrderRegistration {
    GroupOrder register(OrderDetails orderDetails);
}
