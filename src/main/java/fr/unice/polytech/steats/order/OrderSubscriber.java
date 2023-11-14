package fr.unice.polytech.steats.order;
import java.util.Map;
public interface OrderSubscriber {
    void update(Map<String, Object> event);
}
