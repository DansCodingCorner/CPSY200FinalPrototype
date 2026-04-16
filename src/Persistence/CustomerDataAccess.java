package Persistence;

import java.io.*;
import java.util.ArrayList;

import Business.Customer;

/**Class: CustomerDataAccess
 * Singleton class for interacting with the Customer.txt file
 * Can load the data from the file and update
 * changes to the file
 */
public class CustomerDataAccess implements ICustomerDataAccess
{
	//Singleton instance for RentalDataAccess class
	static CustomerDataAccess instance;
	
	//File path for customer.txt file
	private final String customerFilePath = "src/data/customers.txt";
	
	//Internal customer list
	private ArrayList<Customer> customerList;
	
	//private constructor to be called from getInstance method
	private CustomerDataAccess()
	{
		this.customerList = this.loadCustomerList();
	}

	/**Method: loadCustoemrList
	 * @param: none
	 * @return: ArrayLlst<Customer>: A list of customers generated from the custoemrs.txt file
	 * This method is called by the customerManager class upon initializaiton to load the customer data from customers.txt file
	 */
	@Override
	public ArrayList<Customer> loadCustomerList() 
	{
		ArrayList<Customer> customerList = new ArrayList<>();
		
        try (BufferedReader br = new BufferedReader(new FileReader(customerFilePath))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
            	String[] lineDeconstrcuted  = line.split(",");
            	Customer customerToAdd = new Customer (Integer.parseInt(lineDeconstrcuted[0]), lineDeconstrcuted[1], lineDeconstrcuted[2], lineDeconstrcuted[3],lineDeconstrcuted[4],Boolean.parseBoolean(lineDeconstrcuted[5]), Double.parseDouble(lineDeconstrcuted[6]));
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

	/**Method: saveCustomerList
	 * @param ArrayList<Customer>
	 *@return void
	 *Method to save changes to the customers.txt file when provided with the updated
	 *arrayList fromt he customer manager class
	 */
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

	/**CustomerDataAccess.getInstance
	 * @param none
	 * @return singleton isntance of customerDataAccess
	 * Singleton consstructor for the custoemrDataAccessclass
	 */
	public  static CustomerDataAccess getInstance() 
	{
		if(instance == null)
		{
			instance = new CustomerDataAccess();
		}
		
		return instance;
	}
	
	/**Method:getCustoemrList
	 * @param none
	 * @return ArrayList<Customer>
	 * Getter for the internal customer list
	 */
	public ArrayList<Customer> getCustomerList()
	{
		return this.customerList;
	}

}

