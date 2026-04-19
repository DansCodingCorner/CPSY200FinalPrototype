package Business;




import Business.Interfaces.IRentalManager;
import Persistence.IRentalDataAccess;
import Persistence.RentalDataAccess;
import Business.Interfaces.IRental;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.temporal.ChronoUnit;

public class RentalManager implements IRentalManager{
    
    private IRentalDataAccess rentalDataAccess;
    private int nextRentalId = 1;

   

    public RentalManager() {
        this.rentalDataAccess = RentalDataAccess.getInstance();;
    }

    @Override
    public void addRental(IRental rental) {
        List<IRental> rentals = rentalDataAccess.getRentalList();
        rentals.add(rental);
        rentalDataAccess.saveRentalList(rentals);

    }

    @Override
    public List<IRental> getAllRentals() {
        return rentalDataAccess.getRentalList();
    }

    @Override
    public IRental getRentalById(int id) {
        for (IRental rental : rentalDataAccess.getRentalList()) {
            if (rental.getId() == id) {
                return rental;
            }
        }
        return null;
    }

    @Override
    public boolean updateRental(IRental updatedRental) {
        List<IRental> rentals = rentalDataAccess.getRentalList();
        for (int i = 0; i < rentals.size(); i++) {
            if (rentals.get(i).getId() == updatedRental.getId()) {
                rentals.set(i, updatedRental);
                rentalDataAccess.saveRentalList(rentals);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<IRental> getRentalsByCustomerId(int customerId) {
        List<IRental> rentals = new ArrayList<>();
        for (IRental rental : rentalDataAccess.getRentalList()) {
            if (rental.getCustomerId() == customerId) {
                rentals.add(rental);
            }
        }
        return rentals;
    }

    @Override
    public List<IRental> getRentalsByEquipmentId(int equipmentId) {
        List<IRental> rentals = new ArrayList<>();
        for (IRental rental : rentalDataAccess.getRentalList()) {
            if (rental.getEquipmentId() == equipmentId) {
                rentals.add(rental);
            }
        }
        return rentals;
    }

    @Override
    public void deleteRentalById(int id) {
        List<IRental> rentals = rentalDataAccess.getRentalList();
        boolean removed = rentals.removeIf(rental -> rental.getId() == id);
        if (removed) {
            rentalDataAccess.saveRentalList(rentals);
        }
    }

    @Override
    public void saveRentals() {
        rentalDataAccess.saveRentalList(rentalDataAccess.getRentalList());
    }

    @Override
    public void deleteRentalsByCustomerId(int customerId) {
        List<IRental> rentals = rentalDataAccess.getRentalList();
        boolean removed = rentals.removeIf(rental -> rental.getCustomerId() == customerId);
        if (removed) {
            rentalDataAccess.saveRentalList(rentals);
        }
    }

    @Override
    public void deleteRentalsByEquipmentId(int equipmentId) {
        List<IRental> rentals = rentalDataAccess.getRentalList();
        boolean removed = rentals.removeIf(rental -> rental.getEquipmentId() == equipmentId);
        if (removed) {
            rentalDataAccess.saveRentalList(rentals);
        }
    }

    @Override
    public void deleteAllRentals() {
        List<IRental> rentals = rentalDataAccess.getRentalList();
        if (!rentals.isEmpty()) {
            rentals.clear();
            rentalDataAccess.saveRentalList(rentals);
        }
    }

    @Override
    public double calculateCost (LocalDate rentalDate, LocalDate returnDate, double dailyRate) {

        long daysRented = ChronoUnit.DAYS.between(rentalDate, returnDate);

        if (daysRented < 0) {
            throw new IllegalArgumentException("Return date cannot be before rental date.");
        }
        return daysRented * dailyRate;
    }

    @Override
    public void createRental(int id, LocalDate currentDate, int customerId, int equipmentId, LocalDate rentalDate, LocalDate returnDate, double dailyRate) {
        double cost = calculateCost(rentalDate, returnDate, dailyRate);
        IRental rental = new Rental(id, currentDate, customerId, equipmentId, rentalDate, returnDate, cost, false);
        addRental(rental);
    }

    @Override
    public double getOutstandingFeesByCustomerId(int customerId) {
        double totalFees = 0.0;
        LocalDate currentDate = LocalDate.now();

        for (IRental rental : getRentalsByCustomerId(customerId)) {
            if (rental.getReturnDate().isBefore(currentDate)) {
                long daysOverdue = ChronoUnit.DAYS.between(rental.getReturnDate(), currentDate);
                totalFees += daysOverdue * 5.0; // Assuming a flat fee of $5 per day overdue
            }
        }
        return totalFees;
    }

    @Override
    public int createRentalId() {
        return nextRentalId++;
    }

}    
