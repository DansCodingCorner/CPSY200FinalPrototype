package Business.Reports;

<<<<<<< HEAD
import java.util.List;

import Business.Customer;
import Business.Equipment;
import Business.EquipmentManager;
import Business.Interfaces.IRental;
import Business.Reports.Interfaces.ISalesByCustomerReport;
import Persistence.EquipmentDataAccess;
import Persistence.IEquipmentDataAccess;
import Persistence.RentalDataAccess;
=======
import Business.Reports.Interfaces.ISalesByCustomerReport;
>>>>>>> ee62980901fc2729db76176d5873b434777997c4

public class SalesByCustomerReport implements ISalesByCustomerReport
{

<<<<<<< HEAD
	public String generateReport(Customer customer, RentalDataAccess dataAccess)
	{
	    List<IRental> rentals = dataAccess.loadRentalList();
	    EquipmentManager equipmentManager = new EquipmentManager();

	    if (rentals == null || rentals.isEmpty())
	    {
	        return "No rentals found.";
	    }

	    StringBuilder report = new StringBuilder();

	    // Fancy Header with Customer Name
	    report.append("╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════╗\n");
	    report.append(String.format("║               SALES REPORT FOR: %-76s ║\n",
	            customer.getFirstName() + " " + customer.getLastName()));
	    report.append("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════╣\n\n");

	    // Table Header - Better aligned
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

	            // Safe string conversion for dates
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

        // Keep your main method for testing
        public static void main(String[] args) 
        {
        	Customer customer = new Customer(1001,"John","Doe","jd@sample.net","(555) 555-1212",false,0.00);
            SalesByCustomerReport c = new SalesByCustomerReport();
            RentalDataAccess d = RentalDataAccess.getInstance();
            System.out.println(c.generateReport(customer, d));
        }

	}

=======
	public String generateReport() 
	{
		return null;
	}

}
>>>>>>> ee62980901fc2729db76176d5873b434777997c4
