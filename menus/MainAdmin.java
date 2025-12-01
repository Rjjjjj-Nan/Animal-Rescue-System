package menus;

import util.UtilAdmin;
import util.Utility;

import models.Admin;

public class MainAdmin {
    public static void mainAdmin () {

        while (true) {
            Utility.clearScreen();
            Utility.loadingScreen();
            Utility.clearScreen();
            Admin admin = new Admin();
            UtilAdmin.adminMenu();

            int choice = 0;
            int chance = 3;

            while (chance > 0) {

                choice = Utility.getInput("Enter you choice: ");

                if (choice >= 1 && choice <= 5) {
                    break;
                }

                else {
                    chance--;
                    System.out.println("\nYou entered an ivalid input. You have " + chance + " chance(s) left.");
                }
            }

            switch (choice) {
                case 1:
                    boolean transactionMenu = true;
                    while (transactionMenu) {
                        UtilAdmin.transactChoices();

                        switch (Utility.getInput("Enter you choice: ")) {
                            case 1:
                                UtilAdmin.viewAllAdoptionLogs("Adoption Logs");
                                break;
                            case 2:
                                UtilAdmin.viewAllRehomeRequest();
                                break;
                            case 3:
                                UtilAdmin.viewAllUserReports();
                                break;
                            case 4:
                                System.out.println("\nReturning to Admin Menu...");
                                transactionMenu = false;
                                break;
                            default:
                                System.out.println("Invalid input! Returning to Admin Menu...");
                                transactionMenu = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean applicantMenu = true;
                    while (applicantMenu) {
                        UtilAdmin.applicantChoices();

                        switch (Utility.getInput("Enter you choice: ")) {
                            case 1:
                                UtilAdmin.viewAllApplicants();
                                break;
                            case 2:
                                UtilAdmin.hireApplicant();
                                break;
                            case 3:
                                System.out.println("\nReturning to Admin Menu...");
                                applicantMenu = false;
                                break;
                            default:
                                System.out.println("Invalid Input. Returning to Admin Menu...");
                                applicantMenu = false;
                                break;
                        }
                    }
                    break;
                case 3:
                    boolean recordMenu = true;
                    while (recordMenu) {
                        UtilAdmin.manageRecordsChoices();

                        switch (Utility.getInput("Enter your choice: ")) {
                            case 1:
                                UtilAdmin.updateEmployees();
                                break;
                            case 2:
                                UtilAdmin.deleteType();

                                int type = Utility.getInput("Enter your choice: ");

                                UtilAdmin.deleteRecord(type);
                                break;
                            case 3:
                                admin.updateReportStatus();
                                break;
                            case 4:
                                admin.applicationDecision();
                                break;
                            case 5:
                                admin.reviewRequest();
                                break;
                            case 6:
                                boolean animalRecordMenu = true;
                                while (animalRecordMenu) {
                                    UtilAdmin.updateAnimalInformation();

                                    switch (Utility.getInput("Enter your choice: ")) {
                                        case 1:
                                            admin.addNewAnimal();
                                            break;
                                        case 2:
                                            admin.updateAnimalInformation();
                                            break;
                                        case 3:
                                            admin.updateAnimalStatus();
                                            break;
                                        case 4:
                                            System.out.println("Returning to Record Management Menu...");
                                            animalRecordMenu = false;
                                            break;
                                        default:
                                            System.out.println("You entered an invalid input. Returning to Record Management Menu...");
                                            animalRecordMenu = false;
                                            break;
                                    }
                                }
                            case 7:
                                System.out.println("Returning to Admin Menu...");
                                recordMenu = false;
                                break;
                            default:
                                System.out.println("Invalid Input. Returning to Admin Menu...");
                                recordMenu = false;
                                break;
                        }
                    }
                    break;
                case 4:
                    boolean customizationMenu = true;
                    while (customizationMenu) {
                        UtilAdmin.accountCustomization();

                        switch (Utility.getInput("Enter your choice: ")) {
                            case 1:
                                UtilAdmin.createNewProfile();
                                break;
                            case 2:
                                UtilAdmin.changePassword();
                                break;
                            case 3:
                                UtilAdmin.chageUsername();
                                break;
                            case 4:
                                UtilAdmin.changeWorkerPassword();
                                break;
                            case 5:
                                System.out.println("\nReturning to Admin Menu...");
                                customizationMenu = false;
                                break;
                            case 6:
                                System.out.println("Invalid Input. Returning to Admin Menu...");
                                customizationMenu = false;
                                break;
                        }
                    }
                    break;
                case 5:
                    Utility.clearScreen();
                    Utility.signingOut();
                    return;
                default:
                    System.out.println("No more chances left. Returning to Admin Menu...");
                    break;
            }
        }
    }
}
