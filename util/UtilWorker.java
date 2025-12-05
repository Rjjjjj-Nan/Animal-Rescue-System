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

    // menus for the program
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
        System.out.println("[1] View Adoption Applications");
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

    public static void showAllAnimals() {
        Utility.clearScreen(); //para maclear yung nandun sa taas
        List<Animal> animals = AnimalJsonHandler.loadAnimals(); //nag gagawa ng list for animal class tapos niloload yung data

        System.out.println("\nAnimals:\n"); //para lang to sa pag didisplay ng data animal
        System.out.printf("+-----+--------+-----------+-----------------+--------+------+------------+%n");
        System.out.printf("| ID  | Name   | Type      | Breed           | Sex    | Age  | Status     |%n");
        System.out.printf("+-----+--------+-----------+-----------------+--------+------+------------+%n");

        //for each loop
        // nag for each dito para maloop yung nasa loob ng list then display gamit yung mga attributes ng animal
        for (Animal dog : animals) {
            System.out.printf("| %-3d | %-6s | %-9s | %-15s | %-6s | %-4s | %-10s |%n", // ito nigamit lang para iformat yung kung pano madidisplay yung mga data
                    dog.getId(), // ito yung mga attrib ng animal na nadisplay
                    dog.getName(),
                    dog.getType(),
                    dog.getBreed(),
                    dog.getSex(),
                    dog.getAge(),
                    dog.getStatus());
        }

        System.out.printf("+-----+--------+-----------+-----------------+--------+------+------------+%n");
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

    public static void viewAdoptionApplication() {
        Utility.clearScreen();
        List<AdoptionLogs> adoptions = AdoptionInheritJsonHandler.loadAdoptionLogs(); // gaya lang din to nung kanina na nag gagawa ng list then load yung data

        System.out.println("\n-------- View Adoption Application --------");

        // ito nigagamit lang as validator para malaman if meron na ba laman yung list
        if (adoptions.isEmpty()) {
            System.out.println("There are no adoption applications.");
            return;
        }

        // ito yung nag didisplay gaya nung kanina same structure lang naiiba lang yung mga data na naka input
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

    public static void updatePriority() {
        Utility.clearScreen();
        List<ReportLogs> reports = ReportJsonHandler.loadUserReports(); // pang load ng data (if makita na meron List<> sa una tapos load keyword pang load lang ng data)

        System.out.println("\n-------- Update Priority --------");

        System.out.print("Do you want to view the report list (y/n or .. to go back): ");
        String answer = Utility.inputHandleString().toLowerCase();

        // ito yung nigamit para mavalidate if want ng user na mag go back
        if (answer.equals("..")) {
            return;
        }

        // validator para malaman if meron na laman yung list
        if (answer.equals("y")) {
            UtilAdmin.viewAllUserReports();
        }
        
        System.out.print("Enter the Id (0 to cancel): ");
        int id = Utility.inputHandlerInt();
        if (id == 0) {
            return;
        }

        // for each loop
        // pang iterate ng laman ng list
        boolean found = false;
        for (ReportLogs report : reports) {
            if (id == report.getReportId()) {
                found = true;
                System.out.println("\n-------- Choices --------");
                System.out.println("[1] Low");
                System.out.println("[2] Med");
                System.out.println("[3] High");
                System.out.println("[4] Urgent");
                System.out.println("[5] Cancel");

                // switch statement
                // para sa pag seset ng new status ng report
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
                    case 5:
                        System.out.println("Update cancelled.");
                        return;
                    default:
                        System.out.println("You entered an invalid input.");
                        return;
                }

                report.setPriority(status); //dito nag seset ng new value para sa (priority)
                ReportJsonHandler.saveReportLogs(reports); // nag set kanina ng new value dito naman nisasave na lang yung bagong set na value para mag reflect na sa json file
                System.out.println("Update Status Successfully.");
                break;
            }
        }
        //dito ay validation para malaman if wala ba nag eexist na ganun na data
        if (!found) {
            System.out.println("There is no report with this Id: " + id);
        }
    }

    public static void viewPendingRequest() {
        Utility.clearScreen();
        List<RehomingRequest> requests = RehomeJsonHandler.loadRequests(); // nag loload ng data

        System.out.println("\n-------- Pending Request --------");

        //display ng data
        //gamit yung for each
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

    public static void changePassword() {
        Utility.clearScreen();
        List<Employees> employees = EmployeeJsonHandler.loadEmployees(); // niloload yung list ng employees
        System.out.println("\n-------- Worker Password Customizer --------");

        int id = Utility.getInput("Enter the 4 digit worker ID (0 to go back): ");

        if (id == 0) {
            return;
        }

        // for each loop
        // para ma iterate yung mga laman ng employee na list
        boolean found = false;
        for (Employees employee : employees) {
            if (id == employee.getApplicantId()) {
                found = true;
                String old = Utility.inputHandlerStr("Enter your old password: "); // dito nieenter yung dating password
                String newPass = Utility.inputHandlerStr("Enter the new password: "); // dito nag lalagay ng bago na password
                if (!newPass.equals(old)) {
                    String confirm = Utility.inputHandlerStr("Please confirm your new password: "); // confirmation ng new password
                    if (confirm.equals(newPass)) {
                        employee.setPassword(newPass); // niseset yung new password
                        EmployeeJsonHandler.saveEmployee(employees); // nisasave yung new password
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
