package Persistence;

import java.util.ArrayList;

import Business.Rental;

public interface IRentalDataAccess 
{
	public ArrayList<Rental> loadRentalList();
	
	public void saveRentalList(ArrayList<Rental> rentalList);
}
