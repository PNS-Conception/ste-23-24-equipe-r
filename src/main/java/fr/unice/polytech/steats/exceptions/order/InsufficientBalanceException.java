package fr.unice.polytech.steats.exceptions.order;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(){
        super("Not enough balance!");
    }
}
