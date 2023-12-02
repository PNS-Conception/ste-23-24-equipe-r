package fr.unice.polytech.steats.exceptions.order;

public class ClosedGroupOrderException extends Exception {
    public ClosedGroupOrderException(String groupOrderCode) {
        super("closed group order exception : "+groupOrderCode);
    }
}
