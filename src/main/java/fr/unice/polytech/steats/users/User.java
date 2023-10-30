package fr.unice.polytech.steats.users;

import java.util.UUID;

public abstract class User {
    private String name;
    private UUID userID;

    public User() {
        this.userID = UUID.randomUUID();
        this.name = "User";
    }

    public User(String name){
        this.name = name;
        this.userID = UUID.randomUUID();
    }

    public UUID getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
