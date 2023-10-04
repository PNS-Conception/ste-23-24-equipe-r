package fr.unice.polytech.biblio;

import java.util.Date;

public class CampusUser {

    private String name;

    private String timeSlot;

    private int id;
    private String password;

    public CampusUser(){
        this.name = "CampusUser";
        this.timeSlot= "2023-09-26 10:00:00";
        this.id = 0;
        this.password = "";
    }

    public CampusUser(String name, int id, String password){
        this.name = name;
        this.id = id;
        this.password = password;
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
        return this.id;
    }

    public String getPassword() {
        return password;
    }
}
