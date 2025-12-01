package menus;

import util.UtilWorker;
import util.UtilAdmin;
import util.Utility;
import models.Employees;

public class MainWorker {

    public static void workerMain() {

        while (true) {
            Utility.clearScreen();
            Utility.loadingScreen();
            Utility.clearScreen();
            Employees employee = new Employees();
            UtilWorker.showWorkerMenu();

            int choice = 0;
            int chance = 3;

            while (chance > 0) {

                choice = Utility.getInput("Enter you choice: ");

                if (choice >= 1 && choice <= 6) {
                    break;
                }

                else {
                    chance--;
                    System.out.println("\nYou entered an ivalid input. You have " + chance + " chance(s) left.");
                }
            }

            switch (choice) {
                case 1:
                    boolean animalMenu = true;
                    while (animalMenu) {
                        UtilWorker.animalRecordMenu();

                        switch (Utility.getInput("Enter your choice: ")) {
                            case 1:
                                employee.addNewAnimal();
                                break;
                            case 2:
                                employee.updateAnimalInformation();
                                break;
                            case 3:
                                employee.updateAnimalStatus();
                                break;
                            case 4:
                                UtilWorker.showAllAnimals();
                                break;
                            case 5:
                                System.out.println("Returning to Worker Menu...");
                                animalMenu = false;
                                break;
                            default:
                                System.out.println("You entered an invalid input. Returning to Worker Menu...");
                                animalMenu = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean adoptionMenu = true;
                    while (adoptionMenu) {
                        UtilWorker.showAdoptionMenu();

                        switch (Utility.getInput("Enter your choice: ")) {
                            case 1:
                                UtilWorker.viewAdoptionApplication();
                                break;
                            case 2:
                                employee.applicationDecision();
                                break;
                            case 3:
                                System.out.println("Returning to Worker Menu...");
                                adoptionMenu = false;
                                break;
                            default:
                                System.out.println("You entered an invalid input.");
                                adoptionMenu = false;
                                break;
                        }   
                    }
                    break;
                case 3:
                    boolean reportMenu = true;
                    while (reportMenu) {
                        UtilWorker.showReportMenu();

                        switch (Utility.getInput("Enter your choice: ")) {
                            case 1:
                                UtilAdmin.viewAllUserReports();
                                break;
                            case 2:
                                employee.updateReportStatus();
                                break;
                            case 3:
                                UtilWorker.updatePriority();
                                break;
                            case 4:
                                System.out.println("Returning to Worker Menu...");
                                reportMenu = false;
                                break;
                            default:
                                System.out.println("You entered an invalid input.");
                                reportMenu = false;
                                break;
                        }  
                    }
                    break;
                case 4: 
                    boolean rehomeMenu = true;
                    while (rehomeMenu) {
                        UtilWorker.showRehomingMenu();

                        switch (Utility.getInput("Enter your choice: ")) {
                            case 1:
                                UtilWorker.viewPendingRequest();
                                break;
                            case 2:
                                employee.reviewRequest();
                                break;
                            case 3:
                                System.out.println("Returning to Worker Menu...");
                                rehomeMenu = false;
                                break;
                            default:
                                System.out.println("You entered an invalid input returning to Worker Menu...");
                                rehomeMenu = false;
                                break;
                        } 
                    }
                    break;
                case 5:
                    UtilWorker.changePassword();
                    break;
                case 6:
                    Utility.clearScreen();
                    Utility.signingOut();
                    return;
                default:
                    System.out.println("You entered an invalid input. Exiting program");
                    break;
            }
        }
    }
}