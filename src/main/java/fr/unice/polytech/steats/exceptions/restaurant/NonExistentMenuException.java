package fr.unice.polytech.steats.exceptions.restaurant;

public class NonExistentMenuException extends Exception {
        public NonExistentMenuException(String menuName){
            super("The menu "+menuName+" does not exist");
        }

}
