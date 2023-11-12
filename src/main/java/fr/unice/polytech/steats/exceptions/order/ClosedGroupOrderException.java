package fr.unice.polytech.steats.exceptions.order;

public class ClosedGroupOrderException extends Throwable {
    public ClosedGroupOrderException(String groupOrderCode) {
        super("closed group order exception : "+groupOrderCode);
    }
}
