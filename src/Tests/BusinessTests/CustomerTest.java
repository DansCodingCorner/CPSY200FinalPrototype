package Tests.BusinessTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Business.Customer;

class CustomerTest {

    @Test
    void testCustomerCreation() {
        Customer c = new Customer(1, "John", "Doe", "john@email.com", "1234567890", false, 0.1);

        assertEquals(1, c.getId());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("john@email.com", c.getEmail());
        assertEquals("1234567890", c.getPhoneNumber());
        assertFalse(c.isBanned());
        assertEquals(0.1, c.getDiscountRate());
    }

    @Test
    void testSetters() {
        Customer c = new Customer(1, "John", "Doe", "a", "1", false, 0.0);

        c.setFirstName("Jane");
        c.setLastName("Smith");
        c.setEmail("new@email.com");
        c.setPhoneNumber("999");
        c.setBanned(true);
        c.setDiscountRate(0.2);

        assertEquals("Jane", c.getFirstName());
        assertEquals("Smith", c.getLastName());
        assertEquals("new@email.com", c.getEmail());
        assertEquals("999", c.getPhoneNumber());
        assertTrue(c.isBanned());
        assertEquals(0.2, c.getDiscountRate());
    }

    @Test
    void testToString() {
        Customer c = new Customer(1, "John", "Doe", "a", "123", true, 0.0);

        String result = c.toString();

        assertTrue(result.contains("John"));
        assertTrue(result.contains("Doe"));
        assertTrue(result.contains("123"));
        assertTrue(result.contains("BANNED"));
    }

    @Test
    void testDiscountRate() {
        Customer c = new Customer(1, "John", "Doe", "a", "1", false, 0.0);

        c.setDiscountRate(0.25);

        assertEquals(0.25, c.getDiscountRate());
    }
}