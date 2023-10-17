package fr.unice.polytech.steats.model;

public class User {


    private String name;
    private String userID;
    private String email;
    private String password;

    public User(String userID, String email, String password, String name) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.name="User";
    }

    public User() {
    }

    public String getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
