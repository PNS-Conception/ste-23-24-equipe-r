package fr.unice.polytech.steats.notification;

import java.util.Map;
import java.util.UUID;

public class Notification {
    private final UUID id;
    private final Recipient recipient;

    private final Map<String, Object> event;

    public Recipient getRecipient() {
        return recipient;
    }

    public Notification(Map<String, Object> event, Recipient recipient) {
        this.id = UUID.randomUUID();
        this.event = event;
        this.recipient = recipient;
    }

    public UUID getId() {
        return id;
    }

    public Map<String, Object> getEvent() {
        return event;
    }
}
