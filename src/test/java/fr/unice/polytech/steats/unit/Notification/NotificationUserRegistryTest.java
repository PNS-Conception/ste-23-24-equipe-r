package fr.unice.polytech.steats.unit.Notification;

import fr.unice.polytech.steats.steatspico.entities.notification.Notification;
import fr.unice.polytech.steats.steatspico.components.NotificationRegistry;
import fr.unice.polytech.steats.steatspico.repositories.NotificationRepository;
import fr.unice.polytech.steats.steatspico.entities.users.CampusUser;
import fr.unice.polytech.steats.steatspico.entities.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class NotificationUserRegistryTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationRegistry notificationRegistry;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdd() {
        // Given
        User user = new CampusUser();
        Notification notification = new Notification(new HashMap<>(), user);

        // When
        notificationRegistry.add(notification);

        // Then
        verify(notificationRepository, times(1)).save(any(Notification.class), any(UUID.class));

    }

    @Test
    void testFindByUser() {
        // Given
        User user = new CampusUser();
        Notification notification1 = new Notification(new HashMap<>(), user);
        Notification notification2 = new Notification(new HashMap<>(), user);

        // When
        when(notificationRepository.findAll()).thenReturn(List.of(notification1, notification2));
        List<Notification> userNotifications = notificationRegistry.findByRecipient(user);

        // Then
        assertEquals(2, userNotifications.size());
        assertEquals(user, userNotifications.get(0).getRecipient());
        assertEquals(user, userNotifications.get(1).getRecipient());
    }

}
