package Business;




import Business.Interfaces.IRentalManager;
import Persistence.IRentalDataAccess;
import Business.Interfaces.IRental;
import java.util.ArrayList;
import java.util.List;

public class RentalManager implements IRentalManager{
    
    private IRentalDataAccess rentalDataAccess;

    public RentalManager(IRentalDataAccess rentalDataAccess) {
        this.rentalDataAccess = rentalDataAccess;
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
}
