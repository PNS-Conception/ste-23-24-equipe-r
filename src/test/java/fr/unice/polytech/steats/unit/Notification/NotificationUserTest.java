package fr.unice.polytech.steats.unit.Notification;
import fr.unice.polytech.steats.steatspico.entities.notification.Notification;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;
import fr.unice.polytech.steats.steatspico.entities.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class NotificationUserTest {

    private Notification notification;

    @BeforeEach
    void setUp() {
        User user = new CampusUser();
        Map<String, Object> event = new HashMap<>();
        event.put("key", "value");

        notification = new Notification(event, user);
    }

    @Test
    void testNotificationInitialization() {
        assertNotNull(notification.getId());
        assertNotNull(notification.getEvent());
        assertNotNull(notification.getRecipient());
    }

    @Test
    void testGetters() {
        assertEquals(notification.getId(), notification.getId());
        assertEquals(notification.getEvent(), notification.getEvent());
        assertEquals(notification.getRecipient(), notification.getRecipient());
    }


}