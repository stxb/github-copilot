package MRTFareCalculator;

import java.util.Scanner;
import java.util.Map.Entry;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Custom exception class for handling invalid MRT station input.
 */
class InvalidMRTStationException extends Exception {
    public InvalidMRTStationException(String message) {
        super(message);
    }
}

class InvalidFarePaymentException extends Exception {
    public InvalidFarePaymentException(String message) {
        super(message);
    }
}


/**
 * Represents fare information for an MRT station, including fare amount and stop number.
 */
class FareInfo {
    private double fareAmount;
    private int stopNumber;

    /**
     * Constructs a new FareInfo instance with the specified fare amount and stop number.
     * 
     * @param fareAmount The fare amount.
     * @param stopNumber The stop number.
     */
    public FareInfo(double fareAmount, int stopNumber) {
        this.fareAmount = fareAmount;
        this.stopNumber = stopNumber;
    }

    public double getFareAmount() {
        return fareAmount;
    }

    public int getStopNumber() {
        return stopNumber;
    }
}

public class MRTFareCalculator {
    public static void main(String[] args) {
        /** Write request input from user last name, first name, destination, and fare amount */

        Scanner scanner = new Scanner(System.in);
        Map<String, FareInfo> fareMap = initializeFareMap();
        Map<String, Double> totalFares = initializeTotalFaresMap(fareMap);

        while (true) {
            try {
                System.out.println("Welcome to the MRT Fare Calculator!");
                System.out.println("Please enter the following information:");

                System.out.print("Enter Last Name: ");
                String lastName = scanner.nextLine();

                System.out.print("Enter First Name: ");
                String firstName = scanner.nextLine();

                System.out.print("Enter Destination: ");
                String destination = scanner.nextLine();

                /** Search for a partial match destination name */
                String matchingDestination = findPartialDestinationMatch(destination, fareMap);

                if (matchingDestination != null) {
                    destination = matchingDestination;
                } else {
                    throw new InvalidMRTStationException("Invalid destination.");
                }

                if (!fareMap.containsKey(destination)) {
                    throw new InvalidMRTStationException("Invalid destination.");
                }

                double fareAmount = fareMap.get(destination).getFareAmount();
                int stopNumber = fareMap.get(destination).getStopNumber();
                System.out.println("MRT fare amount to " + destination + " station from North Avenue is " + fareAmount);

                double payment;
                do {
                    System.out.print("Enter payment: ");
                    payment = scanner.nextDouble();
                    if (payment <= 0) {
                        throw new InvalidFarePaymentException("Invalid payment. Must be " + fareAmount + ".");
                    } else if (payment < fareAmount) {
                        System.out.println("Invalid payment. Must be " + fareAmount + ".");
                    }
                } while (payment < fareAmount);

                if (payment == fareAmount) {
                    System.out.println("Thank you for paying the exact amount.");
                } else {
                    double change = payment - fareAmount;
                    System.out.println("Thank you for paying. Your change is " + change);
                }

                savePassengerInfo(lastName, firstName, destination, fareAmount);
                double currentTotal = totalFares.get(destination) + fareAmount;
                totalFares.put(destination, currentTotal);

                System.out.println("Please remember you have to alight at STOP " + stopNumber + ".");
                System.out.println("---------------------------------------------------");

                System.out.println("LIST OF FARE PROFITS");
                printTotalFares(totalFares, fareMap);

                System.out.print("\nDo you want to continue[Y/N]? ");
                String continueInput = scanner.next();
                scanner.nextLine();

                if (!continueInput.equalsIgnoreCase("Y")) {
                    System.out.println(
                            "\nEntering \"Y\" or \"y\" will then repeat the entry. Selection \"N\" or \"n\" will terminate the application.");
                    break;
                }
            } catch (InvalidMRTStationException | InvalidFarePaymentException e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            }
        }
        scanner.close();
    }

    /**
     * Initialize the fare map with valid MRT stations, fares, and stop numbers.
     * 
     * @return A map containing fare information for each MRT station.
     */
    private static Map<String, FareInfo> initializeFareMap() {
        Map<String, FareInfo> fareMap = new HashMap<>();
        fareMap.put("Quezon Avenue and GMA Kamuning", new FareInfo(10.00, 1));
        fareMap.put("Cubao and Santolan", new FareInfo(11.00, 2));
        fareMap.put("Ortigas and Shaw Boulevard", new FareInfo(12.00, 3));
        fareMap.put("Boni Avenue and Guadalupe", new FareInfo(13.00, 4));
        fareMap.put("Buendia and Ayala", new FareInfo(14.00, 5));
        fareMap.put("Magallanes and Taft Avenue", new FareInfo(15.00, 6));
        return fareMap;
    }

    /**
     * Find a partial match for the station name within the MRT station names.
     * 
     * @param partialStationName The partial station name entered by the user.
     * @param fareMap The map containing fare information for each MRT station.
     * @return A matching MRT station name or null if no match is found.
     */
    private static String findPartialDestinationMatch(String partialStationName, Map<String, FareInfo> fareMap) {
        for (String fullDestination : fareMap.keySet()) {
            if (fullDestination.toLowerCase().contains(partialStationName.toLowerCase())) {
                return fullDestination;
            }
        }
        return null;
    }

    /**
     * Initialize the fare map with valid MRT stations and fares.
     * 
     * @param fareMap The map containing fare information for each MRT station.
     * @return A map containing total fares initialized to zero for each station.
     */
    private static Map<String, Double> initializeTotalFaresMap(Map<String, FareInfo> fareMap) {
        Map<String, Double> totalFares = new HashMap<>();
        for (String destination : fareMap.keySet()) {
            totalFares.put(destination, 0.00);
        }
        return totalFares;
    }

    /**
     * Save passenger information to a text file with a filename based on the destination.
     * 
     * @param lastName The last name of the passenger.
     * @param firstName The first name of the passenger.
     * @param destination The destination station name.
     * @param fareAmount The fare amount paid by the passenger.
     */
    private static void savePassengerInfo(String lastName, String firstName, String destination, double fareAmount) {
        String fileName = "Stop " + (getStopNumber(destination)) + "-" + destination.replace(" ", "_") + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Last Name: " + lastName + "\n");
            writer.write("First Name: " + firstName + "\n");
            writer.write("Destination: " + destination + "\n");
            writer.write("Fare Amount: " + fareAmount + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the stop number associated with the given destination station name.
     * 
     * @param destination The destination station name.
     * @return The stop number corresponding to the destination.
     */
    private static int getStopNumber(String destination) {
        int stopNumber = 0;
        for (Entry<String, FareInfo> entry : initializeFareMap().entrySet()) {
            stopNumber++;
            if (entry.getKey().toLowerCase().contains(destination.toLowerCase())) {
                return stopNumber;
            }
        }
        return stopNumber;
    }

    /**
     * Print the total fares for all MRT stops, sorted by stop number in ascending order.
     * 
     * @param totalFares The map containing total fares for each destination.
     * @param fareMap The map containing fare information for MRT stations.
     */
    private static void printTotalFares(Map<String, Double> totalFares, Map<String, FareInfo> fareMap) {
        List<String> destinations = new ArrayList<>(totalFares.keySet());
        destinations.sort(Comparator.comparingInt(dest -> fareMap.get(dest).getStopNumber()));

        for (String destination : destinations) {
            double totalFare = totalFares.get(destination);
            int stopNumber = fareMap.get(destination).getStopNumber();

            System.out.println("TOTAL FARE FOR ALL STOP " + stopNumber + ": " + totalFare);
        }
    }

}
