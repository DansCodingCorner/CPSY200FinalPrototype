package Business.Reports;

import java.util.List;
import Business.Customer;
import Business.Interfaces.IRental;
import Business.Reports.Interfaces.ISalesByCustomerReport;
import Persistence.RentalDataAccess;

public class SalesByCustomerReport implements ISalesByCustomerReport {

    public String generateReport(Customer customer, RentalDataAccess dataAccess) {

        List<IRental> rentals = dataAccess.loadRentalList();

        // Filter rentals for this specific customer
        List<IRental> customerRentals = rentals.stream()
                .filter(r -> r.getCustomerId() == customer.getId())
                .toList();

        if (customerRentals.isEmpty()) {
            return "No rentals found for customer: " + customer.getFirstName() + " " + customer.getLastName();
        }

        StringBuilder report = new StringBuilder();

        // Fancy Header with Customer Name
        report.append("╔════════════════════════════════════════════════════════════════════════════════════╗\n");
        report.append(String.format("║                  SALES REPORT FOR CUSTOMER: %-45s ║\n",
                customer.getFirstName() + " " + customer.getLastName()));
        report.append("╠════════════════════════════════════════════════════════════════════════════════════╣\n\n");

        // Table Header
        report.append("╔══════╤══════════════╤══════════════╤══════════════════════════════════════╤══════════════╤══════════════╤════════════╗\n");
        report.append("║  ID  ║ Last Name    ║ First Name   ║ Equipment Rented                     ║ Rental Date  ║ Return Date  ║   Cost     ║\n");
        report.append("╠══════╪══════════════╪══════════════╪══════════════════════════════════════╪══════════════╪══════════════╪════════════╣\n");

        double totalCost = 0.0;

        // Table Rows
        for (IRental rental : customerRentals) {
            String lastName = customer.getLastName();
            String firstName = customer.getFirstName();

            // Note: Since we don't have Equipment name here, we'll show Equipment ID for now
            String equipmentInfo = "Equipment ID: " + rental.getEquipmentId();

            report.append(String.format("║ %4d ║ %-12s ║ %-12s ║ %-36s ║ %-12s ║ %-12s ║ $%9.2f ║\n",
                    rental.getId(),
                    lastName,
                    firstName,
                    equipmentInfo,
                    rental.getRentalDate(),
                    rental.getReturnDate(),
                    rental.getCost()));

            totalCost += rental.getCost();
        }

        // Table Footer
        report.append("╚══════╧══════════════╧══════════════╧══════════════════════════════════════╧══════════════╧══════════════╧════════════╝\n\n");

        // Summary
        report.append(String.format("Total Rentals: %d\n", customerRentals.size()));
        report.append(String.format("Total Amount: $%.2f\n", totalCost));

        return report.toString();
    }
}