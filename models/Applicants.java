package models;

import java.util.Arrays;
import java.util.List;

import handlers.AdoptionInheritJsonHandler;
import handlers.AnimalJsonHandler;
import handlers.RehomeJsonHandler;
import handlers.ReportJsonHandler;
import util.Utility;
import util.UtilWorker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Applicants extends Function {
    
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String contact;
    private String[] skills;
    private String date;
    private String status; //for evaluation, for interview, Hired

    public Applicants(String firstName, String lastName, int age, String address, String contact, String[] skills) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.contact = contact;
        this.skills = skills;
        this.status = "For Evaluation";

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = now.format(formatter);
    }

    public Applicants() {
        //empty
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getAddress() {
        return address;
    }
    public String getContact() {
        return contact;
    }
    public String[] getSkills() {
        return skills;
    }
    public String getDate() {
        return date;
    }
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "First Name: " + firstName + " | Last Name: " + lastName + " | Age: " + age + " | Address: " + address
         + " | Contact: " + contact + " | Skills: " + Arrays.toString(skills) + "Status: " + status + " | Date: " + date;
    }

    @Override
    public void addNewAnimal() {
        Utility.clearScreen();
        System.out.println("\n-------- Add New Animal --------");
        int id = new Animal().idGenerator();

        System.out.print("Enter Name (.. to go back): ");
        String name = Utility.inputHandleString();

        if (name.equals("..")) {
            return;
        }

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

    @Override
    public void updateAnimalInformation() {
        Utility.clearScreen();
        List<Animal> animals = AnimalJsonHandler.loadAnimals();
        System.out.println("\n-------- Update Animal Information --------");
        System.out.print("Do you want to view the animals(y/n or .. to go back): ");
        String response = Utility.inputHandleString().toLowerCase();

        if (response.equals("..")) {
            return;
        }

        if (response.equals("y")) {
            UtilWorker.showAllAnimals();
        }

        Animal animal = UtilWorker.selectAnimal(animals);

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

    @Override
    public void updateAnimalStatus() {
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

        System.out.print("Enter the Id of Animal (0 to go back): ");
        int id = Utility.inputHandlerInt();

        if (id == 0) {
            return;
        }

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

    @Override
    public void updateReportStatus() {
        Utility.clearScreen();
        List<ReportLogs> reports = ReportJsonHandler.loadUserReports();

        System.out.println("\n-------- Update Report Status --------");

        System.out.print("Do you want to view the report list (y/n or .. to go back): ");
        String answer = Utility.inputHandleString().toLowerCase();

        if (answer.equals("..")) {
            return;
        }

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

    @Override
    public void applicationDecision() {
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
        
        int choice = Utility.getInput("Enter the Application Id you want to evaluate (0 to go back): ");
        if (choice == 0) {
            return;
        }
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

    @Override
    public void reviewRequest() {
        Utility.clearScreen();
        List<RehomingRequest> requests = RehomeJsonHandler.loadRequests();

        System.out.println("\n-------- Review Request --------");


        String choice = Utility.inputHandlerStr("Do you want to view the peding requests (y/n or .. to go back): ").toLowerCase();

        if (choice.equals("..")) {
            return;
        }

        if (choice.equals("y")) {
            UtilWorker.viewPendingRequest();
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

}
