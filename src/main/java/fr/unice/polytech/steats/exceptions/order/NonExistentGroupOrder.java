package fr.unice.polytech.steats.exceptions.order;

public class NonExistentGroupOrder extends Exception {

    public NonExistentGroupOrder(String code){
        super("Non existent group order "+code);
    }
}
