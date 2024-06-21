import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class GroupCheckIn extends CheckIn{
    private Passenger[] passengerDetails;
    private Baggage[] passengerBaggage;
    private int passengers; // Used as a counter to track how many passengers have been added
    private String groupName;

    public GroupCheckIn(Scanner scanner, Random random) {
    }
    
    public void groupCheckIn() {
        passengerDetails = new Passenger[200];
        passengerBaggage = new Baggage[200];
        System.out.println("Group check-in process started.\n");
        getCheckInType();
        askForGroupName();
        Set<String> seatNumbers = new HashSet<>(); // To ensure unique seat numbers

        while (true) {
            System.out.println("\nEnter details for passenger " + (passengers + 1) + ":");
            getPassengerDetails(passengers);
            passengers++;
            System.out.println("\n-------------------------------");
            System.out.println("| 1. Add a new member         |");
            System.out.println("| 2. Finish                   |");
            System.out.println("-------------------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 2) {
                break;
            }
        }

        getIssueType();
        
        // Generate common details for all passengers
        String flightNumber = generateRandomFlightNo();
        String destination = generateRandomCountry();
        String time = generateRandomTime();

        // Print group name
        System.out.println("\nGroup Name: " + groupName);
        
        // Generate boarding pass for each passenger
        for (int i = 0; i < passengers; i++) {
            String seatNumber;
            do {
                seatNumber = generateRandomSeatNumber();
            } while (seatNumbers.contains(seatNumber));
            seatNumbers.add(seatNumber); // Ensure unique seat numbers
            super.generateBoardingPass(passengerDetails[i].getName(), passengerDetails[i].getPassportNo(), passengerDetails[i].getBookingReference(), flightNumber, destination, time, passengerDetails[i].getSpecialNeeds() , passengerDetails[i].getSpecialNeedsDetail(), passengerDetails[i].getHasBaggage(), passengerBaggage[i].getNoOfBaggage(), passengerBaggage[i].getBaggageWeight(), passengerBaggage[i].getBaggageTag(), passengerBaggage[i].getHasScreened(), seatNumber, generateRandomConfirmationID());
            if(passengerBaggage[i].getHasScreened()){
                super.generateBaggageTag(passengerBaggage[i].getBaggageTag(), passengerDetails[i].getBookingReference(), flightNumber);
            }
        }
        
        System.out.println("No of passengers checked in for group '" + groupName + "': " + passengers + "\n");
        printCheckInType();
        printIssueType();
        postCheckInActions();
        passengers = 0; // Reset counter
    }
    
    // Prompt for the group name
    private void askForGroupName() {
        System.out.print("\nEnter the group name: ");
        groupName = scanner.nextLine();
    }

    // Get each passenger details for group check in
    private void getPassengerDetails(int i) {
            passengerDetails[i] = new Passenger(CheckIn.getName(), CheckIn.getPassportNo(), CheckIn.getBookingReference(), "", CheckIn.getSpecialNeedsAssistance());
            if (passengerDetails[i].getSpecialNeeds()) {
            passengerDetails[i].setSpecialNeedsDetail(getSpecialNeedsDetail());
        }
            if (CheckIn.getHasBaggage()) {
                passengerDetails[i].setHasBaggage(true);
                passengerBaggage[i] = new Baggage(CheckIn.getNoOfBaggage(), CheckIn.getBaggageWeight(), CheckIn.generateRandomBaggageTag(), CheckIn.getHasScreened());
            } else {
                passengerBaggage[i] = new Baggage(0, 0, "", false);
            }
    }
}
