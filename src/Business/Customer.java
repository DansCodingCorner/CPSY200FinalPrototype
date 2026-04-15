package Business;

import Business.Interfaces.ICustomer;

public class Customer implements ICustomer {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private double discountRate;
    private boolean isBanned;

    public Customer(int id, String firstName, String lastName, String email, String phoneNumber, boolean isBanned, double discountRate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isBanned = isBanned;
        this.discountRate = discountRate;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean isBanned() { 
        return isBanned;
    }

    @Override
    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    @Override
    public String toString() {
    return id + " - " + firstName + " " + lastName + " (" + phoneNumber + ")" +
           (isBanned ? " [BANNED]" : "");
    }

    @Override   
    public double getDiscountRate() {
        return discountRate;
    }
    
    @Override
    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}
