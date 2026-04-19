package Business.Reports.Interfaces;

import Business.Interfaces.ICustomer;

public interface ISalesByCustomerReport 
{
	public String generateReport(ICustomer customer); 
}
