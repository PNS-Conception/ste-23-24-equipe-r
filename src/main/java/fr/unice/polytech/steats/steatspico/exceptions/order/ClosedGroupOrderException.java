package fr.unice.polytech.steats.steatspico.exceptions.order;

public class ClosedGroupOrderException extends Exception {
    public ClosedGroupOrderException(String groupOrderCode) {
        super("closed group order exception : "+groupOrderCode);
    }
}
