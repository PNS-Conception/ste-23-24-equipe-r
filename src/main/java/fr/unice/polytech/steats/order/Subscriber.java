package fr.unice.polytech.steats.order;
import fr.unice.polytech.steats.notification.Notification;

public interface Subscriber {
    void update(Notification notification);
}
