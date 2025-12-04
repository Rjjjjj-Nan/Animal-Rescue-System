package util;

import models.Admin;
import models.AdoptionLogs;
import models.Animal;
import models.Applicants;
import models.Employees;
import models.RehomingRequest;
import models.ReportLogs;

import handlers.AdminJsonHandler;
import handlers.AdoptionInheritJsonHandler;
import handlers.AnimalJsonHandler;
import handlers.ApplicantJsonHandler;
import handlers.EmployeeJsonHandler;
import handlers.RehomeJsonHandler;
import handlers.ReportJsonHandler;

import java.util.List;
import java.util.Arrays;
import java.util.Iterator;

public class UtilAdmin {

    public static void adminMenu() {
        System.out.println("\n-------- Admin Menu --------");
        System.out.println("[1] Transactions");
        System.out.println("[2] Applicants");
        System.out.println("[3] Manage Records");
        System.out.println("[4] Account Customization");
        System.out.println("[5] Logout");
    }

    public static void accountCustomization() {
        System.out.println("\n-------- Account Customization --------");
        System.out.println("[1] Create Another Profile");
        System.out.println("[2] Change Password");
        System.out.println("[3] Change Username");
        System.out.println("[4] Change Worker Password");
        System.out.println("[5] Admin Menu");
    }

    public static void transactChoices() {
        System.out.println("\n-------- Transactions Menu --------");
        System.out.println("[1] Adoption Logs");
        System.out.println("[2] Rehoming Request Logs");
        System.out.println("[3] User Report Logs");
        System.out.println("[4] Admin Menu");
    }

    public static void applicantChoices() {
        System.out.println("\n-------- Applicants Menu --------");
        System.out.println("[1] View Applicants");
        System.out.println("[2] Hire Applicants");
        System.out.println("[3] Admin Menu");
    }

    public static void recordChoices() {
        System.out.println("\n-------- Manage Record Menu --------");
        System.out.println("[1] Worker");
        System.out.println("[2] Adopter");
        System.out.println("[3] Animal");
        System.out.println("[4] Admin Menu");
    }

    public static void manageRecordsChoices() {
        System.out.println("\n-------- Processes --------");
        System.out.println("[1] Update Employee Operational Status");
        System.out.println("[2] Delete Record");
        System.out.println("[3] Update Report Status");
        System.out.println("[4] Evaluation Adoption Applications");
        System.out.println("[5] Evaluation Rehoming Requests");
        System.out.println("[6] Manage Animals");
        System.out.println("[7] Admin Menu");
    }

    public static void deleteType() {
        System.out.println("\n-------- Delete Type Choices --------");
        System.out.println("[1] Delete Worker");
        System.out.println("[2] Delete Adopter");
        System.out.println("[3] Delete Animal");
        System.out.println("[4] Delete Rehoming");
        System.out.println("[5] Delete Report");
        System.out.println("[6] Process Choices");
    }

    public static void updateAnimalInformation() {
        System.out.println("\n-------- Update Animal Information --------");
        System.out.println("[1] Add New Animal");
        System.out.println("[2] Update Animal Information");
        System.out.println("[3] Update Animal Status");
        System.out.println("[4] Admin Menu");
    }

    //continue working here for the methods

    public static void viewAllAdoptionLogs(String prompt) {
        Utility.clearScreen();
        List<AdoptionLogs> adoptionlogs = AdoptionInheritJsonHandler.getAdoptionLogs();
        System.out.println("\n----------------- " + prompt + " -----------------");

        if (adoptionlogs.isEmpty()) {
            System.out.println("There are no logs at the moment.");
            return;
        }

        System.out.printf("+-----+------------+-----------+------------+----------------------+%n");
        System.out.printf("| ID  | Username   | Animal ID | Status     | Date                 |%n");
        System.out.printf("+-----+------------+-----------+------------+----------------------+%n");
        for (AdoptionLogs logs : adoptionlogs) {
            System.out.printf("| %-3d | %-10s | %-9d | %-10s | %-20s |%n",
                logs.getApplicationId(),
                logs.getUsername(),
                logs.getAnimalId(),
                logs.getStatus(),
                logs.getDate());
        }

        System.out.printf("+-----+------------+-----------+------------+----------------------+%n");
    }

    public static void viewAllRehomeRequest() {
        Utility.clearScreen();
        List<RehomingRequest> requests = RehomeJsonHandler.loadRequests();

        System.out.println("\n-------- Rehoming Request Logs --------");

        boolean contain = false;
        System.out.printf("+------+------------+--------+-----------+-------------+--------+------+-------------+%n");
        System.out.printf("| Id   |Username    | Name   | Type      | Breed       | Sex    | Age  | Status      |%n");
        System.out.printf("+------+------------+--------+-----------+-------------+--------+------+-------------+%n");
        for (RehomingRequest request : requests) {
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
        System.out.printf("+------+------------+--------+-----------+-------------+--------+------+-------------+%n");
        if (!contain) {
            System.out.println("There are no Pending Request as of the moment.");
            return;
        }
    }

    public static void viewAllUserReports() {
        Utility.clearScreen();
        List<ReportLogs> reportlogs = ReportJsonHandler.getReportLogs();
        System.out.println("\n----------------- User Report Logs -----------------");

        if (reportlogs.isEmpty()) {
            System.out.println("There are no logs at the moment.");
            return;
        }

        System.out.printf("+-------+-----------------+------------------+------------------------------------------------------+------------------------+-------------+----------------+------------+------------+%n");
        System.out.printf("| Id    | Username        | Type             | Description                                          | Location               | Status      | Priority       | Date       | Time       |%n");
        System.out.printf("+-------+-----------------+------------------+------------------------------------------------------+------------------------+-------------+----------------+------------+------------+%n");
        for (ReportLogs logs : reportlogs) {
            System.out.printf("| %-5d | %-15s | %-16s | %-52s | %-22s | %-11s | %-14s | %-10s | %-10s |%n",
            logs.getReportId(),
            logs.getUsername(),
            logs.getType(),
            logs.getDescription(),
            logs.getLocation(),
            logs.getStatus(),
            logs.getPriority(),
            logs.getDate(),
            logs.getTime());
        }
        System.out.printf("+-------+-----------------+------------------+------------------------------------------------------+------------------------+-------------+----------------+------------+------------+%n");

    }

    public static void viewAllApplicants() {
        Utility.clearScreen();
        List<Applicants> applicant = ApplicantJsonHandler.loadApplicants();
        System.out.println("\n----------------- View Applicants -----------------");

        if (applicant.isEmpty()) {
            System.out.println("There are no applicants right now...");
            return;
        }

        System.out.printf("+-----------------+------------+-----+-----------------------------+--------------+------------------------------+---------------+-----------------+%n");
        System.out.printf("| First Name      | Last Name  | Age | Address                     | Contact      | Skills                       | Date Applied  | Status          |%n");
        System.out.printf("+-----------------+------------+-----+-----------------------------+--------------+------------------------------+---------------+-----------------+%n");
        for (Applicants apply : applicant) {
            System.out.printf("| %-15s | %-10s | %-3d | %-27s | %-12s | %-28s | %-13s | %-15s |%n",
            apply.getFirstName(),
            apply.getLastName(),
            apply.getAge(),
            apply.getAddress(),
            apply.getContact(),
            Arrays.toString(apply.getSkills()),
            apply.getDate(),
            apply.getStatus());
        }
        System.out.printf("+-----------------+------------+-----+-----------------------------+--------------+------------------------------+---------------+-----------------+%n");
    }

    public static void hireApplicant() {
        Utility.clearScreen();
        List<Applicants> applicant = ApplicantJsonHandler.loadApplicants();
        System.out.println("\n----------------- Hire Applicant -----------------");

        System.out.print("Do you want to see the list of applicants (y/n or .. to go back): ");
        String answer = Utility.inputHandleString().toLowerCase();

        if (answer.equals("..")) {
            return;
        }

        if (answer.equals("y")) {
            viewAllApplicants();
        }
        System.out.print("\nDo you want to hire an employee (y/n)? ");
        String response = Utility.inputHandleString().toLowerCase();

        if (response.equals("y")) {
            System.out.print("\nEnter the full name of the employee you want to hire: ");
            String name = Utility.inputHandleString().trim();
            String[] parts = name.split(" ", 2);
            String first = parts[0];
            String last = (parts.length > 1) ? parts[1] : "";

            boolean found = false;

            for (Applicants apply : applicant) {
                if (apply.getFirstName().equalsIgnoreCase(first) && apply.getLastName().equalsIgnoreCase(last)) {
                    found = true;
                        
                    System.out.print("\nHiring: " + apply.getFirstName() + " " + apply.getLastName() + "\n");
                    int Id = Utility.randomIdGenerator();

                    System.out.print("Enter the start date of the employee (yyyy-mm-dd): ");
                    String start = Utility.inputHandleString();

                    Employees employee = new Employees(
                            apply.getFirstName(),
                            apply.getLastName(),
                            apply.getAge(),
                            apply.getAddress(),
                            apply.getContact(),
                            apply.getSkills(),
                            Id, 
                            apply.getFirstName(),
                            start
                    );

                    apply.setStatus("Hired!");
                    ApplicantJsonHandler.saveApplicants(applicant);
                    EmployeeJsonHandler.addEmployee(employee);

                    System.out.printf("+---------------+-------------+--------------------+------------+--------------+----------------------+%n");
                    System.out.printf("| Name          | Employee ID | Status             | Username   | Password     | Start Date           |%n");
                    System.out.printf("+---------------+-------------+--------------------+------------+--------------+----------------------+%n");
                    System.out.printf("| %-13s | %-11s | %-18s | %-10s | %-12s | %-20s |%n",
                            apply.getFirstName() + " " + apply.getLastName(),
                            employee.getApplicantId(),
                            apply.getStatus(),
                            apply.getFirstName(),
                            employee.getPassword(),
                            employee.getStartDate()
                    );
                    System.out.printf("+---------------+-------------+--------------------+------------+--------------+----------------------+%n");
                    break;
                }
            }
            if (!found) {
                System.out.println("There is no applicant named [" + name + "].");
            }
        }
        else if (response.equals("n")) {
            System.out.println("Returning to Applicant Menu...");
            return;
        } 
        else {
            System.out.println("It appears that you entered an invalid input.");
            System.out.println("Returning to Applicant Menu...");
        }
    }

    public static void viewEmployees() {
        Utility.clearScreen();
        List<Employees> employees = EmployeeJsonHandler.loadEmployees();
        System.out.println("\n----------------- Employees -----------------");

        if (employees.isEmpty()) {
            System.out.println("There are no employees at the moment.");
            return;
        }

        System.out.printf("+--------+-----------------+------------+-----+------------+--------------+---------------+-----------------+%n");
        System.out.printf("| Id     | First Name      | Last Name  | Age | Address    | Contact      | Date Started  | Status          |%n");
        System.out.printf("+--------+-----------------+------------+-----+------------+--------------+---------------+-----------------+%n");
        for (Employees employee : employees) {
            System.out.printf("| %-6d | %-15s | %-10s | %-3d | %-10s | %-12s | %-13s | %-15s |%n",
            employee.getApplicantId(),
            employee.getFirstName(),
            employee.getLastName(),
            employee.getAge(),
            employee.getAddress(),
            employee.getContact(),
            employee.getStartDate(),
            employee.getWorkStatus()
            );
        }
        System.out.printf("+--------+-----------------+------------+-----+------------+--------------+---------------+-----------------+%n");
    }

    public static void deleteRecord(int type) {
        Utility.clearScreen();
        System.out.println("\n----------------- Remove/Delete Processes -----------------");

        if (type == 1) {
            List<Employees> employee = EmployeeJsonHandler.loadEmployees();

            viewEmployees();

            int id = Utility.readInt("Enter the Id you want to fire/remove (0 to go back): ");

            if (id == 0) {
                return;
            }

            boolean found = false;
            Iterator<Employees> iterator = employee.iterator();
            while (iterator.hasNext()) {
                Employees emp = iterator.next();
                if (emp.getApplicantId() == id) {
                    found = true;
                    System.out.println("Firing the employee. Employee ID: " + emp.getApplicantId());
                    iterator.remove();
                    break;
                }
            }

            if (found) {
                EmployeeJsonHandler.saveEmployee(employee);
                System.out.println("Employee removed successfully.");
            }
            else {
                System.out.println("There is no employee with that Id.");
            }
        }

        else if (type == 2) {
            List<AdoptionLogs> adopter = AdoptionInheritJsonHandler.loadAdoptionLogs();

            viewAllAdoptionLogs("Adoption Logs");

            int id = Utility.readInt("Enter the Id you want to remove (0 to go back): ");

            if (id == 0) {
                return;
            }

            boolean found = false;
            Iterator<AdoptionLogs> iterator = adopter.iterator();
            while (iterator.hasNext()) {
                AdoptionLogs adopt = iterator.next();
                if (adopt.getApplicationId() == id) {
                    found = true;
                    System.out.print("Are you sure you want to delete this adopter - "  + adopt.getUsername() + " (y/n): ");
                    String ans = Utility.inputHandleString().toLowerCase();

                    if (ans.equals("y")) {
                        iterator.remove();
                        AdoptionInheritJsonHandler.saveAdoption(adopter);
                        System.out.println("Adopter removed successfully.");
                        break;
                    }
                    else {
                        System.out.println("Returning to Admin Menu...");
                        break;
                    }
                }
            }
            if (!found) {
                System.out.println("There is no Adopter that have that Id.");
            }
        }

        else if (type == 3) {
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

            int id = Utility.readInt("Enter the animal Id (o to go back): ");

            if (id == 0) {
                return;
            }

            boolean found = false;
            Iterator<Animal> iterator = animals.iterator();
            while (iterator.hasNext()) {
                Animal animal = iterator.next();
                if (animal.getId() == id) {
                    found = true;
                    System.out.print("Are you sure you want to delete " + animal.getId() + " - " + animal.getName() + " (y/n): ");
                    String ans = Utility.inputHandleString().toLowerCase();

                    if (ans.equals("y")) {
                        System.out.println(animal.getType() + " is removed successfully.");
                        iterator.remove();
                        AnimalJsonHandler.saveAnimals(animals);
                        break;
                    }
                    else {
                        System.out.println("Returning to Admin Menu...");
                    }
                }
            }
            if (!found) {
                System.out.println("There is no animal who own that Id.");
            }
        }

        else if (type == 4) {
            List<RehomingRequest> rehoming = RehomeJsonHandler.loadRequests();

            UtilWorker.viewPendingRequest();

            int id = Utility.readInt("Enter the Id you want to delete (0 to go back): ");

            if (id == 0) {
                return;
            }

            boolean found = false;
            Iterator<RehomingRequest> iterator = rehoming.iterator();
            while (iterator.hasNext()) {
                RehomingRequest rehome = iterator.next();
                if (rehome.getRehomingId() == id) {
                    found = true;
                    System.out.print("Are you sure you want to delete " + rehome.getRehomingId() + " (y/n): ");
                    String ans = Utility.inputHandleString().toLowerCase();

                    if (ans.equals("y")) {
                        System.out.println(rehome.getRehomingId() + " is removed successfully.");
                        iterator.remove();
                        RehomeJsonHandler.saveRequests(rehoming);
                        break;
                    }
                    else {
                        System.out.println("Returning to Admin menu...");
                    }
                }
            }
            if (!found) {
                System.out.println("There are no rehoming request that have this id: " + id);
            }
        }

        else if (type == 5) {
            List<ReportLogs> reports = ReportJsonHandler.loadUserReports();

            viewAllUserReports();

            int id = Utility.readInt("Enter the Id you want to delete (0 to go back): ");

            if (id == 0) {
                return;
            }

            boolean found = false;
            Iterator<ReportLogs> iterator = reports.iterator();
            while (iterator.hasNext()) {
                ReportLogs report = iterator.next();
                if (report.getReportId() == id) {
                    found = true;
                    System.out.println("Are you sure you want to delete " + report.getReportId() + " (y/n): ");
                    String ans = Utility.inputHandleString().toLowerCase();

                    if (ans.equals("y")) {
                        System.out.println(report.getReportId() + " is removed successfully.");
                        iterator.remove();
                        ReportJsonHandler.saveReportLogs(reports);
                        break;
                    }
                    else {
                        System.out.println("Returning to Admin Menu...");
                    }
                }
            }
            if (!found) {
                System.out.println("There is report that have this id: " + id);
            }
        }
    }

    public static void updateEmployees() {
        Utility.clearScreen();
        List<Employees> employees = EmployeeJsonHandler.loadEmployees();
        
        viewEmployees();

        System.out.print("\nDo you want to update the status of employee (y/n or .. to go back)? ");
        String answer = Utility.inputHandleString().toLowerCase();

        if (answer.equals("..")) {
            return;
        }


        if (answer.equals("y")) {
            int id = Utility.readInt("Enter the Id of the Employee: ");
            boolean found = false;

            for (Employees employee : employees) {
                if (id == employee.getApplicantId()) {
                    found = true;

                    System.out.println("\nSelect new Status: ");
                    System.out.println("[1] Active");
                    System.out.println("[2] On Leave");
                    System.out.println("[3] Resigned");
                    System.out.print("Enter the updated status: ");
                    int currentStatus = Utility.inputHandlerInt();

                    String newStatus;
                    switch (currentStatus) {
                        case 1:
                            newStatus = "Active";
                            break;
                        case 2:
                            newStatus = "On Leave";
                            break;
                        case 3:
                            newStatus = "Resigned";
                            break;
                        default:
                            System.out.println("Invalid Choice. Status not changed.");
                            return;
                    }

                    employee.setWorkStatus(newStatus);
                    EmployeeJsonHandler.saveEmployee(employees);
                    System.out.println("\nStatus updated successfully.");
                    break;
                }
            }
            if(!found) {
                System.out.println("\nNo employee found with Id: " + id);
            }
        }
        else {
            System.out.println("Returning to Process Menu...");
        }
    }

    public static void createNewProfile() {
        Utility.clearScreen();
        List<Admin> admins = AdminJsonHandler.loadAdmins();
        System.out.println("\n----------------- Create Another Profile -----------------");

        String username = Utility.inputHandlerStr("Enter new Username (.. to go back): ");

        if (username.equals("..")) {
            return;
        }
        
        boolean exists = false;
        for (Admin admin : admins) {
            if (username.equals(admin.getUsername())) {
                exists = true;
                break;
            }
        }
        if (exists) {
            System.out.println("Username is already taken. Please enter a new username.");
        }
        else {
            String password = Utility.inputHandlerStr("Enter new Password: ");
            if ((!username.isEmpty()) && (!password.isEmpty())) {
                Admin admin = new Admin(username, password);
                AdminJsonHandler.addAdmin(admin);
                System.out.println("New admin profile is added successfully.");
            }
            else {
                System.out.println("Username or password must not be empty.");
            }
        }
    }
    

    public static void changePassword() {
        Utility.clearScreen();
        System.out.println("\n----------------- Change Password -----------------");

        List<Admin> admins = AdminJsonHandler.loadAdmins();

        String oldPassword = Utility.inputHandlerStr("Enter your old password (.. to go back): ");

        if (oldPassword.equals("..")) {
            return;
        }

        boolean found = false;
        for (Admin admin : admins) {
            if (oldPassword.equals(admin.getPassword())) {
                found = true;
                String newPassword = Utility.inputHandlerStr("Enter a new password: ");

                int chances = 3;
                while (chances > 0) {
                    String confirmPassword = Utility.inputHandlerStr("Enter your password again: ");

                    if (newPassword.equals(confirmPassword)) {
                        admin.setPassword(newPassword);
                        AdminJsonHandler.saveAdmins(admins);
                        System.out.println("Your password is changed successfully.");
                        return;
                    }
                    else {
                        System.out.println("The password that you entered did not match. try again chance(s) " + chances);
                        chances--;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("You entered an invalid password.");
        }   
    }

    public static void chageUsername() {
        Utility.clearScreen();
        System.out.println("\n----------------- Change Username -----------------");

        List<Admin> admins = AdminJsonHandler.loadAdmins();

        String oldUsername = Utility.inputHandlerStr("Enter your old username (.. to go back): ");

        if (oldUsername.equals("..")) {
            return;
        }

        boolean found = false;
        for (Admin admin : admins) {
            if (oldUsername.equals(admin.getUsername())) {
                found = true;
                String newUsername = Utility.inputHandlerStr("Enter a new username: ");

                int chances = 3;
                while (chances > 0) {
                    String confirmUsername = Utility.inputHandlerStr("Enter your username again: ");

                    if (newUsername.equals(confirmUsername)) {
                        admin.setUsername(newUsername);
                        AdminJsonHandler.saveAdmins(admins);
                        System.out.println("Your username is changed successfully.");
                        return;
                    }
                    else {
                        System.out.println("The username that you entered did not match. try again chance(s) " + chances);
                        chances--;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("You entered an invalid username.");
        }   
    }

    public static void changeWorkerPassword() {
        Utility.clearScreen();
        List<Employees> employees = EmployeeJsonHandler.loadEmployees();
        System.out.println("\n----------------- Change Employee Password -----------------");

        System.out.printf("+------+-------------------+--------------+---------------+%n");
        System.out.printf("| ID   | Name              | Username     | Password      |%n");
        System.out.printf("+------+-------------------+--------------+---------------+%n");
        for (Employees employee : employees) {
            System.out.printf("| %-4d | %-17s | %-12s | %-13s |%n",
                employee.getApplicantId(),
                employee.getFirstName() + " " + employee.getLastName(),
                employee.getUsername(),
                employee.getPassword()
            );
        }
        System.out.printf("+------+-------------------+--------------+---------------+%n");

        String ans = Utility.inputHandlerStr("Change the worker password (y/n or .. to go back)?: ").toLowerCase();

        if (ans.equals("..")) {
            return;
        }

        if (ans.equals("y")) {
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
        else {
            System.out.println("Returning to Customization Menu...");
        }
    }
}