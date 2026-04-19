package Business.Reports.Interfaces;

import Business.Customer;
import Persistence.RentalDataAccess;

public interface ISalesByCustomerReport 
{
	public String generateReport(Customer customer, RentalDataAccess dataAccess); 
}
