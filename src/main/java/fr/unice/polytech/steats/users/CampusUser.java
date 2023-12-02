package fr.unice.polytech.steats.users;

import fr.unice.polytech.steats.cart.Cart;
import fr.unice.polytech.steats.notification.order.OrderSubscriber;
import fr.unice.polytech.steats.order.Order;
import fr.unice.polytech.steats.restaurant.Menu;

public class CampusUser extends User implements OrderSubscriber {
    private Cart cart;
    // --Commented out by Inspection (28/11/2023 22:22):private double balance;

    private CampusUserStatus status;

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
    public void setStatus(CampusUserStatus status) {
        this.status = status;
    }

    @Override
    public void update(Order order) {
        if(order.getCustomers().contains(this)){
            System.out.println("The update of your order"+ order.getId() + " is : " + order.getStatus());
            System.out.println("The delivery location of your order"+ order.getId() + " is : " + order.getDeliveryLocation());
        }
    }
}
