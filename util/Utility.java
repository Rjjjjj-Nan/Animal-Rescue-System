package util;

import java.util.Scanner;

import java.util.Random;

public class Utility {
    
    private static final Scanner sc = new Scanner(System.in);
    private static final Random rand = new Random();

    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        }
        catch (Exception e) {
            System.out.println("Error clearing console: " + e.getMessage());
        }
    }

    public static String inputHandlerStr(String promt) {
        System.out.print("\n" + promt);
        return sc.nextLine();
    }

    public static String inputHandleString() {
        return sc.nextLine();
    }

    public static int getInput(String promt) {
        int choice;
        System.out.print(promt);
        choice = Utility.inputHandlerInt();

        return choice;
    }

    public static int inputHandlerInt() {
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    public static int readInt(String prompt) {
        while (true) {
            System.out.print("\n" + prompt);
            String input = sc.nextLine();

            if (input.matches("\\d+")) {
                return Integer.parseInt(input);
            }
            System.out.println("Invalid Input. Please enter a valid number.");
        }
    }

    public static int randomIdGenerator() {
        int min = 1000;
        int max = 9999;

        int randomId = rand.nextInt(max - min + 1);
        return randomId;
    }

    public static void loadingScreen(){
        System.out.print("\n\n\t\tLoading");
        try {
            for (int i = 0; i < 5; i++) { 
                Thread.sleep(500); 
                System.out.print("."); 

                
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void signingOut() {
        System.out.print("\n\n\t\tSigning Out");
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //inde to nagana sa IDE, sa command prompt lang pede (password encryption) - inde implemented
    public static String readPassword() {
        StringBuilder password = new StringBuilder();

        try {
            while (true) {
                int ch = System.in.read();

                if (ch == '\n' || ch == '\r') {
                    break;
                }

                //ascii ata to ang alam ko
                if (ch == 8 || ch == 127) {
                    if (password.length() > 0) {
                        password.deleteCharAt(password.length() - 1);
                        System.out.print("\b \b");
                    }
                }
                else {
                    password.append((char) ch);
                    System.out.print("*");
                }
            }
        }
        catch (Exception e) {
            System.out.println("Invalid " + e.getStackTrace());
        }
        return password.toString();
    }

    public static void clearLeftOver() {
        try {
            while (System.in.available() > 0) {
                System.in.skip(System.in.available());
            }
        }
        catch (Exception e) {}
    }

}
