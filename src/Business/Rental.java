package Business;

import java.time.LocalDate;

import Business.Interfaces.IRental;

public class Rental implements IRental
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

		if (id < 0 || customerId < 0 || equipmentId < 0 || cost < 0) {
			throw new IllegalArgumentException("ID, customer ID, equipment ID, and cost must be non-negative.");
		}

		if (currentDate == null || rentalDate == null || returnDate == null) {
			throw new IllegalArgumentException("Current date, rental date, and return date cannot be null.");
		}

		if (rentalDate.isAfter(returnDate)) {
			throw new IllegalArgumentException("Rental date cannot be after return date.");
		}
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
		if (id < 0) {
			throw new IllegalArgumentException("ID must be non-negative.");
		}
		this.id = id;
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(LocalDate currentDate) {
		if (currentDate == null) {
			throw new IllegalArgumentException("Current date cannot be null.");
		}
		this.currentDate = currentDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		if (customerId < 0) {
			throw new IllegalArgumentException("Customer ID must be non-negative.");
		}
		this.customerId = customerId;
	}

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		if (equipmentId < 0) {
			throw new IllegalArgumentException("Equipment ID must be non-negative.");
		}
		this.equipmentId = equipmentId;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		if (rentalDate == null) {
			throw new IllegalArgumentException("Rental date cannot be null.");
		}
		if (returnDate != null && rentalDate.isAfter(this.returnDate)) {
			throw new IllegalArgumentException("Rental date cannot be after return date.");
		}
		this.rentalDate = rentalDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		if (returnDate == null) {
			throw new IllegalArgumentException("Return date cannot be null.");
		}
		if (rentalDate != null && returnDate.isBefore(this.rentalDate)) {
			throw new IllegalArgumentException("Return date cannot be before rental date.");
		}
		this.returnDate = returnDate;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		if (cost < 0) {
			throw new IllegalArgumentException("Cost must be non-negative.");
		}
		this.cost = cost;
	}
	
	@Override
	public String toString() {
		return "Rental [id=" + id + ", currentDate=" + currentDate + ", customerId=" + customerId + ", equipmentId="
				+ equipmentId + ", rentalDate=" + rentalDate + ", returnDate=" + returnDate + ", cost=" + cost
				+ "]";
	}

	@Override
	public void setRentalDate(String rentalDate) {
		setRentalDate(LocalDate.parse(rentalDate));
	}

	@Override
	public void setReturnDate(String returnDate) {
		setReturnDate(LocalDate.parse(returnDate));
	}

}
