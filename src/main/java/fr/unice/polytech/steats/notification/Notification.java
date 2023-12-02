package fr.unice.polytech.steats.notification;

import fr.unice.polytech.steats.users.CampusUser;
import fr.unice.polytech.steats.users.User;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Notification {
    private final UUID id;
    private final List<User> users;
    final Map<String, Object> event;

    public List<User> getUsers() {
        return users;
    }


    public Notification(Map<String, Object> event, List<User> users) {
        this.id = UUID.randomUUID();
        this.event = event;
        this.users = users;
    }

    public UUID getId() {
        return id;
    }


}
