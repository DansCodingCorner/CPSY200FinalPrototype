package Tests.PersistenceTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import Business.Customer;
import Business.Interfaces.ICustomer;
import Persistence.CustomerDataAccess;

class CustomerDataAccessTest {

	private CustomerDataAccess dataAccess;

	@BeforeEach
	void setUp() throws Exception {
		dataAccess = CustomerDataAccess.getInstance();

		dataAccess.getCustomerList().clear();
		dataAccess.saveCustomerList(dataAccess.getCustomerList());
	}

	@Test
	void testAddCustomer() {
		ICustomer customer = new Customer(1, "John", "Doe", "john.doe@example.com", "1234567890", false, 0.1);
		dataAccess.getCustomerList().add(customer);
		dataAccess.saveCustomerList(dataAccess.getCustomerList());
		List<ICustomer> loadedCustomers = dataAccess.loadCustomerList();
		assertEquals(1, loadedCustomers.size());
		assertEquals("John", loadedCustomers.get(0).getFirstName());
	}

	@Test
	void testLoadCustomerList() {
		ICustomer customer1 = new Customer(1, "John", "Doe", "john.doe@example.com", "1234567890", false, 0.1);
		dataAccess.getCustomerList().add(customer1);
		dataAccess.saveCustomerList(dataAccess.getCustomerList());
		List<ICustomer> loadedCustomers = dataAccess.loadCustomerList();
		
		assertFalse(loadedCustomers.isEmpty());
	}

	@Test
	void testSaveCustomerList() {
		ICustomer customer1 = new Customer(1, "John", "Doe", "john.doe@example.com", "1234567890", false, 0.1);
		dataAccess.getCustomerList().add(customer1);
		dataAccess.saveCustomerList(dataAccess.getCustomerList());
		List<ICustomer> loadedCustomers = dataAccess.loadCustomerList();
		
		assertFalse(loadedCustomers.isEmpty());
	}
}