package fr.unice.polytech.steats.exceptions;

public class AlreadyExistingRestaurantException extends Exception{
    public AlreadyExistingRestaurantException(String name){
        super("user "+name+" already exists");
    }
}
