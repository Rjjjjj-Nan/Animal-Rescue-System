package menus;

import util.Utility;
import util.UtilUser;

import models.User;
import models.Animal;

import java.util.List;

public class MainUser {

    public static void show(User user, List<Animal> animals) {
        Utility.clearScreen();
        Utility.loadingScreen();
        Utility.clearScreen();
        
        while (true) {
            UtilUser.userMenu(user);

            int choice = 0;
            int chance = 3;

            while (chance > 0) {
                choice = Utility.getInput("Enter your choice: ");
                if (choice >= 1 && choice <= 4) 
                    break;
                chance--;
                System.out.println("\nYou entered an invalid input. You have " + chance + " chance(s) left.");
            }

            switch (choice) {
                case 1 -> adoptionSubmenu(user, animals);
                case 2 -> reportSubmenu(user);
                case 3 -> rehomeSubmenu(user);
                case 4 -> {
                    Utility.clearScreen();
                    Utility.signingOut();
                    return;
                }
                default -> System.out.println("Returning to User Menu...");
            }
        }
    }

    private static void adoptionSubmenu(User user, List<Animal> animals) {
        UtilUser.showAdoptableAnimals(animals);
        
        while (true) {
            UtilUser.adoptionChoices();
            int choice = Utility.getInput("Enter your choice: ");

            switch (choice) {
                case 1 -> UtilUser.applyForAdoption(user, animals);
                case 2 -> UtilUser.viewMyAdoptionStatus(user);
                case 3 -> {
                    System.out.println("\nReturning to User Menu...");
                    return;
                }
                default -> {
                    System.out.println("Invalid input! Returning to User Menu...");
                    return;
                }
            }
        }
    }

    private static void reportSubmenu(User user) {
        while (true) {
            UtilUser.reportChoices();
            int choice = Utility.getInput("Enter your choice: ");

            switch (choice) {
                case 1 -> UtilUser.fileReport(user);
                case 2 -> UtilUser.viewMyReports(user);
                case 3 -> {
                    System.out.println("\nReturning to User Menu...");
                    return;
                }
                default -> {
                    System.out.println("Invalid input! Returning to User Menu...");
                    return;
                }
            }
        }
    }

    private static void rehomeSubmenu(User user) {
        while (true) {
            UtilUser.rehomeChoices();
            int choice = Utility.getInput("Enter your choice: ");

            switch (choice) {
                case 1 -> UtilUser.submitRehomeRequest(user);
                case 2 -> UtilUser.viewMyListings(user);
                case 3 -> {
                    System.out.println("\nReturning to User Menu...");
                    return;
                }
                default -> {
                    System.out.println("Invalid input! Returning to User Menu...");
                    return;
                }
            }
        }
    }
}
