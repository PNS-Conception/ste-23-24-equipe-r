package fr.unice.polytech.steats.notification;
import fr.unice.polytech.steats.order.Subscriber;
import fr.unice.polytech.steats.users.User;

import java.util.ArrayList;
import java.util.List;

public class NotificationRegistry implements Subscriber {
    final NotificationRepository notificationRepository;

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
            if(notification.getUsers().contains(user))
                userNotifications.add(notification);
        return userNotifications;
    }
}
