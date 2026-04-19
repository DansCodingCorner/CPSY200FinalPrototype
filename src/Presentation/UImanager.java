package Presentation;

import java.util.Scanner;

import Business.Interfaces.*;
import Business.*;
import java.time.LocalDate;
import Business.Equipment;


public class UImanager implements IUIManager
{
    private Scanner userInput = new Scanner(System.in);
    private ICustomerManager customerManager;
    private IEquipmentManager equipmentManager;
    private IRentalManager rentalManager;
    private ICategoryManager categoryManager;

    public UImanager() {
        this.customerManager = new CustomerManager();
        this.equipmentManager = new EquipmentManager();
        this.rentalManager = new RentalManager();
        this.categoryManager = new CategoryManager();
    }

    @Override
    public void displayMainMenu() {
        System.out.println("\n=== Equipment Rental System ===");
        System.out.println("1. Customer Management");
        System.out.println("2. Equipment Management");
        System.out.println("3. Rental Management");
        System.out.println("4. Category Management");
        System.out.println("5. Exit");

        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume newline
        System.out.print("Enter option: ");
        switch (choice) {
            case 1:
                displayCustomerMenu();
                break;
            case 2:
                displayEquipmentMenu();
                break;
            case 3:
                displayRentalMenu();
                break;
            case 4:
                displayCategoryMenu();
                break;
            case 5:
                System.out.println("Thank you for using the Equipment Rental System. Goodbye!");
                System.exit(0);
                break;
        }
    }



    @Override
    public void displayCustomerMenu() {
        System.out.println("\n=== Customer Management ===");
        System.out.println("1. Add Customer");
        System.out.println("2. View All Customers");
        System.out.println("3. Search Customer by Last Name");
        System.out.println("4. Add Banned Customer");
        System.out.println("5. View Banned Customers");
        System.out.println("6. Update Customer");
        System.out.println("7. Remove Customer");
        System.out.println("8. Back to Main Menu");

        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter ID: ");
                int id = userInput.nextInt();
                userInput.nextLine(); // Consume newline

                System.out.print("Enter First Name: ");
                String firstName = userInput.nextLine();
                
                System.out.print("Enter Last Name: ");
                String lastName = userInput.nextLine();
            
                System.out.print("Enter Email: ");
                String email = userInput.nextLine();

                System.out.print("Enter Phone Number: ");
                String phoneNumber = userInput.nextLine();

                ICustomer newCustomer = new Customer(id, firstName, lastName, email, phoneNumber, false, 0.0);
                customerManager.addCustomer(newCustomer);
                System.out.println("Customer added successfully!");
                break;
            case 2:
                System.out.println("All Customers:");
                customerManager.getAllCustomers().forEach(customer -> displayCustomerDetails(customer.toString()));
                break;
            case 3:
                System.out.print("Enter Last Name to Search: ");
                String searchLastName = userInput.nextLine();
                customerManager.searchCustomerByLastName(searchLastName).forEach(customer -> displayCustomerDetails(customer.toString()));
                break;
            case 4:
                System.out.print("Enter Customer ID to Ban: ");
                int banId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                ICustomer customerToBan = customerManager.getCustomerById(banId);
                if (customerToBan == null) {
                    System.out.println("Customer not found.");
                    break;
                }
                customerManager.banCustomerById(banId);
                System.out.println("Customer banned successfully!");
                break;
            case 5:
                System.out.println("Banned Customers:");
                customerManager.getBannedCustomers().forEach(customer -> displayCustomerDetails(customer.toString()));
                break;
            case 6:
                
                System.out.print("Enter Customer ID to Update: ");
                int updateId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                ICustomer existingCustomer = customerManager.getCustomerById(updateId);
                if (existingCustomer == null) {
                    System.out.println("Customer not found.");
                    break;
                }
                System.out.print("Enter New First Name (current: " + existingCustomer.getFirstName() + "): ");
                String newFirstName = userInput.nextLine();
                System.out.print("Enter New Last Name (current: " + existingCustomer.getLastName() + "): ");
                String newLastName = userInput.nextLine();
                System.out.print("Enter New Email (current: " + existingCustomer.getEmail() + "): ");
                String newEmail = userInput.nextLine();
                System.out.print("Enter New Phone Number (current: " + existingCustomer.getPhoneNumber() + "): ");
                String newPhoneNumber = userInput.nextLine();
                ICustomer updatedCustomer = new Customer(updateId, newFirstName, newLastName, newEmail, newPhoneNumber, existingCustomer.isBanned(), existingCustomer.getDiscountRate());
                if (customerManager.updateCustomer(updatedCustomer)) {
                    System.out.println("Customer updated successfully!");
                } else {
                    System.out.println("Failed to update customer.");
                }
                break;
            case 7:
                System.out.print("Enter Customer ID to Remove: ");
                int removeId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                ICustomer customerToRemove = customerManager.getCustomerById(removeId);
                if (customerToRemove == null) {
                    System.out.println("Customer not found.");
                    break;
                }
                if (customerManager.removeCustomerById(customerToRemove)) {
                    System.out.println("Customer removed successfully!");
                } else {
                    System.out.println("Failed to remove customer.");
                }
                break;
            case 8:
                displayMainMenu();
                break;
        }
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

        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter ID: ");
                int id = userInput.nextInt();
                userInput.nextLine(); // Consume newline

                System.out.print("Enter Name: ");
                String name = userInput.nextLine();
                
                System.out.print("Enter Description: ");
                String description = userInput.nextLine();
            
                System.out.print("Enter Price per Day: ");
                double price = userInput.nextDouble();
                userInput.nextLine(); // Consume newline

                System.out.print("Enter Category ID: ");
                int categoryId = userInput.nextInt();
                userInput.nextLine(); // Consume newline

                IEquipment newEquipment = new Equipment(id, name, categoryId, true, description, price);
                equipmentManager.addEquipment(newEquipment);
                System.out.println("Equipment added successfully!");
                break;
            case 2:
                System.out.println("All Equipment:");
                equipmentManager.getAllEquipment().forEach(equipment -> displayEquipmentDetails(equipment.toString()));
                break;
            case 3:
                System.out.print("Enter Name to Search: ");
                String searchName = userInput.nextLine();
                IEquipment foundEquipment = equipmentManager.searchEquipmentByName(searchName);
                if (foundEquipment != null) {
                    displayEquipmentDetails(foundEquipment.toString());
                } else {
                    System.out.println("Equipment not found.");
                }
                break;
            case 4:
                System.out.println("Available Equipment:");
                equipmentManager.getAllEquipment().stream()
                    .filter(IEquipment::isAvailable)
                    .forEach(equipment -> displayEquipmentDetails(equipment.toString()));
                break;
            case 5:
                System.out.print("Enter Equipment ID to Update: ");
                int updateId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                IEquipment existingEquipment = equipmentManager.getEquipmentById(updateId);
                if (existingEquipment == null) {
                    System.out.println("Equipment not found.");
                    break;
                }
                System.out.print("Enter New Name (current: " + existingEquipment.getName() + "): ");
                String newName = userInput.nextLine();
                System.out.print("Enter New Description (current: " + existingEquipment.getDescription() + "): ");
                String newDescription = userInput.nextLine();
                System.out.print("Enter New Price per Day (current: " + existingEquipment.getPrice() + "): ");
                double newPrice = userInput.nextDouble();
                userInput.nextLine(); // Consume newline
                System.out.print("Enter New Category ID (current: " + existingEquipment.getCategoryId() + "): ");
                int newCategoryId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                Equipment updatedEquipment = new Equipment(updateId, newName, newCategoryId, existingEquipment.isAvailable(), newDescription, newPrice);
                equipmentManager.updateEquipment(updatedEquipment);
                System.out.println("Equipment updated successfully!");
                break;
            case 6:
                System.out.print("Enter Equipment ID to Remove: ");
                int removeId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                IEquipment equipmentToRemove = equipmentManager.getEquipmentById(removeId);
                if (equipmentToRemove == null) {
                    System.out.println("Equipment not found.");
                    break;
                }
                equipmentManager.removeEquipment(equipmentToRemove);
                System.out.println("Equipment removed successfully!");
                break;
            case 7:
                displayMainMenu();
                break;
        }
    }

    @Override
    public void displayRentalMenu() {
        System.out.println("\n=== Rental Management ===");
        System.out.println("1. Rent Equipment");
        System.out.println("2. Return Equipment");
        System.out.println("3. View All Rentals");
        System.out.println("4. Back to Main Menu");

        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.println("Enter Customer ID: ");
                int customerId = userInput.nextInt();

                System.out.println("Enter Equipment ID: ");
                int equipmentId = userInput.nextInt();
                userInput.nextLine(); // Consume newline

                ICustomer customer = customerManager.getCustomerById(customerId);
                if (customer == null) {
                    System.out.println("Customer not found.");
                    break;
                }
                if (customer.isBanned()) {
                    System.out.println("Customer is banned and cannot rent equipment.");
                    break;
                }

                IEquipment equipment = equipmentManager.getEquipmentById(equipmentId);
                if (equipment == null) {
                    System.out.println("Equipment not found.");
                    break;
                }
                if (!equipment.isAvailable()) {
                    System.out.println("Equipment is currently unavailable.");
                    break;
                }
                
                System.out.println("Enter Rental Date (YYYY-MM-DD): ");
                String rentalDate = userInput.nextLine();

                System.out.println("Enter Return Date (YYYY-MM-DD): ");
                String returnDate = userInput.nextLine();
                
                try {
                    LocalDate rentalLocalDate = LocalDate.parse(rentalDate);
                    LocalDate returnLocalDate = LocalDate.parse(returnDate);
                    
                    rentalManager.createRental(rentalManager.createRentalId(), rentalLocalDate, customerId, equipmentId, rentalLocalDate, returnLocalDate, equipment.getPrice());

                    equipmentManager.updateEquipmentAvailability(equipmentId, false);
                System.out.println("Rental created successfully!" + " Total Cost: $" + rentalManager.calculateCost(rentalLocalDate, returnLocalDate, equipment.getPrice()));
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    break;
                }
                break;
            case 2:
                System.out.println("Enter Rental ID to Return: ");
                int rentalId = userInput.nextInt();

                IRental rental = rentalManager.getRentalById(rentalId);
                if (rental == null) {
                    System.out.println("Rental not found.");
                    break;
                }
                if (rental.isReturned()) {
                    System.out.println("Equipment has already been returned.");
                    break;
                }
                rental.setReturned(true);
                rentalManager.updateRental(rental);
                equipmentManager.updateEquipmentAvailability(rental.getEquipmentId(), true);
                System.out.println("Equipment returned successfully!");
                break;
            case 3:
                System.out.println("All Rentals:");
                for (IRental r : rentalManager.getAllRentals()) {
                    displayRentalDetails(r.toString());
                }
                break;
            case 4:
                displayMainMenu();
                break;
        }
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

    @Override
    public void displayCategoryMenu() {
        System.out.println("\n=== Category Management ===");
        System.out.println("1. Add Category");
        System.out.println("2. View All Categories");
        System.out.println("3. Search Category by Name");
        System.out.println("4. Update Category");
        System.out.println("5. Remove Category");
        System.out.println("6. Back to Main Menu");
    }

}
