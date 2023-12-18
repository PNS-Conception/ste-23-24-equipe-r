package fr.unice.polytech.steats.steatspico.exceptions.cart;

public class MenuRemovalFromCartException extends Exception{
    public MenuRemovalFromCartException(){
        super("Quantity of menus to remove is too large");
    }
}
