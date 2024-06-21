import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(); // Create a Random object
        CheckIn checkIn = new CheckIn();
        GroupCheckIn groupCheckIn = new GroupCheckIn(scanner, random); // Pass both Scanner and Random objects

        System.out.println("-------------------------------");
        System.out.println("| Welcome to AeroCheck System |");

        while (true) {
            System.out.println("-------------------------------");
            System.out.println("| Select an option:           |");
            System.out.println("-------------------------------");
            System.out.println("| 1. Individual Check-in      |");
            System.out.println("| 2. Group Check-in           |");
            System.out.println("| 3. Exit                     |");
            System.out.println("-------------------------------");
            System.out.print("Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: 
                    System.out.println();
                    checkIn.individualCheckIn();
                    break;
                case 2:
                    System.out.println();
                    groupCheckIn.groupCheckIn();
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Exiting AeroCheck System. Have a nice day!");
                    scanner.close(); // Close the scanner
                    System.exit(0); // Exit the program
                default:
                    System.out.println();
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
