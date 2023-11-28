package fr.unice.polytech.steats.notification;
import fr.unice.polytech.steats.order.Subscriber;
import fr.unice.polytech.steats.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotificationRegistry implements Subscriber {
    NotificationRepository notificationRepository;

    public NotificationRegistry(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void update(Notification notification) {
        notificationRepository.save(notification,notification.getId());
    }

    public List<Notification> findByUser(User user){

        Iterable<Notification> notifications = notificationRepository.findAll();
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification: notifications)
            if(notification.getUserId()==user.getId())
                userNotifications.add(notification);
        return userNotifications;
    }
}
