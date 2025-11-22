import handlers.AdminJsonHandler;
import handlers.AnimalJsonHandler;
import handlers.EmployeeJsonHandler;
import handlers.UserJsonHandler;

import menus.MainAdmin;
import menus.MainWorker;
import menus.MainUser;

import models.Admin;
import models.Animal;
import models.Employees;
import models.User;

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
                case 1 -> login(animals);
                case 2 -> register();
                case 3 -> UtilUser.applyAsWorker();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void register() {
        Utility.clearScreen();
        System.out.println("\nRegister New User");

        String name;
        do {
            System.out.print("Full Name: ");
            name = Utility.inputHandleString().trim();
            if (!name.matches("[A-Za-z ]+")) {
                System.out.println("Invalid name. Use letters and spaces only.");
            }
        } while (!name.matches("[A-Za-z ]+"));

        int age;
        while (true) {
            System.out.print("Age: ");
            String ageInput = Utility.inputHandleString();
            try {
                age = Integer.parseInt(ageInput);
                if (age <= 0) {
                    System.out.println("Age must be a positive number.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a number.");
            }
        }

        String address;
        do {
            System.out.print("Address: ");
            address = Utility.inputHandleString().trim();
            if (address.isBlank()) {
                System.out.println("Address cannot be empty.");
            }
        } while (address.isBlank());

        String contact;
        do {
            System.out.print("Contact No (7–11 digits): ");
            contact = Utility.inputHandleString().trim();
            if (!contact.matches("\\d{7,11}")) {
                System.out.println("Invalid contact number. Use 7 to 11 digits.");
            }
        } while (!contact.matches("\\d{7,11}"));

        String username;
        do {
            System.out.print("Username: ");
            username = Utility.inputHandleString().trim();
            if (username.isBlank()) {
                System.out.println("Username cannot be empty.");
            }
        } while (username.isBlank());

        String password;
        do {
            System.out.print("Password: ");
            password = Utility.inputHandleString();
            if (password.isBlank()) {
                System.out.println("Password cannot be empty.");
            }
        } while (password.isBlank());

        User newUser = new User(name, age, address, contact, username, password);
        UserJsonHandler.addUser(newUser);

        System.out.println("Registration successful. You can now log in!");
    }

    private static void login(List<Animal> animals) {
        Utility.clearScreen();
        System.out.println("\nLogin Options");

        System.out.println("[1] User");
        System.out.println("[2] Worker");
        System.out.println("[3] Admin");

        switch (Utility.getInput("Enter your choice: ")) {
            case 1:
                Utility.clearScreen();
                System.out.println("\nLogin as User");
                String username;
                do {
                    System.out.print("Username: ");
                    username = Utility.inputHandleString();
                    if (username.isBlank()) {
                        System.out.println("Username cannot be empty.");
                    }
                } while (username.isBlank());

                String password;
                do {
                    System.out.print("Password: ");
                    password = Utility.inputHandleString();
                    if (password.isBlank()) {
                        System.out.println("Password cannot be empty.");
                    }
                } while (password.isBlank());

                User user = UserJsonHandler.login(username, password);

                if (user != null) {
                    System.out.println("Login successful. Welcome, " + user.getFullName() + "!");
                    MainUser.show(user, animals);
                } else {
                    System.out.println("Invalid credentials. Try again.");
                }
                break;
            case 2:
                Utility.clearScreen();
                System.out.println("\nLogin as Worker");
                List<Employees> employees = EmployeeJsonHandler.loadEmployees();
                
                System.out.print("Enter your applicant ID: ");
                int id = Utility.inputHandlerInt();

                System.out.print("Enter your password: ");
                String workerPassword = Utility.inputHandleString();

                boolean foundWorker = false;
                for (Employees employee : employees) {
                    if ((id == employee.getApplicantId()) && (workerPassword.equals(employee.getPassword()))) {
                        foundWorker = true;
                        MainWorker.workerMain();
                        break;
                    }
                }
                if (!foundWorker) {
                    System.out.println("The employee id and password you entered is invalid.");
                }
                break;
            case 3:
                Utility.clearScreen();
                System.out.println("\nLogin as Admin");
                List<Admin> admins = AdminJsonHandler.loadAdmins();

                System.out.print("Enter username: ");
                String adminUsername = Utility.inputHandleString();

                System.out.print("Enter password: ");
                String adminPassword = Utility.inputHandleString();

                boolean foundAdmin = false;
                for (Admin admin : admins) {
                    if ((adminUsername.equals(admin.getUsername())) && (adminPassword.equals(admin.getPassword()))) {
                        foundAdmin = true;
                        MainAdmin.mainAdmin();
                        break;
                    }
                }
                if (!foundAdmin) {
                    System.out.println("The username and password you entered is invalid.");
                }
                break;
            default:
                System.out.println("Error. Your entered an invalid input.");
                break;
        }
    }
}
