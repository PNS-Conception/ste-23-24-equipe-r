package fr.unice.polytech.steats.users;

import java.util.UUID;

public class User {
    private String name;
    private UUID id;
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

    public void setName(String name) {
        this.name = name;
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
