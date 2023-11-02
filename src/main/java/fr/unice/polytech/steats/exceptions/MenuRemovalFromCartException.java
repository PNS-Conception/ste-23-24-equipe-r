package fr.unice.polytech.steats.exceptions;

public class MenuRemovalFromCartException extends Exception{
    public MenuRemovalFromCartException(){
        super("Failed to remove menu item from cart.");
    }
}
