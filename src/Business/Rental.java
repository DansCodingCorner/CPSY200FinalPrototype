package Business;

import java.time.LocalDate;

public class Rental 
{
	private int id;
	private LocalDate currentDate;
	private int customerId;
	private int equipmentId;
	private LocalDate rentalDate;
	private LocalDate returnDate;
	private double cost;
	
	public Rental(int id, LocalDate currentDate, int customerId, int equipmentId, LocalDate rentalDate, LocalDate returnDate, double cost)
	{
		this.id = id;
		this.currentDate = currentDate;
		this.customerId = customerId;
		this.equipmentId = equipmentId;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		this.currentDate = currentDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	

}
