package Business.Reports;

import java.util.List;

import Business.Customer;
import Business.EquipmentManager;
import Business.Interfaces.ICategory;
import Business.Interfaces.IRental;
import Business.Reports.Interfaces.ISalesByCustomerReport;
import Persistence.CategoryDataAccess;
import Persistence.ICategoryDataAccess;
import Persistence.RentalDataAccess;

public class SalesByCustomerReport implements ISalesByCustomerReport
{

	public String generateReport(Customer customer, RentalDataAccess dataAccess) 
	{
		List<IRental> rentals = dataAccess.loadRentalList();
		EquipmentManager equipmentManager = new EquipmentManager();
		
        if (rentals == null || rentals.isEmpty()) 
        {
            return "No categories found.";
        }
        
        StringBuilder report = new StringBuilder();

        // Fancy Header
        report.append("╔════════════════════════════════════════════════════════════════════════════╗\n");
        report.append("║                    CUSTOMER SALES REPORT                                    ║\n");
        report.append("╠════════════════════════════════════════════════════════════════════════════╣\n\n");
        


            // Table Header
        report.append("╔═════╤═══════╤══════╤═══════════╤═════════════╤═════════════╤═══════════════╗\n");
        report.append("║  ID ║ FIRST ║ LAST ║ EQUIPMENT ║ RENTAL DATE ║ RETURN DATE ║ COST          ║\n");
        report.append("╠═════╪═══════╪══════╪═══════════╪═════════════╪═════════════╪═══════════════╣\n");

            // Table Rows
            for (IRental rental : rentals) {
                int id = rental.getId();
                // Truncate name if it's too long to keep table alignment

                
                report.append(String.format("║ %3d ║ %-10s ║ %-10s ║ %-10s ║\n", 
                    rental.getId(), 
                    customer.getFirstName() + customer.getLastName(), equipmentManager.,rental.getRentalDate()));
            }

            // Table Footer
            report.append("╚═════╧══════════════════════════════════════════════════════════════════════╝\n\n");

            // Summary
            report.append("Total Categories: ")
                  .append(categories.size())
                  .append("\n");

            return report.toString();
        }

        // Keep your main method for testing
        public static void main(String[] args) {
            CategoryListReport c = new CategoryListReport();
            CategoryDataAccess d = CategoryDataAccess.getInstance();
            System.out.println(c.generateReport(d));
        }

		return null;
	}

}
