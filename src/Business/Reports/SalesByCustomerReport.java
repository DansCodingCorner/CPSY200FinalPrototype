package Business.Reports;
import java.util.List;


import Business.EquipmentManager;
import Business.Interfaces.ICustomer;
import Business.Interfaces.IRental;
import Business.Reports.Interfaces.ISalesByCustomerReport;
import Persistence.IRentalDataAccess;
import Persistence.RentalDataAccess;

public class SalesByCustomerReport implements ISalesByCustomerReport
{
	IRentalDataAccess dataAccess;
	
	public SalesByCustomerReport()
	{
		this.dataAccess = RentalDataAccess.getInstance();
	}

	public String generateReport(ICustomer customer)
	{
	    List<IRental> rentals = dataAccess.getRentalList();
	    EquipmentManager equipmentManager = new EquipmentManager();

	    if (rentals == null || rentals.isEmpty())
	    {
	        return "No rentals found.";
	    }

	    StringBuilder report = new StringBuilder();

	    report.append("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n");
	    report.append(String.format("║               SALES REPORT FOR: %-76s ║\n",
	            customer.getFirstName() + " " + customer.getLastName()));
	    report.append("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n\n");

	    
	    report.append("╔══════╤══════════════╤══════════════╤══════════════════════════════╤══════════════╤══════════════╤════════════╗\n");
	    report.append("║  ID  ║ FIRST NAME   ║ LAST NAME    ║ EQUIPMENT                    ║ RENTAL DATE  ║ RETURN DATE  ║    COST    ║\n");
	    report.append("╠══════╪══════════════╪══════════════╪══════════════════════════════╪══════════════╪══════════════╪════════════╣\n");

	    double totalCost = 0.0;
	    int rentalCount = 0;

	    // Table Rows
	    for (IRental rental : rentals)
	    {
	        if (rental.getCustomerId() == customer.getId())
	        {
	            String fullNameFirst = customer.getFirstName() != null ? customer.getFirstName() : "";
	            String fullNameLast  = customer.getLastName()  != null ? customer.getLastName()  : "";
	            String equipmentName = equipmentManager.searchEquipment(rental.getEquipmentId()).getName();

	            String rentalDateStr = rental.getRentalDate() != null ? rental.getRentalDate().toString() : "N/A";
	            String returnDateStr = rental.getReturnDate() != null ? rental.getReturnDate().toString() : "N/A";

	            report.append(String.format("║ %4d ║ %-12s ║ %-12s ║ %-28s ║ %-12s ║ %-12s ║ $%9.2f ║\n",
	                    rental.getId(),
	                    fullNameFirst,
	                    fullNameLast,
	                    equipmentName != null ? equipmentName : "Unknown",
	                    rentalDateStr,
	                    returnDateStr,
	                    rental.getCost()));

	            totalCost += rental.getCost();
	            rentalCount++;
	        }
	    }

	    // Table Footer
	    report.append("╚══════╧══════════════╧══════════════╧══════════════════════════════╧══════════════╧══════════════╧════════════╝\n\n");

	    // Summary
	    report.append("Total Rentals: ").append(rentalCount).append("\n");
	    report.append(String.format("Total Amount : $%.2f\n", totalCost));

	    return report.toString();
	}


}
