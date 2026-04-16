package Persistence;

import java.util.ArrayList;

import Business.Customer;

public interface ICustomerDataAccess 
{
	public  void saveCustomerList(ArrayList<Customer> customerList);
	
	public ArrayList<Customer> loadCustomerList();
}
