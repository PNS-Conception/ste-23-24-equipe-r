package fr.unice.polytech.steats.model;

import java.util.ArrayList;
import java.util.List;

public class CampusUser extends User {
    private List<Menu> cart = new ArrayList<>();
    public CampusUser(){
        super("CampusUser");
    }

    public CampusUser(String name){
        super(name);
    }

    public CampusUser(String userID, String name) {
        super(userID, name);
    }

    public void addMenuToCart(Menu menufromName) {
        cart.add(menufromName);
    }

    public List<Menu> getCart() {
        return cart;
    }
}
