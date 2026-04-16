package Persistence;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import Business.Rental;

public class RentalDataAccess implements IRentalDataAccess
{
	
	static RentalDataAccess instance = null;
	
	private ArrayList<Rental> rentalList;
	
	private final String rentalFilePath = "src/data/customers.txt";

	private RentalDataAccess()
	{
		this.rentalList = this.loadRentalList();
	}
	
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

	
	public static RentalDataAccess getInstance()
	{
		if(instance ==null)
		{
			instance = new RentalDataAccess();
		}
		
		return instance;
	}
	
	public ArrayList<Rental> getRentalList()
	{
		return this.rentalList;
	}

}
