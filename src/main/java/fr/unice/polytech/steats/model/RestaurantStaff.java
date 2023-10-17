package fr.unice.polytech.steats.model;

public class RestaurantStaff extends User{

    public RestaurantStaff(){
        super();
    }

    public RestaurantStaff(String name){
        super(name);
    }
    public RestaurantStaff(String userID, String name) {
        super(userID,name);
    }

}
