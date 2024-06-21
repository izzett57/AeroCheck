import java.util.Random;
import java.util.Scanner;

public class CheckIn {
    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();
    String checkInType;
    String issueType;
    
    String[] gateNumbers = {"A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2", "E1", "E2"};
    String[] counterNumbers = {"A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2", "E1", "E2"};
    String[] flightStatuses = {"On Time", "Delayed", "Landed", "Boarding", "Gate Change", "Scheduled", "Final Call", "Delayed Onboard"};

    String gate = gateNumbers[random.nextInt(gateNumbers.length)];
    String counter = counterNumbers[random.nextInt(counterNumbers.length)];
    String flightStatus = flightStatuses[random.nextInt(flightStatuses.length)];

    public void individualCheckIn() {
        System.out.println("Individual check-in process started.\n");
        performCheckIn();
    }

    private void performCheckIn() {
        getCheckInType();
        Passenger p1 = new Passenger (getName(), getPassportNo(), getBookingReference(), generateRandomFlightNo(), getSpecialNeedsAssistance());
        if (p1.getSpecialNeeds()) {
            p1.setSpecialNeedsDetail(getSpecialNeedsDetail());
        }
        if (getHasBaggage()) {
            p1.setHasBaggage(true);
            Baggage b1 = new Baggage(getNoOfBaggage(), getBaggageWeight(), generateRandomBaggageTag(), getHasScreened());   
            getIssueType();
            generateBoardingPass(p1.getName(), p1.getPassportNo(), p1.getBookingReference(), p1.getFlightNo(), generateRandomCountry(), generateRandomTime(), p1.getSpecialNeeds(), p1.getSpecialNeedsDetail(), p1.getHasBaggage(), b1.getNoOfBaggage(), b1.getBaggageWeight(), b1.getBaggageTag(), b1.getHasScreened(), generateRandomSeatNumber(), generateRandomConfirmationID());
            if (b1.getHasScreened()) {
                generateBaggageTag(b1.getBaggageTag(), p1.getBookingReference(), p1.getFlightNo());
            }
        }
        if (p1.getSpecialNeeds() && !p1.getHasBaggage()) {
            getIssueType();
            generateBoardingPass(p1.getName(), p1.getPassportNo(), p1.getBookingReference(), p1.getFlightNo(), generateRandomCountry(), generateRandomTime(), p1.getSpecialNeeds(), p1.getSpecialNeedsDetail(), p1.getHasBaggage(), 0, 0, generateRandomBaggageTag(), false, generateRandomSeatNumber(), generateRandomConfirmationID());
        }
        if (!p1.getSpecialNeeds() && !p1.getHasBaggage()) {
            getIssueType();
            generateBoardingPass(p1.getName(), p1.getPassportNo(), p1.getBookingReference(), p1.getFlightNo(), generateRandomCountry(), generateRandomTime(), p1.getSpecialNeeds(), p1.getSpecialNeedsDetail(), p1.getHasBaggage(), 0, 0, generateRandomBaggageTag(), false, generateRandomSeatNumber(), generateRandomConfirmationID());
        }
        
        printCheckInType();
        printIssueType();
        postCheckInActions();
    }

    public void getCheckInType() {
        while (true) {
                System.out.println("----------------------------------");
                System.out.println("| Select an option:              |");
                System.out.println("----------------------------------");
                System.out.println("| 1. Counter Check-in            |");
                System.out.println("| 2. Self-Service Kiosk Check-in |");
                System.out.println("----------------------------------");
                System.out.print("Option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            switch (choice) {
                    case 1:
                        System.out.println("\nCounter check-in selected!");
                        checkInType = "counter";
                        return;
                    case 2:
                        System.out.println("\nSelf-service kiosk selected!");
                        checkInType = "self";
                        return;
                    default:
                        System.out.println();
                        System.out.println("\nInvalid option. Please try again.");
            }
        }
    }
    
    public static String getName() {
        while (true) {
            System.out.println("\nEnter your name (max 100 characters):");
            String name = scanner.nextLine();
            if (name.length() <= 100) {
                return name;
            } else {
                System.out.println("\nName must be not more than 100 characters.");
            }
        }
    }

    public static String getPassportNo() {
        while (true) {
            System.out.println("\nEnter your Passport Number (format: P12345):");
            String passportNo = scanner.nextLine();
            if (passportNo.matches("[P]\\d{5}")) {
                return passportNo;
            } else {
                System.out.println("\nPassport must be exactly in the correct format. (format: P12345)");
            }
        }
    }

    public static String getBookingReference() {
        while (true) {
            System.out.println("\nEnter your booking reference (format: ABC1234):");
            String bookingReference = scanner.nextLine();
            if (bookingReference.matches("[A-Z]{3}\\d{4}")) {
                return bookingReference;
            } else {
                System.out.println("Booking reference must be in the format ABC1234.");
            }
        }
    }

    public static boolean getSpecialNeedsAssistance() {
        while (true) {
            System.out.println("\nDo you need special assistance? (Y/N):");
            String response = scanner.nextLine().trim();
            if (response.equalsIgnoreCase("y")) {
                return true;
            } else if (response.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("\nInvalid option. Please try again.");
            }
        }
    }
    
    public static String getSpecialNeedsDetail() {
        while (true) {
            System.out.println("\nEnter special assistance detail (max 100 characters):");
            String name = scanner.nextLine();
            if (name.length() <= 100) {
                return name;
            } else {
                System.out.println("\nSpecial Assistance Detail must be not more than 100 characters.");
            }
        }
    }
    
    public static boolean getHasBaggage() {
        while (true) {
        System.out.println("\nDo you wish to check-in baggage(s)? (Y/N):");
        String response = scanner.nextLine().trim();
        if (response.equalsIgnoreCase("y")) {
                return true;
            } else if (response.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("\nInvalid option. Please try again.");
            }
        }
    }
    
    public static int getNoOfBaggage() {
        System.out.println("\nEnter number of check-in baggages: ");
        int noOfBaggage = scanner.nextInt();
        return noOfBaggage;
    }

    public static double getBaggageWeight() {
        while (true) {
            System.out.println("\nEnter total baggage weight (less than 20 kg):");
            double weight = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            if (weight < 20) {
                return Math.round(weight * 10.0) / 10.0; // Round to 1 decimal place
            } else {
                System.out.println("\nOnly baggage weighing less than 10 kilograms is permitted on the plane.");
            }
        }
    }

    public static boolean getHasScreened() {
        while (true) {
        System.out.println("\nHas your baggage(s) been screened? (Y/N):");
        String response = scanner.nextLine().trim();
        if (response.equalsIgnoreCase("y")) {
                return true;
            } else if (response.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("\nInvalid option. Please try again.");
            }
        }
    }
    
    public void getIssueType() {
        while (true) {
                System.out.println("\nWould you like to have a printed copy? (Y/N): ");
                char choice = scanner.next().charAt(0);
                switch (choice) {
                        case 'y','Y':
                            issueType = "print";
                            return;
                        case 'n','N':
                            issueType = "mobile";
                            return;
                        default:
                            System.out.println();
                            System.out.println("Invalid option. Please try again.");
                }
        }
    }
    
    public void generateBoardingPass(String name, String passport, String bookingReference, String flightNo, String destination, String time, boolean specialNeeds, String specialNeedsDetail, boolean hasBaggage, int noOfBaggage, double baggageWeight, String baggageTag, boolean hasScreened, String seatNumber, String confirmationID) {
        if(specialNeeds && hasBaggage) { // Generate with special needs detail and baggage
            String[] boardingPass = {
                "Boarding Pass",
                "*",
                "Name: " + name,
                "Passport No: " + passport,
                "Booking Reference: " + bookingReference,
                "*",
                "Flight Number: " + flightNo,
                "Destination: " + destination,
                "Time: " + time,
                "Gate: " + gate,
                "Flight Status: " + flightStatus,
                "Special Needs: " + (specialNeeds ? "Yes" : "No"),
                "Details of Special Needs: " + specialNeedsDetail,
                "Check-in baggages: " + (hasBaggage ? "Yes" : "No"),
                "No of baggages: " + noOfBaggage,
                "Total Baggage Weight: " + baggageWeight + " kg",
                "Baggage Screened: " + (hasScreened ? "Yes" : "No"),
                "Seat Number: " + seatNumber,
                "*",
                "Confirmation ID: " + confirmationID,
                "Baggage Tag: " + baggageTag
            };
            printBoxedContent(boardingPass);
        } else if(hasBaggage){ // Generate only with baggage
            String[] boardingPass = {
            "Boarding Pass",
            "*",
            "Name: " + name,
            "Passport No: " + passport,
            "Booking Reference: " + bookingReference,
            "*",
            "Flight Number: " + flightNo,
            "Destination: " + destination,
            "Time: " + time,
            "Gate: " + gate,
            "Flight Status: " + flightStatus,
            "Special Needs: " + (specialNeeds ? "Yes" : "No"),
            "Check-in baggages: " + (hasBaggage ? "Yes" : "No"),
            "No of baggages: " + noOfBaggage,
            "Total Baggage Weight: " + baggageWeight + " kg",
            "Baggage Screened: " + (hasScreened ? "Yes" : "No"),
            "Seat Number: " + seatNumber,
            "*",
            "Confirmation ID: " + confirmationID,
            "Baggage Tag: " + baggageTag
            };
            printBoxedContent(boardingPass);
        } else if (specialNeeds){ // Generate with only special needs detail
            String[] boardingPass = {
                "Boarding Pass",
                "*",
                "Name: " + name,
                "Passport No: " + passport,
                "Booking Reference: " + bookingReference,
                "*",
                "Flight Number: " + flightNo,
                "Destination: " + destination,
                "Time: " + time,
                "Gate: " + gate,
                "Flight Status: " + flightStatus,
                "Special Needs: " + (specialNeeds ? "Yes" : "No"),
                "Details of Special Needs: " + specialNeedsDetail,
                "Check-in baggages: " + (hasBaggage ? "Yes" : "No"),
                "Seat Number: " + seatNumber,
                "*",
                "Confirmation ID: " + confirmationID,
            };
            printBoxedContent(boardingPass);
        } else { // Generate with no special needs or baggage
            String[] boardingPass = {
                "Boarding Pass",
                "*",
                "Name: " + name,
                "Passport No: " + passport,
                "Booking Reference: " + bookingReference,
                "*",
                "Flight Number: " + flightNo,
                "Destination: " + destination,
                "Time: " + time,
                "Gate: " + gate,
                "Flight Status: " + flightStatus,
                "Special Needs: " + (specialNeeds ? "Yes" : "No"),
                "Check-in baggages: " + (hasBaggage ? "Yes" : "No"),
                "Seat Number: " + seatNumber,
                "*",
                "Confirmation ID: " + confirmationID,
            };
            printBoxedContent(boardingPass);
        }
            if (hasBaggage && !hasScreened){
                System.out.println("\nPlease proceed to check-in counter " + counter + " to screen and check-in your baggage(s).\n");
            }
    }

    public void generateBaggageTag(String baggageTag, String bookingReference, String flightNo) {
            System.out.println();
            String[] boardingPass = {
                "Baggage Tag",
                "*",
                "Tag ID: " + baggageTag,
                "Booking Reference: " + bookingReference,
                "Flight Number: " + flightNo
            };
            printBoxedContent(boardingPass);
    }
    
    public static void printBoxedContent(String[] contents) {
        int maxLength = getMaxLength(contents);

        printHorizontalBorder(maxLength);
        for (String content : contents) {
            if (content.equals("*")) {
                printHorizontalBorder(maxLength);
            } else {
                printContentLine(content, maxLength);
            }
        }
        printHorizontalBorder(maxLength);
    }

    private static int getMaxLength(String[] contents) {
        int maxLength = 0;
        for (String content : contents) {
            if (content.length() > maxLength) {
                maxLength = content.length();
            }
        }
        return maxLength;
    }

    private static void printHorizontalBorder(int length) {
        System.out.print("-");
        for (int i = 0; i < length + 3; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    
    private static void printContentLine(String content, int maxLength) {
        System.out.print("| " + content);
        for (int i = 0; i < maxLength - content.length(); i++) {
            System.out.print(" ");
        }
        System.out.println(" |");
    }
    
    
    public void printCheckInType() {
        if(checkInType == "counter") {
            System.out.println("Please proceed to check-in counter " + counter + " to finalize your check-in.");
        } else {
            System.out.println("Please proceed to the nearest self-service kiosk to finalize your check-in.");
        }
    }
    
    public void printIssueType() {
        if(issueType == "print") {
            System.out.println("\nBoarding passes will be printed for you.");
        } else {
            System.out.println("\nBoarding passes will be available on mobile.");
        }
    }
    
    public void postCheckInActions() {
        while (true) {
            System.out.println();
            System.out.println("-------------------------------");
            System.out.println("| Select an option:           |");
            System.out.println("-------------------------------");
            System.out.println("| 1. Back to Main Menu        |");
            System.out.println("| 2. Exit                     |");
            System.out.println("-------------------------------");
            System.out.print("Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    return; // Return to Main Menu (breaks out of this method)
                case 2:
                    System.out.println("\nExiting AeroCheck System. Have a nice day!");
                    System.exit(0); // Exit the program
                default:
                    System.out.println("\nInvalid option. Please try again.");
            }
        }
    }
    
    public static String generateRandomFlightNo() {
        int number = random.nextInt(9000) + 1000; // Generates a number between 1000 and 9999
        return "PN" + number;
    }

    public static String generateRandomCountry() {
        String[] countries = {"USA", "UK", "Germany", "France", "Japan", "South Korea", "Mexico", "South Africa", "Australia", "Canada", "India", "Brazil", "China"};
        return countries[random.nextInt(countries.length)];
    }

    public static String generateRandomTime() {
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);
        return String.format("%02d%02d", hour, minute); // Returns time in HHMM format
    }

    public static String generateRandomSeatNumber() {
        int row = random.nextInt(30) + 1; // Generates a number between 1 and 30 for row
        char seat = (char) (random.nextInt(6) + 'A'); // Generates a character between 'A' and 'F' for seat
        return row + String.valueOf(seat);
    }
    
    public static String generateRandomConfirmationID() {
        StringBuilder confirmationID = new StringBuilder(6);
        for (int i = 0; i < 6; i++) { // Generates 6 random letters
            char randomLetter = (char) (random.nextInt(26) + 'A');
            confirmationID.append(randomLetter);
        }
        return confirmationID.toString();
    }
    
    public static String generateRandomBaggageTag() {
        StringBuilder baggageTag = new StringBuilder(6);
        for (int i = 0; i < 2; i++) { // Generates 2 random letters
            char randomLetter = (char) (random.nextInt(26) + 'A');
            baggageTag.append(randomLetter);
        }
        int number1 = random.nextInt(9000) + 1000; // Generates a number between 1000 and 9999
        int number2 = random.nextInt(900000) + 100000; // Generates a number between 1000 and 9999
        return number1 + baggageTag.toString() + "-" + number2;
    }
}
