package fr.unice.polytech.steats.exceptions.restaurant;

public class AlreadyExistingRestaurantException extends Exception{
    public AlreadyExistingRestaurantException(String name){
        super("user "+name+" already exists");
    }
}
