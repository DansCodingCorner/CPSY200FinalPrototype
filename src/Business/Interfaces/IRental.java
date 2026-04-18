package Business.Interfaces;

import java.time.LocalDate;

public interface IRental {
    int getId();
    void setId(int id);
    int getCustomerId();
    void setCustomerId(int customerId);
    int getEquipmentId();
    void setEquipmentId(int equipmentId);
    LocalDate getRentalDate();
    void setRentalDate(String rentalDate);
    LocalDate getReturnDate();
    void setReturnDate(String returnDate);
    LocalDate getCurrentDate();
    double getCost();
    void setCost(double cost);
    boolean isReturned();
    void setReturned(boolean isReturned);
}
