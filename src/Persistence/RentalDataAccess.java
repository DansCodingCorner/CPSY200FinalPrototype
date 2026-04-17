package Persistence;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Business.Rental;
import Business.Interfaces.IRental;


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
	private List<IRental> rentalList;
	
	//File path for the retnals.txt file
	private final String rentalFilePath = "src/data/rentals.txt";

	
	//Private constructor for singleton pattern
	private RentalDataAccess()
	{
		this.rentalList = this.loadRentalList();
	}
	
	
	/**Method: loadRentalsList
	 * @Params: None
	 * @Returns: ArrayList<IRental>
	 *Method to load data fromt he rentals.txt file.
	 *Called upon program initalization by the rental manager class
	 */
	@Override
	public List<IRental> loadRentalList() 
	{
		
		List<IRental> rentalList = new ArrayList<>();
		
        try (BufferedReader br = new BufferedReader(new FileReader(rentalFilePath))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
            	String[] lineDeconstructed  = line.split(",");
				if (lineDeconstructed.length != 7) {
					System.out.println("Invalid line format in rentals.txt: " + line);
					continue;
				}
            	try {
            		IRental rentalToAdd = new Rental(
						Integer.parseInt(lineDeconstructed[0]), 
						LocalDate.parse(lineDeconstructed[1]), 
						Integer.parseInt(lineDeconstructed[2]), 
						Integer.parseInt(lineDeconstructed[3]), 
						LocalDate.parse(lineDeconstructed[4]), 
						LocalDate.parse(lineDeconstructed[5]), 
						Double.parseDouble(lineDeconstructed[6]));
            		rentalList.add(rentalToAdd);
            	} catch (Exception e) {
            		System.out.println("Error parsing line in rentals.txt: " + line);
            		e.printStackTrace();
            	}
            }
			this.rentalList = rentalList;
            return rentalList;
        } 
        
        catch (IOException e) 
        {
            e.printStackTrace();
        }
		this.rentalList = rentalList;
        System.out.println("rentals.txt file is empty");
		return rentalList;
	}


	/**Method: saveRentalList
	 *@Params: List<IRental>
	 *@Returns: Void
	 *Method to save changes to the rentals.txt file.
	 *Intended to be called fromt the rental
	 *manager class to update any changes made to the rentaal
	 *list
	 */
	@Override
	public void saveRentalList(List<IRental> rentalList) 
	{
		
		List<String> dataToAdd = new ArrayList<>();
		
		for(IRental rental : rentalList)
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
	public List<IRental> getRentalList()
	{
		
		return this.rentalList;
	}

	public void setRentalList(List<IRental> rentalList) {
		this.rentalList = rentalList;
	}

}
