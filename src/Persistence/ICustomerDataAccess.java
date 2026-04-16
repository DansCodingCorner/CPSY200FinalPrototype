package Persistence;

import java.util.List;

import Business.Interfaces.ICustomer;

public interface ICustomerDataAccess 
{
	public  void saveCustomerList(List<ICustomer> customerList);
	
	public List<ICustomer> loadCustomerList();

	public List<ICustomer> getCustomerList();
}
