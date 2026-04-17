package Business.Interfaces;

public interface IRentalManager {
    void addRental(IRental rental);
    java.util.List<IRental> getAllRentals();
    IRental getRentalById(int id);
    boolean updateRental(IRental rental);
    void deleteRentalById(int id);
    java.util.List<IRental> getRentalsByCustomerId(int customerId);
    java.util.List<IRental> getRentalsByEquipmentId(int equipmentId);
    void saveRentals();
    void deleteRentalsByCustomerId(int customerId);
    void deleteRentalsByEquipmentId(int equipmentId);
    void deleteAllRentals();
}
