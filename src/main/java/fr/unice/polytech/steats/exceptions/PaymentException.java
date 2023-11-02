package fr.unice.polytech.steats.exceptions;

public class PaymentException extends Exception {
    public PaymentException(){
        super("payment failure!");
    }
}
