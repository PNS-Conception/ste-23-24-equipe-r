package fr.unice.polytech.steats.notification;

import java.util.UUID;

public class Notification {
    private UUID Id;
    private UUID userId;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public Notification() {
        this.Id= UUID.randomUUID();
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }


}
