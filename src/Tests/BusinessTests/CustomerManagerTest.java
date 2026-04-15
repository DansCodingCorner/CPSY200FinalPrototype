package Tests.BusinessTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Business.Customer;
import Business.CustomerManager;
import Business.Interfaces.ICustomer;

class CustomerManagerTest {

    private CustomerManager manager;

    @BeforeEach
    void setUp() {
        manager = new CustomerManager();
    }

    @Test
    void testAddCustomer() {
        ICustomer c = new Customer(1, "John", "Doe", "john@email.com", "1234567890", false, 0.0);

        manager.addCustomer(c);

        List<ICustomer> customers = manager.getAllCustomers();

        assertEquals(1, customers.size());
        assertEquals("John", customers.get(0).getFirstName());
    }

    @Test
    void testFindCustomerById() {
        ICustomer c = new Customer(2, "Jane", "Smith", "jane@email.com", "1111111111", false, 0.0);
        manager.addCustomer(c);

        ICustomer found = manager.getCustomerById(2);

        assertNotNull(found);
        assertEquals("Jane", found.getFirstName());
    }

    @Test
    void testSearchCustomerByLastName() {
        manager.addCustomer(new Customer(1, "John", "Doe", "a", "1", false, 0.0));
        manager.addCustomer(new Customer(2, "Jane", "Doe", "b", "2", false, 0.0));

        List<ICustomer> results = manager.searchCustomerByLastName("Doe");

        assertEquals(2, results.size());
    }

    @Test
    void testRemoveCustomerById() {
        manager.addCustomer(new Customer(1, "John", "Doe", "a", "1", false, 0.0));

        boolean removed = manager.removeCustomerById(new Customer(1, "John", "Doe", "a", "1", false, 0.0));

        assertTrue(removed);
        assertEquals(0, manager.getAllCustomers().size());
    }

    @Test
    void testUpdateCustomer() {
        ICustomer c = new Customer(1, "John", "Doe", "a", "1", false, 0.0);
        manager.addCustomer(c);

        ICustomer updated = new Customer(1, "Johnny", "Doe", "a", "1", false, 0.0);

        boolean result = manager.updateCustomer(updated);

        assertTrue(result);
        assertEquals("Johnny", manager.getCustomerById(1).getFirstName());
    }

    @Test
    void testGetBannedCustomers() {
        manager.addCustomer(new Customer(1, "John", "Doe", "a", "1", true, 0.0));
        manager.addCustomer(new Customer(2, "Jane", "Smith", "b", "2", false, 0.0));

        List<ICustomer> banned = manager.getBannedCustomers();

        assertEquals(1, banned.size());
        assertTrue(banned.get(0).isBanned());
    }
}