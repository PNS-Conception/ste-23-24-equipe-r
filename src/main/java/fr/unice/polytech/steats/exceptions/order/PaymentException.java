package fr.unice.polytech.steats.exceptions.order;

public class PaymentException extends Exception {
    public PaymentException(){
        super("payment failure!");
    }
}
