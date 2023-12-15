package fr.unice.polytech.steats.notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationRegistry {
    final NotificationRepository notificationRepository;
    private static NotificationRegistry instance = null;

    private NotificationRegistry(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public static NotificationRegistry getInstance() {
        if(instance == null){
            instance = new NotificationRegistry(new NotificationRepository());
        }
        return instance;
    }

    public void add(Notification notification) {
        notificationRepository.save(notification,notification.getId());
    }

    public List<Notification> findByRecipient(Recipient user){
        Iterable<Notification> notifications = notificationRepository.findAll();
        List<Notification> userNotifications = new ArrayList<>();
        for (Notification notification: notifications)
            if(notification.getRecipient().equals(user))
                userNotifications.add(notification);
        return userNotifications;
    }

    public NotificationRepository getNotificationRepository() {
        return notificationRepository;
    }
}
