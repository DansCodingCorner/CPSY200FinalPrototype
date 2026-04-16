package Persistence;

import java.io.*;
import java.util.ArrayList;

import Business.Interfaces.ICustomer;


public class CustomerDataAccess implements ICustomerDataAccess
{
	
	static CustomerDataAccess instance;
	
	private final String customerFilePath = "src/data/customers.txt";
	
	private ArrayList<ICustomer> customerList;
	
	public CustomerDataAccess()
	{
		this.customerList = this.loadCustomerList();
	}

	@Override
	public ArrayList<ICustomer> loadCustomerList() 
	{
		ArrayList<ICustomer> customerList = new ArrayList<>();
		
        try (BufferedReader br = new BufferedReader(new FileReader(customerFilePath))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
            	String[] lineDeconstrcuted  = line.split(",");
            	ICustomer customerToAdd = new ICustomer (Integer.parseInt(lineDeconstrcuted[0]), lineDeconstrcuted[1], lineDeconstrcuted[2], lineDeconstrcuted[3],lineDeconstrcuted[4],Boolean.parseBoolean(lineDeconstrcuted[5]), Double.parseDouble(lineDeconstrcuted[6]));
            	customerList.add(customerToAdd);
            }
            return customerList;
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        System.out.println("categories.txt file is empty");
		return customerList;
	}

	@Override
	public void saveCustomerList(ArrayList<Customer> customerList) 
	{
		ArrayList<String> dataToAdd = new ArrayList<>();
		
		for(Customer customer : customerList)
		{
			String data = Integer.toString(customer.getId()) + "," + customer.getFirstName() + "," + customer.getLastName() + "," + customer.getEmail() + "," + customer.getPhoneNumber() + "," + customer.isBanned() + "," + customer.getDiscountRate();
			dataToAdd.add(data);
		}
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customerFilePath))) 
        {
		for(String data : dataToAdd)
		{
            writer.write(data);
            writer.newLine();
        } 
        }
		catch (IOException e) 
		{
            e.printStackTrace();
        }
		
	}

	public  static CustomerDataAccess getInstance() 
	{
		if(instance == null)
		{
			instance = new CustomerDataAccess();
		}
		
		return instance;
	}
	
	public ArrayList<Customer> getCustomerList()
	{
		return this.customerList;
	}

}

