package Presentation;

public class UImanager implements IUIManager
{

    @Override
    public void displayMainMenu() {
        System.out.println("\n=== Equipment Rental System ===");
        System.out.println("1. Customer Management");
        System.out.println("2. Equipment Management");
        System.out.println("3. Rental Management");
        System.out.println("4. Exit");
    }

    @Override
    public void displayCustomerMenu() {
        System.out.println("\n=== Customer Management ===");
        System.out.println("1. Add Customer");
        System.out.println("2. View All Customers");
        System.out.println("3. Search Customer by Last Name");
        System.out.println("4. View Banned Customers");
        System.out.println("5. Update Customer");
        System.out.println("6. Remove Customer");
        System.out.println("7. Back to Main Menu");
    }

    @Override
    public void displayEquipmentMenu() {
        System.out.println("\n=== Equipment Management ===");
        System.out.println("1. Add Equipment");
        System.out.println("2. View All Equipment");
        System.out.println("3. Search Equipment by Name");
        System.out.println("4. View Available Equipment");
        System.out.println("5. Update Equipment");
        System.out.println("6. Remove Equipment");
        System.out.println("7. Back to Main Menu");
    }

    @Override
    public void displayRentalMenu() {
        System.out.println("\n=== Rental Management ===");
        System.out.println("1. Rent Equipment");
        System.out.println("2. Return Equipment");
        System.out.println("3. View All Rentals");
        System.out.println("4. Back to Main Menu");
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayError(String errorMessage) {
        System.err.println("Error: " + errorMessage);
    }

    @Override
    public void displayCustomerDetails(String customerDetails) {
        System.out.println("Customer Details:");
        System.out.println(customerDetails);
    }

    @Override
    public void displayEquipmentDetails(String equipmentDetails) {
        System.out.println("Equipment Details:");
        System.out.println(equipmentDetails);
    }

    @Override
    public void displayRentalDetails(String rentalDetails) {
        System.out.println("Rental Details:");
        System.out.println(rentalDetails);
    }

    

}
