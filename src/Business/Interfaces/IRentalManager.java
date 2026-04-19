package Business.Interfaces;
import java.time.LocalDate;

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
    double calculateCost(LocalDate rentalDate, LocalDate returnDate, double dailyRate);
    void createRental(int id, LocalDate currentDate, int customerId, int equipmentId, LocalDate rentalDate, LocalDate returnDate, double dailyRate);
    double getOutstandingFeesByCustomerId(int customerId);
    int createRentalId();
    
}
