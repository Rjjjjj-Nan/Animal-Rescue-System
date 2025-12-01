import handlers.AnimalJsonHandler;

import models.Animal;

import util.Utility;
import util.UtilUser;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Utility.clearScreen();
        Utility.loadingScreen();
        List<Animal> animals = AnimalJsonHandler.loadAnimals();


        while (true) {
            Utility.clearScreen();
            System.out.println("\n=== Animal Rescue & Adoption System ===");
            System.out.println("[1] Login");
            System.out.println("[2] Register as User");
            System.out.println("[3] Apply as Worker");
            System.out.println("[4] Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(Utility.inputHandleString());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> UtilUser.login(animals);
                case 2 -> UtilUser.register();
                case 3 -> UtilUser.applyAsWorker();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
