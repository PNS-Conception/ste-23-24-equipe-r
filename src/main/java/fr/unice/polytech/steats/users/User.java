package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.exceptions.order.NoOrdersPlacedException;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.order.OrderVolume;
import fr.unice.polytech.steats.stats.StatisticsManager;

import java.util.List;
import java.util.UUID;

public abstract class User {
    private String name;
    private UUID id;

    public User() {
        this.id = UUID.randomUUID();
        this.name = "User";
    }

    public User(String name){
        this.name = name;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderVolumesOverTimeByUser() throws NoOrdersPlacedException {
        StatisticsManager statisticsManager = new StatisticsManager();
        return statisticsManager.getOrderVolumesOverTime();
    }
}
