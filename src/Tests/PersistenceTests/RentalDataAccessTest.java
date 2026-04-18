package Tests.PersistenceTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import Persistence.RentalDataAccess;
import Business.Interfaces.IRental;
import Business.Rental;
import java.time.LocalDate;

class RentalDataAccessTest {

	private RentalDataAccess dataAccess;

	@BeforeEach
	void setUp() throws Exception {
		dataAccess = RentalDataAccess.getInstance();

		dataAccess.getRentalList().clear();
		dataAccess.saveRentalList(dataAccess.getRentalList());
	}

	@Test
	void testAddRental() {
		IRental rental = new Rental(1, LocalDate.of(2024,2,13), 1, 1, LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-10"), 100.0);
		dataAccess.getRentalList().add(rental);
		dataAccess.saveRentalList(dataAccess.getRentalList());
		List<IRental> loadedRentals = dataAccess.loadRentalList();
		assertEquals(1, loadedRentals.size());
		assertEquals(1, loadedRentals.get(0).getCustomerId());
	}
	
	@Test
	void testLoadRentals() {
		IRental rental1 = new Rental(1, LocalDate.of(2024,2,13), 1, 1, LocalDate.parse("2024-01-01"), LocalDate.parse("2024-01-10"), 100.0);
		IRental rental2 = new Rental(2, LocalDate.of(2024,2,14), 2, 2, LocalDate.parse("2024-02-01"), LocalDate.parse("2024-02-10"), 150.0);
		dataAccess.getRentalList().add(rental1);
		dataAccess.getRentalList().add(rental2);
		dataAccess.saveRentalList(dataAccess.getRentalList());
		List<IRental> loadedRentals = dataAccess.loadRentalList();
		assertEquals(2, loadedRentals.size());
		assertEquals(1, loadedRentals.get(0).getCustomerId());
		assertEquals(2, loadedRentals.get(1).getCustomerId());
	}

	@Test
	void testSaveRentalList() {
		IRental rental = new Rental(3, LocalDate.of(2024,3,15), 3, 3, LocalDate.parse("2024-03-01"), LocalDate.parse("2024-03-10"), 200.0);
		dataAccess.getRentalList().add(rental);
		dataAccess.saveRentalList(dataAccess.getRentalList());
		List<IRental> loadedRentals = dataAccess.loadRentalList();
		assertEquals(1, loadedRentals.size());
		assertEquals(3, loadedRentals.get(0).getCustomerId());
	}

	@Test
	void testEmptyRentalList() {
		dataAccess.getRentalList().clear();
		dataAccess.saveRentalList(dataAccess.getRentalList());
		List<IRental> loadedRentals = dataAccess.loadRentalList();
		assertTrue(loadedRentals.isEmpty());
	}

}
