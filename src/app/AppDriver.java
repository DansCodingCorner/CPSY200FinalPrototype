package app;

import Presentation.IUIManager;
import Presentation.UImanager;


public class AppDriver {
    public static void main(String[] args) {
        System.out.println("Welcome to the Equipment Rental System!");
        IUIManager iManager = new UImanager();
        iManager.displayMainMenu();
    }
}
