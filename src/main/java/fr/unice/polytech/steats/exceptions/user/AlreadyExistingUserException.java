package fr.unice.polytech.steats.exceptions.user;

public class AlreadyExistingUserException extends Exception {
    public AlreadyExistingUserException(String name){
        super("user "+name+" already exists");
    }
}
