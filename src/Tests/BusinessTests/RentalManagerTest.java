package Tests.BusinessTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.util.List;
import Business.RentalManager;
import Business.Rental;
import Business.Interfaces.IRental;
import Persistence.RentalDataAccess;

class RentalManagerTest {

	private RentalManager rentalManager;
	

	@BeforeEach
	void setUp() {
		RentalDataAccess dataAccess = RentalDataAccess.getInstance();
		dataAccess.getRentalList().clear();
		dataAccess.saveRentalList(dataAccess.getRentalList());

		rentalManager = new RentalManager();
	}

	@Test
	void testAddAndGetRental() {
		IRental rental = new Rental(1, LocalDate.now(), 1, 1, LocalDate.now(), LocalDate.now().plusDays(7), 100.0);
		rentalManager.addRental(rental);
		IRental retrievedRental = rentalManager.getRentalById(1);
		assertNotNull(retrievedRental);
		assertEquals(1, retrievedRental.getId());
		assertEquals(1, retrievedRental.getCustomerId());
		assertEquals(1, retrievedRental.getEquipmentId());
		assertEquals(100.0, retrievedRental.getCost(), 0.001);
	}

	@Test
	void testUpdateRental() {
		IRental rental = new Rental(2, LocalDate.now(), 2, 2, LocalDate.now(), LocalDate.now().plusDays(7), 150.0);
		rentalManager.addRental(rental);
		rental.setCost(200.0);
		rentalManager.updateRental(rental);
		IRental updatedRental = rentalManager.getRentalById(2);
		assertNotNull(updatedRental);
		assertEquals(200.0, updatedRental.getCost(), 0.001);
	}

	@Test
	void testDeleteRental() {
		IRental rental = new Rental(3, LocalDate.now(), 3, 3, LocalDate.now(), LocalDate.now().plusDays(7), 200.0);
		rentalManager.addRental(rental);
		rentalManager.deleteRentalById(3);
		IRental deletedRental = rentalManager.getRentalById(3);
		assertNull(deletedRental);
	}

	@Test
	void testGetRentalsByCustomerId() {
		IRental rental1 = new Rental(4, LocalDate.now(), 4, 4, LocalDate.now(), LocalDate.now().plusDays(7), 250.0);
		IRental rental2 = new Rental(5, LocalDate.now(), 4, 5, LocalDate.now(), LocalDate.now().plusDays(7), 300.0);
		rentalManager.addRental(rental1);
		rentalManager.addRental(rental2);
		List<IRental> rentalsByCustomer = rentalManager.getRentalsByCustomerId(4);
		assertEquals(2, rentalsByCustomer.size());
		assertTrue(rentalsByCustomer.stream().anyMatch(r -> r.getId() == 4));
		assertTrue(rentalsByCustomer.stream().anyMatch(r -> r.getId() == 5));
	}

	@Test
	void testGetRentalsByEquipmentId() {
		IRental rental1 = new Rental(6, LocalDate.now(), 5, 6, LocalDate.now(), LocalDate.now().plusDays(7), 350.0);
		IRental rental2 = new Rental(7, LocalDate.now(), 6, 6, LocalDate.now(), LocalDate.now().plusDays(7), 400.0);
		rentalManager.addRental(rental1);
		rentalManager.addRental(rental2);
		List<IRental> rentalsByEquipment = rentalManager.getRentalsByEquipmentId(6);
		assertEquals(2, rentalsByEquipment.size());
	}

	@Test
	void testDeleteAllRentals() {
		IRental rental1 = new Rental(8, LocalDate.now(), 7, 8, LocalDate.now(), LocalDate.now().plusDays(7), 450.0);
		IRental rental2 = new Rental(9, LocalDate.now(), 8, 9, LocalDate.now(), LocalDate.now().plusDays(7), 500.0);
		rentalManager.addRental(rental1);
		rentalManager.addRental(rental2);
		rentalManager.deleteAllRentals();
		List<IRental> allRentals = rentalManager.getAllRentals();
		assertTrue(allRentals.isEmpty());
	}

	@Test
	void testDeleteRentalsByCustomerId() {
		IRental rental1 = new Rental(10, LocalDate.now(), 9, 10, LocalDate.now(), LocalDate.now().plusDays(7), 550.0);
		IRental rental2 = new Rental(11, LocalDate.now(), 9, 11, LocalDate.now(), LocalDate.now().plusDays(7), 600.0);
		rentalManager.addRental(rental1);
		rentalManager.addRental(rental2);
		rentalManager.deleteRentalsByCustomerId(9);
		List<IRental> rentalsByCustomer = rentalManager.getRentalsByCustomerId(9);
		assertTrue(rentalsByCustomer.isEmpty());
	}

	@Test
	void testGetRentalNotFound() {
		IRental rental = rentalManager.getRentalById(999);
		assertNull(rental);
	}

	@Test
	void testDeleteNonExistingRental() {
		assertDoesNotThrow(() -> rentalManager.deleteRentalById(999));
	}

	@Test
	void testPersistenceAfterAdd() {
		IRental rental = new Rental(12, LocalDate.now(), 10, 12, LocalDate.now(), LocalDate.now().plusDays(7), 650.0);
		rentalManager.addRental(rental);
		RentalDataAccess dataAccess = RentalDataAccess.getInstance();
		List<IRental> rentalsFromFile = dataAccess.getRentalList();
		assertTrue(rentalsFromFile.stream().anyMatch(r -> r.getId() == 12));
	}
}
