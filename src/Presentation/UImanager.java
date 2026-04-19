package Presentation;

import java.util.Scanner;

import Business.Interfaces.*;
import Business.*;
import java.time.LocalDate;


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

        System.out.print("\nEnter option: ");
        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume newline
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
        System.out.println("4. Ban Customer");
        System.out.println("5. View Banned Customers");
        System.out.println("6. Unban Customer");
        System.out.println("7. Update Customer");
        System.out.println("8. Remove Customer");
        System.out.println("9. Back to Main Menu");

        System.out.print("\nEnter option: ");
        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                int id = customerManager.getNextCustomerId();
                System.out.println("Assigning Customer ID: " + id);

                String firstName;
                while (true) {
                System.out.print("Enter First Name (or 'q' to cancel): ");
                firstName = userInput.nextLine();
                if (firstName.equalsIgnoreCase("q")) {
                    System.out.println("Customer creation cancelled.");
                    displayCustomerMenu();
                    return;
                }

                if (firstName.trim().isEmpty()) {
                    System.out.println("First name cannot be empty. Please try again.");
                    } else {
                        break;
                    }
                }

                String lastName;
                while (true) {
                System.out.print("Enter Last Name (or 'q' to cancel): ");
                lastName = userInput.nextLine();
                if (lastName.equalsIgnoreCase("q")) {
                    System.out.println("Customer creation cancelled.");
                    displayCustomerMenu();
                    return;
                }

                if (lastName.trim().isEmpty()) {
                    System.out.println("Last name cannot be empty. Please try again.");
                    } else {
                        break;
                    }
                }

                System.out.print("Enter Email (or 'q' to cancel): ");
                String email = userInput.nextLine();
                if (email.equalsIgnoreCase("q")) {
                    System.out.println("Customer creation cancelled.");
                    displayCustomerMenu();
                    return;
                }

                String phoneNumber;
                while (true) {
                System.out.print("Enter Phone Number (or 'q' to cancel): ");
                phoneNumber = userInput.nextLine();
                if (phoneNumber.equalsIgnoreCase("q")) {
                    System.out.println("Customer creation cancelled.");
                    displayCustomerMenu();
                    return;
                }
                if (phoneNumber.trim().isEmpty()) {
                    System.out.println("Phone number cannot be empty. Please try again.");
                    } else {
                        break;
                    }
                }

                ICustomer newCustomer = new Customer(id, firstName, lastName, email, phoneNumber, false, 0.0);
                customerManager.addCustomer(newCustomer);
                displayCustomerMenu();
                break;
            case 2:
                System.out.println("All Customers:");
                customerManager.getAllCustomers().forEach(customer -> displayCustomerDetails(customer.toString()));
                displayCustomerMenu();
                break;
            case 3:
                System.out.print("Enter Last Name to Search: ");
                String searchLastName = userInput.nextLine();
                customerManager.searchCustomerByLastName(searchLastName).forEach(customer -> displayCustomerDetails(customer.toString()));
                displayCustomerMenu();
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
                displayCustomerMenu();

                break;
            case 5:
                System.out.println("Banned Customers:");
                customerManager.getBannedCustomers().forEach(customer -> displayCustomerDetails(customer.toString()));
                displayCustomerMenu();
                break;
            case 6:
                System.out.print("Enter Customer ID to Unban: ");
                int unbanId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                ICustomer customerToUnban = customerManager.getCustomerById(unbanId);
                if (customerToUnban == null) {
                    System.out.println("Customer not found.");
                    break;
                }
                customerManager.unbanCustomerById(unbanId);
                displayCustomerMenu();
                break;
            case 7:
                System.out.print("Enter 'q' and press Enter at any time to cancel update process.\n");
                System.out.print("Enter Customer ID to Update: ");
                int updateId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                ICustomer existingCustomer = customerManager.getCustomerById(updateId);
                if (existingCustomer == null) {
                    System.out.println("Customer not found.");
                    break;
                }
                System.out.print("Enter New First Name or press Enter to keep the same (current: " + existingCustomer.getFirstName() + "): ");
                String newFirstName = userInput.nextLine();
                if (newFirstName.equalsIgnoreCase("q")) {
                    System.out.println("Update cancelled.");
                    displayCustomerMenu();
                    break;
                }
                if (newFirstName.trim().isEmpty()) {
                    newFirstName = existingCustomer.getFirstName();
                }
                System.out.print("Enter New Last Name or press Enter to keep the same (current: " + existingCustomer.getLastName() + "): ");
                String newLastName = userInput.nextLine();
                if (newLastName.equalsIgnoreCase("q")) {
                    System.out.println("Update cancelled.");
                    displayCustomerMenu();
                    break;
                }
                if (newLastName.trim().isEmpty()) {
                    newLastName = existingCustomer.getLastName();
                }
                System.out.print("Enter New Email or press Enter to keep the same (current: " + existingCustomer.getEmail() + "): ");
                String newEmail = userInput.nextLine();
                if (newEmail.equalsIgnoreCase("q")) {
                    System.out.println("Update cancelled.");
                    displayCustomerMenu();
                    break;
                }
                if (newEmail.trim().isEmpty()) {
                    newEmail = existingCustomer.getEmail();
                }
                System.out.print("Enter New Phone Number or press Enter to keep the same (current: " + existingCustomer.getPhoneNumber() + "): ");
                String newPhoneNumber = userInput.nextLine();
                if (newPhoneNumber.equalsIgnoreCase("q")) {
                    System.out.println("Update cancelled.");
                    displayCustomerMenu();
                    break;
                }

                if (newPhoneNumber.trim().isEmpty()) {
                    newPhoneNumber = existingCustomer.getPhoneNumber();
                }

                ICustomer updatedCustomer = new Customer(updateId, newFirstName, newLastName, newEmail, newPhoneNumber, existingCustomer.isBanned(), existingCustomer.getDiscountRate());
                customerManager.updateCustomer(updatedCustomer);
                displayCustomerMenu();
                break;
            case 8:
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
                displayCustomerMenu();
                break;
            case 9:
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

        System.out.print("\nEnter option: ");
        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                int id = equipmentManager.getNextEquipmentId();
                System.out.println("Assigning Equipment ID: " + id);

                String name;
                while (true) {
                System.out.print("Enter Name: ");
                name = userInput.nextLine();
                if (name.trim().isEmpty()) {
                    System.out.println("Name cannot be empty. Please try again.");
                    } else {
                        break;
                    }
                }
                
                String description;
                while (true) {
                System.out.print("Enter Description: ");
                description = userInput.nextLine();
                if (description.trim().isEmpty()) {
                    System.out.println("Description cannot be empty. Please try again.");
                    } else {
                        break;
                    }
                }
                
                double price;
                while (true) {
                System.out.print("Enter Price per Day: ");
                price = userInput.nextDouble();
                userInput.nextLine(); // Consume newline
                if (price < 0) {
                    System.out.println("Price cannot be negative. Please try again.");
                    } else {
                        break;
                    }
                }

                int categoryId;
                while (true) {

                System.out.print("Enter Category ID: ");
                categoryId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                ICategory category = categoryManager.getCategoryById(categoryId);
                if (category == null) {
                    System.out.println("Category not found. Please enter a valid Category ID.");
                    } else {
                        break;
                    }
                }
                
                IEquipment newEquipment = new Equipment(id, name, categoryId, true, description, price);
                equipmentManager.addEquipment(newEquipment);
                System.out.println("Equipment added successfully!");
                displayEquipmentMenu();
                break;
            case 2:
                System.out.println("All Equipment:");
                System.out.printf("%-5s %-20s %-12s %-14s %10s%n", "ID", "Name", "Category ID", "Available", "Price/Day");
                System.out.println("-------------------------------------------------------------------------------------");
                equipmentManager.getAllEquipment().forEach(equipment -> displayEquipmentDetails(equipment.toString()));
                displayEquipmentMenu();
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
                displayEquipmentMenu();
                break;
            case 4:
                System.out.println("Available Equipment:");
                equipmentManager.getAllEquipment().stream()
                    .filter(IEquipment::isAvailable)
                    .forEach(equipment -> displayEquipmentDetails(equipment.toString()));
                displayEquipmentMenu();

                break;
            case 5:
                System.out.print("Enter Equipment ID to Update: ");
                int updateId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                IEquipment existingEquipment = equipmentManager.searchEquipment(updateId);
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
                IEquipment updatedEquipment = new Equipment(updateId, newName, newCategoryId, existingEquipment.isAvailable(), newDescription, newPrice);
                equipmentManager.updateEquipment(updatedEquipment);
                System.out.println("Equipment updated successfully!");
                displayEquipmentMenu();

                break;
            case 6:
                System.out.print("Enter Equipment ID to Remove: ");
                int removeId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                IEquipment equipmentToRemove = equipmentManager.searchEquipment(removeId);
                if (equipmentToRemove == null) {
                    System.out.println("Equipment not found.");
                    break;
                }
                equipmentManager.removeEquipment(equipmentToRemove);
                System.out.println("Equipment removed successfully!");
                displayEquipmentMenu();
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

        System.out.print("\nEnter option: ");
        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume newline

        switch (choice) {
            case 1:

                customerManager.getAllCustomers().forEach(customer -> displayCustomerDetails(customer.toString()));
                ICustomer customer;
                while (true) {
                    System.out.println("\nEnter Customer ID or 0 to cancel: ");
                    int customerId = userInput.nextInt();
                    userInput.nextLine(); // Consume newline
                    if (customerId == 0) {
                        System.out.println("Rental process cancelled.");
                        return;
                    }
                    customer = customerManager.getCustomerById(customerId);
                    if (customer == null) {
                        System.out.println("Customer not found. Please enter a valid Customer ID.");
                    } else if (customer.isBanned()) {
                        System.out.println("Customer is banned and cannot rent equipment.");
                        return;
                    } else {
                        break;
                    }
                }

                IEquipment equipment;
                while (true) {
                    System.out.println("Enter Equipment ID or 0 to cancel: ");
                    int equipmentId = userInput.nextInt();
                    userInput.nextLine(); // Consume newline
                    if (equipmentId == 0) {
                        System.out.println("Rental process cancelled.");
                        displayRentalMenu();
                        return;
                    }
                    equipment = equipmentManager.searchEquipment(equipmentId);
                    if (equipment == null) {
                        System.out.println("Equipment not found. Please enter a valid Equipment ID.");
                    } else if (!equipment.isAvailable()) {
                        System.out.println("Equipment is currently unavailable. Please select a different Equipment ID.");
                    } else {
                        break;
                    }
                }
                LocalDate RentalLocalDate;
                while (true) {
                    System.out.println("Enter Rental Date (YYYY-MM-DD) or 'q' to cancel: ");
                    String rentalDateInput = userInput.nextLine();
                    if (rentalDateInput.equalsIgnoreCase("q")) {
                        System.out.println("Rental process cancelled.");
                        displayRentalMenu();
                        return;
                    }
                    try {
                        RentalLocalDate = LocalDate.parse(rentalDateInput);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    }
                }
                
                LocalDate returnLocalDate;
                while (true) {
                    System.out.println("Enter Return Date (YYYY-MM-DD) or 'q' to cancel: ");
                    String returnDateInput = userInput.nextLine();
                    if (returnDateInput.equalsIgnoreCase("q")) {
                        System.out.println("Rental process cancelled.");
                        displayRentalMenu();
                        return;
                    }
                    try {
                        returnLocalDate = LocalDate.parse(returnDateInput);
                        if (returnLocalDate.isBefore(RentalLocalDate)) {
                            System.out.println("Return date cannot be before rental date. Please enter a valid return date.");
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                    }
                }
                int rentalId = rentalManager.createRentalId();
                Rental newRental = new Rental(rentalId, RentalLocalDate, customer.getId(), equipment.getId(), RentalLocalDate, returnLocalDate, rentalId, false);
                rentalManager.addRental(newRental);
                equipmentManager.updateEquipmentAvailability(equipment.getId(), false);
                System.out.println("Equipment rented successfully! Rental ID: " + rentalId);
                displayRentalMenu();
                break;
            case 2:
                System.out.println("Enter Rental ID to Return: ");
                int rentalId1 = userInput.nextInt();
                userInput.nextLine(); // Consume newline

                IRental rental = rentalManager.getRentalById(rentalId1);
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
                displayRentalMenu();
                break;
            case 3:
                System.out.println("All Rentals:");
                for (IRental r : rentalManager.getAllRentals()) {
                    displayRentalDetails(r.toString());
                }
                displayRentalMenu();
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

        int choice = userInput.nextInt();
        userInput.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter ID: ");
                int id = userInput.nextInt();
                userInput.nextLine(); // Consume newline

                System.out.print("Enter Name: ");
                String name = userInput.nextLine();
                ICategory newCategory = new Category(id, name);
                categoryManager.addCategory(newCategory);
                System.out.println("Category added successfully!");
                displayCategoryMenu();
                break;
            case 2:
                System.out.println("All Categories:");
                categoryManager.getAllCategories().forEach(category -> displayMessage(category.toString()));
                displayCategoryMenu();
                break;
            case 3:
                System.out.print("Enter Name to Search: ");
                String searchName = userInput.nextLine();
                ICategory foundCategory = categoryManager.searchCategoryByName(searchName);
                if (foundCategory != null) {
                    displayMessage(foundCategory.toString());
                } else {
                    System.out.println("Category not found.");
                }
                displayCategoryMenu();
                break;
            case 4:
                System.out.print("Enter Category ID to Update: ");
                int updateId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                ICategory existingCategory = categoryManager.getCategoryById(updateId);
                if (existingCategory == null) {
                    System.out.println("Category not found.");
                    displayCategoryMenu();
                    break;
                }
                System.out.print("Enter New Name: ");
                String newName = userInput.nextLine();
                existingCategory.setName(newName);
                categoryManager.updateCategory(existingCategory);
                System.out.println("Category updated successfully!");
                displayCategoryMenu();
                break;
            case 5:
                System.out.print("Enter Category ID to Remove: ");
                int removeId = userInput.nextInt();
                userInput.nextLine(); // Consume newline
                ICategory categoryToRemove = categoryManager.getCategoryById(removeId);
                if (categoryToRemove == null) {
                    System.out.println("Category not found.");
                    displayCategoryMenu();
                    break;
                }
                categoryManager.removeCategoryById(categoryToRemove);
                System.out.println("Category removed successfully!");
                displayCategoryMenu();
                break;
            case 6:
                displayMainMenu();
                break;
            default:
                break;
        }
    }

}
