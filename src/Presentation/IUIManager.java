package Presentation;

public interface IUIManager {
    void displayMainMenu();
    void displayCustomerMenu();
    void displayEquipmentMenu();
    void displayRentalMenu();
    void displayCategoryMenu();
    void displayMessage(String message);
    void displayError(String errorMessage);
    void displayCustomerDetails(String customerDetails);
    void displayEquipmentDetails(String equipmentDetails);
    void displayRentalDetails(String rentalDetails);


}
