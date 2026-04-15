package Business.Interfaces;

public interface IRental {
    int getId();
    void setId(int id);
    int getCustomerId();
    void setCustomerId(int customerId);
    int getEquipmentId();
    void setEquipmentId(int equipmentId);
    String getRentalDate();
    void setRentalDate(String rentalDate);
    String getReturnDate();
    void setReturnDate(String returnDate);
}
