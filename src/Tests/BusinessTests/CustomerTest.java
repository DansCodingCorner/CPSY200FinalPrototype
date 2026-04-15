package Tests.BusinessTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Business.Customer;

class CustomerTest extends Customer {

	public CustomerTest(int id, String name, String email, String phoneNumber, boolean isBanned, double discountRate) {
		super(id, name, email, phoneNumber, isBanned, discountRate);
		//TODO Auto-generated constructor stub
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
