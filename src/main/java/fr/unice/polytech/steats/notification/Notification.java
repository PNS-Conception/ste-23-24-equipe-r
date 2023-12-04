package fr.unice.polytech.steats.notification;

import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Notification {
    private final UUID id;
    private final User user;
    final Map<String, Object> event;

    public User getUser() {
        return user;
    }


    public Notification(Map<String, Object> event, User user) {
        this.id = UUID.randomUUID();
        this.event = event;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public Map<String, Object> getEvent() {
        return event;
    }
}
