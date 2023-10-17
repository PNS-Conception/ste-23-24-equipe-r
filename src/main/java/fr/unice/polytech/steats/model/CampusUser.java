package fr.unice.polytech.steats.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CampusUser extends User {

    private String name;

    private List<Menu> cart = new ArrayList<>();

    private String timeSlot;

    private int campusUserId;
    private String password;

    public CampusUser(){
        super();
        this.name = "CampusUser";
        this.timeSlot= "2023-09-26 10:00:00";
        this.campusUserId = 0;
        this.password = "";
    }

    public CampusUser(String name, int id, String password){
        super();
        this.name = name;
        this.campusUserId = id;
        this.password = password;
    }

    public CampusUser(String name){
        this.name = name;
        this.timeSlot= "2023-09-26 10:00:00";
        this.campusUserId = 0;
        this.password = "";
    }


    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getTimeSlot(){
        return this.timeSlot;
    }

    public void setTimeSlot(String timeSlot){
        this.timeSlot = timeSlot;
    }

    public int getId(){
        return this.campusUserId;
    }

    public String getPassword() {
        return password;
    }

    public void addMenuToCart(Menu menufromName) {
        cart.add(menufromName);
    }

    public List<Menu> getCart() {
        return cart;
    }
}
