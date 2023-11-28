package fr.unice.polytech.steats.users;

public class DeliveryPerson extends User {
    String phoneNumber;


    public DeliveryPerson(String deliveryPersonName) {
        super(deliveryPersonName,UserRole.DELIVERY_PERSON);
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
