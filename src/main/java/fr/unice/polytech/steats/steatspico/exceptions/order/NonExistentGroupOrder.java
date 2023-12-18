package fr.unice.polytech.steats.steatspico.exceptions.order;

public class NonExistentGroupOrder extends Exception {

    public NonExistentGroupOrder(String code){
        super("Non existent group order "+code);
    }
}
