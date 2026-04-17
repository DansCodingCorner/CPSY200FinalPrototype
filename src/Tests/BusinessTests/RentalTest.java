package Tests.BusinessTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import Business.Rental;
import java.time.format.DateTimeParseException;

class RentalTest {

	@Test
	void testRentalCreation() {
		Rental rental = new Rental(1, LocalDate.now(), 1, 1, LocalDate.now(), LocalDate.now().plusDays(7), 100.0);
		assertNotNull(rental);
		assertEquals(1, rental.getId());
		assertEquals(1, rental.getCustomerId());
		assertEquals(1, rental.getEquipmentId());
		assertEquals(100.0, rental.getCost(), 0.001);
	}

	@Test
	void testRentalSetters() {
		Rental rental = new Rental(2, LocalDate.now(), 2, 2, LocalDate.now(), LocalDate.now().plusDays(7), 150.0);
		rental.setCost(200.0);
		assertEquals(200.0, rental.getCost(), 0.001);
	}

	@Test
	void testRentalDateHandling() {
		Rental rental = new Rental(3, LocalDate.now(), 3, 3, LocalDate.now(), LocalDate.now().plusDays(7), 200.0);
		assertEquals(LocalDate.now(), rental.getCurrentDate());
		assertEquals(LocalDate.now(), rental.getRentalDate());
		assertEquals(LocalDate.now().plusDays(7), rental.getReturnDate());
	}

	@Test
	void testRentalIdHandling() {
		Rental rental = new Rental(4, LocalDate.now(), 4, 4, LocalDate.now(), LocalDate.now().plusDays(7), 250.0);
		assertEquals(4, rental.getId());
		rental.setId(5);
		assertEquals(5, rental.getId());
	}

	@Test
	void testRentalCustomerAndEquipmentIdHandling() {
		Rental rental = new Rental(6, LocalDate.now(), 5, 6, LocalDate.now(), LocalDate.now().plusDays(7), 300.0);
		assertEquals(5, rental.getCustomerId());
		assertEquals(6, rental.getEquipmentId());
		rental.setCustomerId(7);
		rental.setEquipmentId(8);
		assertEquals(7, rental.getCustomerId());
		assertEquals(8, rental.getEquipmentId());
	}

	@Test
	void testRentalCostHandling() {
		Rental rental = new Rental(8, LocalDate.now(), 6, 7, LocalDate.now(), LocalDate.now().plusDays(7), 350.0);
		assertEquals(350.0, rental.getCost(), 0.001);
		rental.setCost(400.0);
		assertEquals(400.0, rental.getCost(), 0.001);
	}

	@Test
	void testRentalDateSetters() {
		Rental rental = new Rental(9, LocalDate.now(), 7, 8, LocalDate.now(), LocalDate.now().plusDays(7), 450.0);
		rental.setRentalDate(LocalDate.now().minusDays(1));
		rental.setReturnDate(LocalDate.now().plusDays(8));
		assertEquals(LocalDate.now().minusDays(1), rental.getRentalDate());
		assertEquals(LocalDate.now().plusDays(8), rental.getReturnDate());
	}

	@Test
	void testRentalToString() {
		Rental rental = new Rental(10, LocalDate.now(), 8, 9, LocalDate.now(), LocalDate.now().plusDays(7), 500.0);
		String expectedString = "Rental [id=10, currentDate=" + LocalDate.now() + ", customerId=8, equipmentId=9, rentalDate=" + LocalDate.now() + ", returnDate=" + LocalDate.now().plusDays(7) + ", cost=500.0]";
		assertEquals(expectedString, rental.toString());
	}

	@Test
	void testRentalWithInvalidData() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Rental(-1, LocalDate.now(), -1, -1, LocalDate.now(), LocalDate.now().plusDays(7), -100.0);
		});
	}

	@Test
	void testRentalWithNullDates() {
		assertThrows(NullPointerException.class, () -> {
			new Rental(11, null, 9, 10, null, null, 550.0);
		});
	}

	@Test
	void testRentalWithInvalidDateFormat() {
		Rental rental = new Rental(12, LocalDate.now(), 10, 11, LocalDate.now(), LocalDate.now().plusDays(7), 600.0);
		assertThrows(DateTimeParseException.class, () -> {
			rental.setRentalDate("invalid-date");
		});
		assertThrows(DateTimeParseException.class, () -> {
			rental.setReturnDate("invalid-date");
		});
	}
}
