package fr.unice.polytech.steats.users;

public abstract class User {
    private String name;
    private String userID;

    public User() {
        this.userID = "0";
        this.name = "User";
    }

    public User(String name){
        this.name = name;
        this.userID = "0";
    }

    public User(String userID, String name) {
        this.userID = userID;
        this.name=name;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
