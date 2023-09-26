package fr.unice.polytech.biblio;

import java.util.Date;

public class CampusUser {

    private String name;

    private String timeSlot;

    public CampusUser(){
        this.name = "CampusUser";
        this.timeSlot= "2023-09-26 10:00:00";
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

}
