package fr.unice.polytech.steats.users;

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
}
