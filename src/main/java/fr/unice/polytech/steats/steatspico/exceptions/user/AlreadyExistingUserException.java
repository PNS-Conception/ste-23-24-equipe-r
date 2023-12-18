package fr.unice.polytech.steats.steatspico.exceptions.user;

public class AlreadyExistingUserException extends Exception {
    public AlreadyExistingUserException(String name){
        super("user "+name+" already exists");
    }
}
