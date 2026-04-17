package Persistence;

import java.util.ArrayList;
import java.util.List;

import Business.Rental;
import Business.Interfaces.IRental;

public interface IRentalDataAccess 
{
	public List<IRental> getRentalList();
	
	public void setRentalList(List<IRental> rentalList);
    /**Method: saveRentalList
     *@Params: List<IRental>
     *@Returns: Void
     *Method to save changes to the rentals.txt file.
     *Intended to be called fromt the rental
     *manager class to update any changes made to the rentaal
     *list
     */
    void saveRentalList(List<IRental> rentalList);

	/**Method: loadRentalsList
	 * @Params: None
	 * @Returns: ArrayList<IRental>
	 *Method to load data fromt he rentals.txt file.
	 *Called upon program initalization by the rental manager class
	 */
	List<IRental> loadRentalList();


}
