package Persistence;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import Business.Rental;

/**RentalDataaccess class used to interact
 * with the rentals.txt file.
 * Includes methods to load the file and
 * to save data to it.
 * Singleton class, use the getInstance method to isntantiate.
 * Author: Daniel Caron
 * Last Edit April 16 2026
 * 
 * 
 */
public class RentalDataAccess implements IRentalDataAccess
{
	
	//singleton instance of the rental data acccess object
	static RentalDataAccess instance = null;
	
	//Inetrnal array of rental objects for the getter method
	private ArrayList<Rental> rentalList;
	
	//File path for the retnals.txt file
	private final String rentalFilePath = "src/data/customers.txt";

	
	//Private constructor for singleton pattern
	private RentalDataAccess()
	{
		this.rentalList = this.loadRentalList();
	}
	
	
	/**Method: loadRentalsList
	 * @Params: None
	 * @Returns: ArrayList<Rental>
	 *Method to load data fromt he rentals.txt file.
	 *Called upon program initalization by the rental manager class
	 */
	@Override
	public ArrayList<Rental> loadRentalList() 
	{
		
		ArrayList<Rental> rentalList = new ArrayList<>();
		
        try (BufferedReader br = new BufferedReader(new FileReader(rentalFilePath))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
            	String[] lineDeconstrcuted  = line.split(",");
            	Rental RentalToAdd = new Rental (Integer.parseInt(lineDeconstrcuted[0]), LocalDate.parse(lineDeconstrcuted[1]), Integer.parseInt(lineDeconstrcuted[2]), Integer.parseInt(lineDeconstrcuted[3]), LocalDate.parse(lineDeconstrcuted[4]), LocalDate.parse(lineDeconstrcuted[5]), Double.parseDouble(lineDeconstrcuted[6]));
            	rentalList.add(RentalToAdd);
            }
            return rentalList;
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        System.out.println("rentals.txt file is empty");
		return rentalList;
	}


	/**Method: saveRentalList
	 *@Params: ArrayList<Rental>
	 *@Reutnrs: Void
	 *Method to save changes to the rentals.txt file.
	 *Intended to be called fromt the rental
	 *manager class to update any changes made to the rentaal
	 *list
	 */
	@Override
	public void saveRentalList(ArrayList<Rental> rentalList) 
	{
		
		ArrayList<String> dataToAdd = new ArrayList<>();
		
		for(Rental rental : rentalList)
		{
			String data = Integer.toString(rental.getId()) + "," + rental.getCurrentDate() + "," + rental.getCustomerId() + "," + rental.getEquipmentId() + "," + rental.getRentalDate() + "," + rental.getReturnDate() + "," + rental.getCost();
			dataToAdd.add(data);
		}
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rentalFilePath))) 
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

	
	/**Method: RentalDataAccess.getInstance
	 * @param: none
	 * @return: Singleton RentalDataAccess
	 * Singleton constructor for the rental
	 * Data access class.
	 */
	public static RentalDataAccess getInstance()
	{
		
		if(instance ==null)
		{
			instance = new RentalDataAccess();
		}
		
		return instance;
	}
	
	/**Method: getRentalList
	 * @param: None
	 * @return: ArrayList<Rental>
	 * Getter for the internal rental list stored by the 
	 * Rental Data Access class
	 */
	public ArrayList<Rental> getRentalList()
	{
		
		return this.rentalList;
	}

}
