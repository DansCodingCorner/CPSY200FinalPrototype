package Business.Interfaces;

public interface ICustomer {
    int getId();
    void setId(int id);
    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    String getEmail();
    void setEmail(String email);
    String getPhoneNumber();
    void setPhoneNumber(String phoneNumber);
    boolean isBanned();
    void setBanned(boolean banned);
    void setDiscountRate(double discountRate);
    double getDiscountRate();

}
