package fr.unice.polytech.steats.steatspico.entities.users;

import fr.unice.polytech.steats.steatspico.interfaces.users.Recipient;

import java.util.UUID;

public class User implements Recipient {
    private final String name;
    private final UUID id;
    private UserRole userRole;

    public User() {
        this.id = UUID.randomUUID();
        this.name = "User";
        userRole = UserRole.CAMPUS_MANAGER;
    }

    public User(String name){
        this.name = name;
        this.id = UUID.randomUUID();
        userRole = UserRole.CAMPUS_MANAGER;
    }

    public User(String name, UserRole userRole){
        this.name = name;
        this.id = UUID.randomUUID();
        this.userRole = userRole;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRole getRole() {
        return userRole;
    }

}
