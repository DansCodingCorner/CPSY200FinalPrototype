package Persistence;

import java.util.ArrayList;

import Business.Interfaces.ICustomer;

public interface ICustomerDataAccess 
{
	public  void saveCustomerList(ArrayList<ICustomer> customerList);
	
	public ArrayList<ICustomer> loadCustomerList();
}
