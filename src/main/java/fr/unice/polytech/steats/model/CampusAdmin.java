package fr.unice.polytech.steats.model;

import java.util.ArrayList;

public class CampusAdmin extends User{

    ArrayList<String> notifications = new ArrayList<>();
    public CampusAdmin(String name){
        super(name);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public void addNotification(String notif){
        notifications.add(notif);
    }
}
