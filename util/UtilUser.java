package util;

import models.User;
import models.Animal;
import models.AdoptionLogs;
import models.Report;
import models.RehomingRequest;
import models.Applicants;

import handlers.AdoptionInheritJsonHandler;
import handlers.AnimalJsonHandler;
import handlers.ApplicantJsonHandler;
import handlers.RehomeJsonHandler;
import handlers.UserFileReportHandler;

import java.util.List;

public class UtilUser {

    public static void userMenu(User user) {
        System.out.println("\nWelcome, " + user.getFullName() + "!");
        System.out.println("[1] Adoption");
        System.out.println("[2] Report");
        System.out.println("[3] Rehome My Pet");
        System.out.println("[4] Log Out");
    }

    public static void adoptionChoices() {
        System.out.println("\n== Adoption ==");
        System.out.println("[1] Apply for Adoption");
        System.out.println("[2] Adoption Application Status");
        System.out.println("[3] Back to Main Menu");
    }

    public static void reportChoices() {
        System.out.println("\n== Reports ==");
        System.out.println("[1] File a Report");
        System.out.println("[2] Track My Report");
        System.out.println("[3] Back to Main Menu");
    }

    public static void rehomeChoices() {
        System.out.println("\nRehome My Pet");
        System.out.println("[1] Submit Pet Details");
        System.out.println("[2] My Listing Status");
        System.out.println("[3] Back to Main Menu");
    }

    private static String truncate(String text, int maxLength) {
        return text.length() > maxLength ? text.substring(0, maxLength - 3) + "..." : text;
    }

    // Adoption Actions 

    public static void showAdoptableAnimals(List<Animal> animals) {
        Utility.clearScreen();
        System.out.println("\nAdoptable Animals:\n");
        System.out.printf("+------+--------+-----------+--------------+--------+------+------------+%n");
        System.out.printf("| ID   | Name   | Type      | Breed        | Sex    | Age  | Status     |%n");
        System.out.printf("+------+--------+-----------+--------------+--------+------+------------+%n");

        boolean found = false;

        for (Animal animal : animals) {
            if (animal.getStatus().equalsIgnoreCase("Adoptable")) {
                System.out.printf("| %-4d | %-6s | %-9s | %-12s | %-6s | %-4s | %-10s |%n",
                        animal.getId(),
                        animal.getName(),
                        animal.getType(),
                        animal.getBreed(),
                        animal.getSex(),
                        animal.getAge(),
                        animal.getStatus());
                found = true;
            }
        }

        if (!found) {
            System.out.println("| No adoptable animals available.                                           |");
        }

        System.out.printf("+------+--------+-----------+--------------+--------+------+------------+%n");

        System.out.print("\nWhat animal are you looking for? (Enter animal type or press Enter to skip): ");
        String animalType = Utility.inputHandleString().trim().toLowerCase();

        if (!animalType.isEmpty()) {
            System.out.println("\nFiltered by Type: " + animalType + "\n");
            System.out.printf("+------+--------+-----------+--------------+--------+------+------------+%n");
            System.out.printf("| ID   | Name   | Type      | Breed        | Sex    | Age  | Status     |%n");
            System.out.printf("+------+--------+-----------+--------------+--------+------+------------+%n");

            boolean typeFound = false;

            for (Animal animal : animals) {
                if (animal.getStatus().equalsIgnoreCase("Adoptable")
                        && animal.getType().toLowerCase().contains(animalType)) {
                    System.out.printf("| %-4d | %-6s | %-9s | %-12s | %-6s | %-4s | %-10s |%n",
                            animal.getId(),
                            animal.getName(),
                            animal.getType(),
                            animal.getBreed(),
                            animal.getSex(),
                            animal.getAge(),
                            animal.getStatus());
                    typeFound = true;
                }
            }

            if (!typeFound) {
                System.out.println("| No animals found of that type.                                           |");
            }

            System.out.printf("+------+--------+-----------+--------------+--------+------+------------+%n");
        }
    }

    public static void applyForAdoption(User user, List<Animal> animals) {
        Utility.clearScreen();
        List<Animal> allAnimals = AnimalJsonHandler.loadAnimals();
        System.out.println("\nAdoptable Animals:\n");
        System.out.printf("+------+--------+-----------+--------------+--------+------+------------+%n");
        System.out.printf("| ID   | Name   | Type      | Breed        | Sex    | Age  | Status     |%n");
        System.out.printf("+------+--------+-----------+--------------+--------+------+------------+%n");

        boolean found = false;

        for (Animal animal : allAnimals) {
            if (animal.getStatus().equalsIgnoreCase("Adoptable")) {
                System.out.printf("| %-4d | %-6s | %-9s | %-12s | %-6s | %-4s | %-10s |%n",
                        animal.getId(),
                        animal.getName(),
                        animal.getType(),
                        animal.getBreed(),
                        animal.getSex(),
                        animal.getAge(),
                        animal.getStatus());
                found = true;
            }
        }

        if (!found) {
            System.out.println("| No adoptable animals available.                                           |");
        }

        System.out.printf("+------+--------+-----------+--------------+--------+------+------------+%n");

        try {
            System.out.print("Enter Animal ID to apply for: ");
            int id = Integer.parseInt(Utility.inputHandleString());

            AdoptionLogs log = new AdoptionLogs();
            int idGenerate = log.idGenerator();

            AdoptionLogs app = new AdoptionLogs(idGenerate, user.getUsername(), id, "Pending");
            AdoptionInheritJsonHandler.addAdoption(app);
            System.out.println("Adoption application submitted.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Animal ID. Please enter a number.");
        }
    }

    public static void viewMyAdoptionStatus(User user) {
        Utility.clearScreen();
        List<AdoptionLogs> myApps = AdoptionInheritJsonHandler.getAdoptionLogs(user.getUsername());
        System.out.println("\nMy Adoption Applications:\n");

        if (myApps.isEmpty()) {
            System.out.println("No adoption applications found.");
            return;
        }

        System.out.printf("+------------+-----------+------------+%n");
        System.out.printf("| Username   | Animal ID | Status     |%n");
        System.out.printf("+------------+-----------+------------+%n");
        for (AdoptionLogs app : myApps) {
            System.out.printf("| %-10s | %-9d | %-10s |%n",
                    app.getUsername(),
                    app.getAnimalId(),
                    app.getStatus());
        }
        System.out.printf("+------------+-----------+------------+%n");
    }

    // Reports Actions 

    public static void fileReport(User user) {
        Utility.clearScreen();
        System.out.println("\n----------- File Report -----------");
        System.out.print("Type of Report: ");
        String type = Utility.inputHandleString();
        System.out.print("Description: ");
        String desc = Utility.inputHandleString();
        System.out.print("Location: ");
        String loc = Utility.inputHandleString();

        if (type.isBlank() || desc.isBlank() || loc.isBlank()) {
            System.out.println("All fields are required.");
            return;
        }

        Report report = new Report(user.getUsername(), type, desc, loc);
        UserFileReportHandler.addReport(report);
        System.out.println("Report submitted.");
    }

    public static void viewMyReports(User user) {
        Utility.clearScreen();
        List<Report> myReports = UserFileReportHandler.getUserReports(user.getUsername());
        System.out.println("\nMy Reports:\n");

        if (myReports.isEmpty()) {
            System.out.println("No reports found.");
            return;
        }

        System.out.printf("+--------------+----------------+------------------------------------------+-------------------------------+-------------+%n");
        System.out.printf("| %-12s | %-14s | %-40s | %-29s | %-11s |%n",
                "Username", "Type", "Description", "Location", "Status");
        System.out.printf("+--------------+----------------+------------------------------------------+-------------------------------+-------------+%n");

        for (Report r : myReports) {
            System.out.printf("| %-12s | %-14s | %-40s | %-29s | %-11s |%n",
                    truncate(r.getUsername(), 12),
                    truncate(r.getType(), 14),
                    truncate(r.getDescription(), 40),
                    truncate(r.getLocation(), 29),
                    truncate(r.getStatus(), 11));
        }

        System.out.printf("+--------------+----------------+------------------------------------------+-------------------------------+-------------+%n");
    }

    // Rehome Actions 

    public static void submitRehomeRequest(User user) {
        Utility.clearScreen();
        System.out.println("\n----------- Rehoming Request -----------");
        System.out.print("Pet Name: ");
        String name = Utility.inputHandleString();
        System.out.print("Animal Type: ");
        String type = Utility.inputHandleString();
        System.out.print("Breed: ");
        String breed = Utility.inputHandleString();
        System.out.print("Sex (M/F): ");
        String sexChoice = Utility.inputHandleString().toLowerCase();
        String sex = "";
        switch (sexChoice) {
            case "m":
                sex = "Male";
                break;
            case "f":
                sex = "Female";
                break;
            default:
                System.out.println("You entered an invalid input.");
        }
        System.out.print("Age (optional): ");
        String age = Utility.inputHandleString();
        System.out.print("Reason for Rehoming: ");
        String reason = Utility.inputHandleString();

        if (name.isBlank() || type.isBlank() || breed.isBlank() || sex.isBlank() || reason.isBlank()) {
            System.out.println("Please fill in all required fields.");
            return;
        }

        RehomingRequest request = new RehomingRequest(user.getUsername(), name, type, breed, sex, age, reason);
        RehomeJsonHandler.addRequest(request);
        System.out.println("Rehome request submitted.");
    }

    public static void viewMyListings(User user) {
        Utility.clearScreen();
        List<RehomingRequest> myListings = RehomeJsonHandler.getUserRequests(user.getUsername());
        System.out.println("\nMy Rehome Listings:\n");

        if (myListings.isEmpty()) {
            System.out.println("No rehoming listings found.");
            return;
        }

        System.out.printf("+------------+--------+-----------+-------------+--------+------+----------------------+-------------+%n");
        System.out.printf("| Username   | Name   | Type      | Breed       | Sex    | Age  | Reason               | Status      |%n");
        System.out.printf("+------------+--------+-----------+-------------+--------+------+----------------------+-------------+%n");

        for (RehomingRequest req : myListings) {
            System.out.printf("| %-10s | %-6s | %-9s | %-11s | %-6s | %-4s | %-20s | %-11s |%n",
                    req.getUsername(),
                    req.getName(),
                    req.getAnimalType(),
                    req.getBreed(),
                    req.getSex(),
                    req.getAge(),
                    truncate(req.getReason(), 20),
                    req.getStatus());
        }

        System.out.printf("+------------+--------+-----------+-------------+--------+------+----------------------+-------------+%n");
    }

    // Worker Application 

    public static void applyAsWorker() {
        Utility.clearScreen();
        System.out.println("\nApply as Worker");

        System.out.print("Enter First Name: ");
        String firstName = Utility.inputHandleString();

        System.out.print("Enter Last Name: ");
        String lastName = Utility.inputHandleString();

        System.out.print("Enter Age: ");
        int age = Utility.inputHandlerInt();

        System.out.print("Enter Address: ");
        String address = Utility.inputHandleString();

        System.out.print("Enter Contact Detail: ");
        String contact = Utility.inputHandleString();

        System.out.print("How many related skill(s) do you have (maximum of 3)? ");
        int count = Utility.inputHandlerInt();

        if (count < 0) {
            System.out.println("Invalid skills count.");
            return;
        }
        if (count > 3) {
            System.out.println("You exceed the limit.");
            return;
        }

        String[] skills = new String[count];
        for (int i = 0; i < count; i++) {
            System.out.print("Enter your skill " + (i + 1) + ": ");
            skills[i] = Utility.inputHandleString();
        }

        Applicants applicant = new Applicants(firstName, lastName, age, address, contact, skills);
        ApplicantJsonHandler.addApplicants(applicant);
        System.out.println("Your Application is Submitted Successfully.");
        System.out.println("Please Wait for the Updates.");
    }
}
