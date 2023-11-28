package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.restaurant.Menu;

public class CampusUser extends User {
    private final Cart cart;
    // --Commented out by Inspection (28/11/2023 22:22):private double balance;

    private final CampusUserStatus status;

    // --Commented out by Inspection (28/11/2023 22:21):private List<SimpleOrder> previousSimpleOrders;



    public CampusUser() {
        super();
        this.cart = new Cart();
        this.status = CampusUserStatus.EXTERNAL;
    }

    public CampusUser(String name) {
        super(name);
        this.cart = new Cart();
        this.status = CampusUserStatus.EXTERNAL;
    }

    public CampusUser(String name, CampusUserStatus status) {
        super(name);
        this.cart = new Cart();
        this.status = status;
    }


    public Cart getCart() {
        return this.cart;
    }


    public void addMenuToCart(Menu mn){
        this.cart.addMenu(mn);
    }

    public CampusUserStatus getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return "CampusUser{" +
                "cart=" + cart +
                ", status=" + status ;
    }
}
