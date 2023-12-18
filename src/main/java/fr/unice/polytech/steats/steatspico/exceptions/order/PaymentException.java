package fr.unice.polytech.steats.steatspico.exceptions.order;

public class PaymentException extends Exception {
    public PaymentException(){
        super("payment failure!");
    }
}
