package util;

import handlers.AnimalJsonHandler;
import handlers.EmployeeJsonHandler;
import handlers.AdoptionInheritJsonHandler;
import handlers.ReportJsonHandler;
import handlers.RehomeJsonHandler;

import models.Animal;
import models.AdoptionLogs;
import models.Employees;
import models.ReportLogs;
import models.RehomingRequest;

import java.util.List;


public class UtilWorker {

    public static void showWorkerMenu() {
        System.out.println("\n-------- WORKER MENU --------");
        System.out.println("[1] Manage Animal Records");
        System.out.println("[2] Handle Adoptions");
        System.out.println("[3] Respond to Reports");
        System.out.println("[4] Rehoming Requests");
        System.out.println("[5] Profile Customization");
        System.out.println("[6] Sign Out");
    }

    public static void animalRecordMenu() {
        System.out.println("\n-------- Manage Animal Records --------");
        System.out.println("[1] Add New Animal");
        System.out.println("[2] Update Animal Info");
        System.out.println("[3] Update Animal Status");
        System.out.println("[4] View All Animals");
        System.out.println("[5] Back to Worker Menu");
    }

    public static void showAdoptionMenu() {
        System.out.println("\n-------- Handle Adoptions --------");
        System.out.println("[1] View Adoption History");
        System.out.println("[2] Evaluate Applications");
        System.out.println("[3] Back to Worker Menu");
    }

    public static void showRehomingMenu() {
        System.out.println("\n-------- Rehoming Requests --------");
        System.out.println("[1] View Requests");
        System.out.println("[2] Review Request");
        System.out.println("[3] Back to Worker Menu");
    }

    public static void showReportMenu() {
        System.out.println("\n-------- Respond to Reports --------");
        System.out.println("[1] View Reports");
        System.out.println("[2] Update Report Status");
        System.out.println("[3] Set Report Priority");
        System.out.println("[4] Back to Worker Menu");
    }

    public static void showUpdateMenu() {
        System.out.println("\n-------- Animal Information Update --------");
        System.out.println("[1] Name");
        System.out.println("[2] Type");
        System.out.println("[3] Breed");
        System.out.println("[4] Sex");
        System.out.println("[5] Age");
        System.out.println("[6] Back to Worker Menu");
    }

    //methods here

    public static void addNewAnimal() {
        Utility.clearScreen();
        System.out.println("\n-------- Add New Animal --------");
        int id = new Animal().idGenerator();

        System.out.print("Enter Name: ");
        String name = Utility.inputHandleString();

        System.out.print("Enter Animal Type: ");
        String type = Utility.inputHandleString();

        System.out.print("Enter Breed: ");
        String breed = Utility.inputHandleString();

        System.out.print("Enter Sex (M/F): ");
        String sexInput = Utility.inputHandleString().toLowerCase();
        String sex;
        switch (sexInput) {
            case "m": 
                sex = "Male";
                break;
            case "f":
                sex = "Female";
                break;
            default:
                System.out.println("Invalid input. Status not changed.");
                return;
        }

        System.out.print("Enter Age (or leave blank): ");
        String ageInput = Utility.inputHandleString();
        String age = (ageInput.equals(null)) ? "Unknown" : ageInput;

        Animal animal = new Animal(id, name, type, breed, sex, age);
        AnimalJsonHandler.addAnimal(animal);
        System.out.println(type + " added successfully.");
    }

    public static void showAllAnimals() {
        Utility.clearScreen();
        List<Animal> animals = AnimalJsonHandler.loadAnimals();

        System.out.println("\nAnimals:\n");
        System.out.printf("+-----+--------+-----------+-----------------+--------+------+------------+%n");
        System.out.printf("| ID  | Name   | Type      | Breed           | Sex    | Age  | Status     |%n");
        System.out.printf("+-----+--------+-----------+-----------------+--------+------+------------+%n");

        for (Animal dog : animals) {
            System.out.printf("| %-3d | %-6s | %-9s | %-15s | %-6s | %-4s | %-10s |%n",
                    dog.getId(),
                    dog.getName(),
                    dog.getType(),
                    dog.getBreed(),
                    dog.getSex(),
                    dog.getAge(),
                    dog.getStatus());
        }

        System.out.printf("+-----+--------+-----------+-----------------+--------+------+------------+%n");
    }

    public static void updateAnimalInformation() {
        Utility.clearScreen();
        List<Animal> animals = AnimalJsonHandler.loadAnimals();
        System.out.println("\n-------- Update Animal Information --------");
        System.out.print("Do you want to view the animals(y/n): ");
        String response = Utility.inputHandleString().toLowerCase();

        if (response.equals("y")) {
            showAllAnimals();
        }

        Animal animal = selectAnimal(animals);

        String name = Utility.inputHandlerStr("Enter updated name: ");
        if (name != null && !name.trim().isEmpty()) {
            animal.setName(name.trim());
        }

        String type = Utility.inputHandlerStr("Enter updated animal type: ");
        if (type != null && !type.trim().isEmpty()) {
            animal.setType(type.trim());
        }

        String breed = Utility.inputHandlerStr("Enter updated breed: ");
        if (breed != null && !breed.trim().isEmpty()) {
            animal.setBreed(breed.trim());
        }

        String ageInput = Utility.inputHandlerStr("Enter updated age: ");
        if (ageInput != null && !ageInput.trim().isEmpty()) {
            animal.setAge(ageInput.trim());
        }

        System.out.println("Animal detail(s) updated successfully.");
        AnimalJsonHandler.saveAnimals(animals);
    }

    //this will handle the repeated inputs for animal update

    public static Animal selectAnimal(List<Animal> animals) {
        System.out.print("Enter the animal id: ");
        int id = Utility.inputHandlerInt();

        for (Animal animal : animals) {
            if (animal.getId() == id) {
                return animal;
            }
        }
        System.out.println("There is no animal with Id: " + id);
        return null;
    }

    public static void updateAnimalStatus() {
        Utility.clearScreen();
        List<Animal> animals = AnimalJsonHandler.loadAnimals();

        System.out.println("\n-------- Update Animal Status --------");

        System.out.printf("+-----+------------+%n");
        System.out.printf("| Id  | Status     |%n");
        System.out.printf("+-----+------------+%n");
        for (Animal animal : animals) {
            System.out.printf("| %-3d | %-10s |%n",
                animal.getId(),
                animal.getStatus()            
            );
        }
        System.out.printf("+-----+------------+%n");

        System.out.print("Enter the Id of Animal: ");
        int id = Utility.inputHandlerInt();

        boolean found = false;
        for (Animal animal : animals) {
            if (id == animal.getId()) {
                found = true;
                System.out.println("\n-------- Animal Status Choices --------");
                System.out.println("[1] Adoptable");
                System.out.println("[2] Quarantine");
                System.out.println("[3] Medical");

                String status;
                System.out.print("Enter the status of animal: ");
                int input = Utility.inputHandlerInt();

                switch (input) {
                    case 1:
                        status = "Adoptable";
                        break;
                    case 2:
                        status = "Quarantine";
                        break;
                    case 3:
                        status = "Medical";
                        break;
                    default:
                        System.out.println("You entered an invalid input.");
                        return;
                }

                animal.setStatus(status);
                AnimalJsonHandler.saveAnimals(animals);
                System.out.println("\n" + animal.getType() + " status updated successfully.");
            }
        }
        if (!found) {
            System.out.println("There is no animal with id: " + id);
        }
    }

    public static void viewAdoptionApplication() {
        Utility.clearScreen();
        List<AdoptionLogs> adoptions = AdoptionInheritJsonHandler.loadAdoptionLogs();

        System.out.println("\n-------- View Adoption Application --------");

        if (adoptions.isEmpty()) {
            System.out.println("There are no adoption applications.");
            return;
        }

        System.out.printf("+--------+------------+-----------+------------+%n");
        System.out.printf("| Id     | Username   | Animal ID | Status     |%n");
        System.out.printf("+--------+------------+-----------+------------+%n");
        for (AdoptionLogs adoption : adoptions) {
            if (adoption.getStatus().equals("Pending")) {
                System.out.printf("| %-6d | %-10s | %-9d | %-10s |%n",
                    adoption.getApplicationId(),
                    adoption.getUsername(),
                    adoption.getAnimalId(),
                    adoption.getStatus()
                );
            }
        }
        System.out.printf("+--------+------------+-----------+------------+%n");
    }

    public static void applicationDecision() {
        Utility.clearScreen();
        List<AdoptionLogs> applications = AdoptionInheritJsonHandler.loadAdoptionLogs();

        System.out.println("\n-------- Evaluation of Adoption Application --------");

        System.out.printf("+--------+------------------+%n");
        System.out.printf("| Id     | Decision         |%n");
        System.out.printf("+--------+------------------+%n");
        boolean contain = false;
        for (AdoptionLogs application : applications) {
            if (application.getStatus().equals("Pending")) {
                contain = true;
                System.out.printf("| %-6d | %-16s |%n",
                    application.getApplicationId(),
                    application.getStatus()
                );
            }
        }
        System.out.printf("+--------+------------------+%n");
        if (!contain) {
            System.out.println("There are no pending application as of the moment.");
            return;
        }
        
        int choice = Utility.getInput("Enter the Application Id you want to evaluate: ");
        boolean found = false;
        for (AdoptionLogs application : applications) {
            if (choice == application.getApplicationId()) {
                found = true;

                System.out.println("\n-------- Choices --------");
                System.out.println("[1] Approved");
                System.out.println("[2] Denied");

                String decision;
                switch (Utility.getInput("Enter you choice: ")) {
                    case 1:
                        decision = "Approved";
                        break;
                    case 2:
                        decision = "Denied";
                        break;
                    default:
                        System.out.println("You entered an invalid input.");
                        return;
                }

                application.setStatus(decision);
                AdoptionInheritJsonHandler.saveAdoption(applications);
                System.out.println("Evaluated Successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("There is no adoption application with this Id: " + choice);
        }    
    }

    public static void updateReportStatus() {
        Utility.clearScreen();
        List<ReportLogs> reports = ReportJsonHandler.loadUserReports();

        System.out.println("\n-------- Update Report Status --------");

        System.out.print("Do you want to view the report list (y/n): ");
        String answer = Utility.inputHandleString().toLowerCase();

        boolean contain = false;
        if (answer.equals("y")) {
            System.out.printf("+-------+-----------------+------------------+----------------------------------------+------------+-------------+----------------+------------+------------+%n");
            System.out.printf("| Id    | Username        | Type             | Description                            | Location   | Status      | Priority       | Date       | Time       |%n");
            System.out.printf("+-------+-----------------+------------------+----------------------------------------+------------+-------------+----------------+------------+------------+%n");
            for (ReportLogs report : reports) {
                if (report.getStatus().equals("In-Review")) {
                    contain = true;
                    System.out.printf("| %-5d | %-15s | %-16s | %-38s | %-10s | %-11s | %-14s | %-10s | %-10s |%n",
                    report.getReportId(),
                    report.getUsername(),
                    report.getType(),
                    report.getDescription(),
                    report.getLocation(),
                    report.getStatus(),
                    report.getPriority(),
                    report.getDate(),
                    report.getTime());
                }
            }
            System.out.printf("+-------+-----------------+------------------+----------------------------------------+------------+-------------+----------------+------------+------------+%n");
        }
        if (!contain) {
            System.out.println("There are no In-Review reports as of the moment.");
            return;
        }
        
        System.out.print("Enter the Id: ");
        int id = Utility.inputHandlerInt();

        boolean found = false;
        for (ReportLogs report : reports) {
            if (id == report.getReportId()) {
                found = true;
                System.out.println("\n-------- Choices --------");
                System.out.println("[1] Resolved");
                System.out.println("[2] Archived");

                String status;
                switch (Utility.getInput("Enter you choice: ")) {
                    case 1:
                        status = "Resolved";
                        break;
                    case 2:
                        status = "Archived";
                        break;
                    default:
                        System.out.println("You entered an invalid input.");
                        return;
                }

                report.setStatus(status);
                ReportJsonHandler.saveReportLogs(reports);
                System.out.println("Update Status Successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("There is no report with this Id: " + id);
        }
    }

    public static void updatePriority() {
        Utility.clearScreen();
        List<ReportLogs> reports = ReportJsonHandler.loadUserReports();

        System.out.println("\n-------- Update Priority --------");

        System.out.print("Do you want to view the report list (y/n): ");
        String answer = Utility.inputHandleString().toLowerCase();

        if (answer.equals("y")) {
            UtilAdmin.viewAllUserReports();
        }
        
        System.out.print("Enter the Id: ");
        int id = Utility.inputHandlerInt();

        boolean found = false;
        for (ReportLogs report : reports) {
            if (id == report.getReportId()) {
                found = true;
                System.out.println("\n-------- Choices --------");
                System.out.println("[1] Low");
                System.out.println("[2] Med");
                System.out.println("[3] High");
                System.out.println("[4] Urgent");

                String status;
                switch (Utility.getInput("Enter you choice: ")) {
                    case 1:
                        status = "Low";
                        break;
                    case 2:
                        status = "Med";
                        break;
                    case 3:
                        status = "High";
                        break;
                    case 4:
                        status = "Urgent";
                        break;
                    default:
                        System.out.println("You entered an invalid input.");
                        return;
                }

                report.setPriority(status);
                ReportJsonHandler.saveReportLogs(reports);
                System.out.println("Update Status Successfully.");
                break;
            }
        }
        if (!found) {
            System.out.println("There is no report with this Id: " + id);
        }
    }

    public static void viewPendingRequest() {
        Utility.clearScreen();
        List<RehomingRequest> requests = RehomeJsonHandler.loadRequests();

        System.out.println("\n-------- Pending Request --------");

        boolean contain = false;
        System.out.printf("+------+------------+--------+-----------+-------------+--------+------+-------------+%n");
        System.out.printf("| Id   |Username    | Name   | Type      | Breed       | Sex    | Age  | Status      |%n");
        System.out.printf("+------+------------+--------+-----------+-------------+--------+------+-------------+%n");
        for (RehomingRequest request : requests) {
            if (request.getStatus().equals("Pending")) {
                contain = true;
                System.out.printf("| %-4d | %-10s | %-6s | %-9s | %-11s | %-6s | %-4s | %-11s |%n",
                    request.getRehomingId(),
                    request.getUsername(),
                    request.getName(),
                    request.getAnimalType(),
                    request.getBreed(),
                    request.getSex(),
                    request.getAge(),
                    request.getStatus());
            }
        }
        System.out.printf("+------+------------+--------+-----------+-------------+--------+------+-------------+%n");
        if (!contain) {
            System.out.println("There are no Pending Request as of the moment.");
            return;
        }
    }

    public static void reviewRequest() {
        Utility.clearScreen();
        List<RehomingRequest> requests = RehomeJsonHandler.loadRequests();

        System.out.println("\n-------- Review Request --------");


        String choice = Utility.inputHandlerStr("Do you want to view the peding requests (y/n): ").toLowerCase();

        if (choice.equals("y")) {
            viewPendingRequest();
        }

        boolean found = false;
        for (RehomingRequest request : requests) {
            if (request.getStatus().equals("Pending")) {
                found = true;
                System.out.print("Enter the Id you want to modify: ");
                int id = Utility.inputHandlerInt();
                
                if (id == request.getRehomingId()) {
                    found = true;

                    System.out.println("\n-------- Choices --------");
                    System.out.println("[1] Approved");
                    System.out.println("[2] Denied");
                    System.out.print("Enter your choice: ");
                    int answer = Utility.inputHandlerInt();

                    String status;
                    switch (answer) {
                        case 1:
                            status = "Approved";
                            break;
                        case 2:
                            status = "Denied";
                            break;
                        default:
                            System.out.println("You entered an invalid input.");
                            return;
                    }

                    request.setStatus(status);
                    RehomeJsonHandler.saveRequests(requests);
                    System.out.println("Request status updated successfully.");
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("There is no review that have this Id.");
        }
    }

    public static void changePassword() {
        Utility.clearScreen();
        List<Employees> employees = EmployeeJsonHandler.loadEmployees();
        System.out.println("\n-------- Worker Password Customizer --------");

        int id = Utility.getInput("Enter the 4 digit worker ID: ");

        boolean found = false;
        for (Employees employee : employees) {
            if (id == employee.getApplicantId()) {
                found = true;
                String old = Utility.inputHandlerStr("Enter your old password: ");
                String newPass = Utility.inputHandlerStr("Enter the new password: ");
                if (!newPass.equals(old)) {
                    String confirm = Utility.inputHandlerStr("Please confirm your new password: ");
                    if (confirm.equals(newPass)) {
                        employee.setPassword(newPass);
                        EmployeeJsonHandler.saveEmployee(employees);
                        System.out.println("Password changed successfully.");
                        break;
                    }
                    else {
                        System.out.println("The password did not match.");
                    }    
                }
                else {
                    System.out.println("The password that you entered is already your old password.");
                }
            }
        }
        if (!found) {
            System.out.println("There is no employee that have this ID: " + id);
        }
    }

}
