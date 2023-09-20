package FuelCostCalculator;

import java.util.Scanner;

public class FuelCostCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String fuelType = getFuelType(scanner);
            double liters = getLiters(scanner);
            double pricePerLiter = getPricePerLiter(fuelType);
            double purchaseAmount = calculatePurchaseAmount(liters, pricePerLiter);
            double vat = calculateVAT(purchaseAmount);

            displayReceipt(fuelType, pricePerLiter, purchaseAmount, vat);

            System.out.print("Do you want to calculate another fuel cost? (Y/N): ");
            String continueInput = scanner.next();

            if (!continueInput.equalsIgnoreCase("Y")) {
                System.out.println("Thank you for using Fuel Cost Calculator!");
                break;
            }
        }

        scanner.close();
    }

    /**
     * Prompt the user to enter the fuel type (unleaded, diesel, or premium) and return it as a
     * lowercase string.
     * 
     * @param input The Scanner object for user input.
     * @return The fuel type as a lowercase string.
     */
    public static String getFuelType(Scanner input) {
        String fuelType;
        while (true) {
            System.out.print("Enter the fuel type (unleaded, diesel, or premium): ");
            fuelType = input.next().toLowerCase();
            if (isValidFuelType(fuelType)) {
                return fuelType;
            } else {
                System.out.println("Invalid fuel type. Please enter unleaded, diesel, or premium.");
            }
        }
    }

    /**
     * Check if the provided fuel type is valid (unleaded, diesel, or premium).
     * 
     * @param fuelType The fuel type to validate.
     * @return True if the fuel type is valid, false otherwise.
     */
    public static boolean isValidFuelType(String fuelType) {
        return fuelType.equals("unleaded") || fuelType.equals("diesel")
                || fuelType.equals("premium");
    }

    /**
     * Prompt the user to enter the number of liters and return it as a double.
     * 
     * @param input The Scanner object for user input.
     * @return The number of liters as a double.
     */
    public static double getLiters(Scanner input) {
        double liters;
        while (true) {
            System.out.print("Enter the number of liters: ");
            if (input.hasNextDouble()) {
                liters = input.nextDouble();
                if (liters > 0) {
                    return liters;
                } else {
                    System.out.println("Liters must be a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                input.next();
            }
        }
    }

    /**
     * Get the price per liter based on the provided fuel type.
     * 
     * @param fuelType The type of fuel (unleaded, diesel, or premium).
     * @return The price per liter for the specified fuel type.
     */
    public static double getPricePerLiter(String fuelType) {
        switch (fuelType) {
            case "unleaded":
                return 44.00;
            case "diesel":
                return 38.00;
            case "premium":
                return 50.00;
            default:
                return 0.00;
        }
    }

    /**
     * Calculate the total purchase amount based on the number of liters and price per liter.
     * 
     * @param liters The number of liters purchased.
     * @param pricePerLiter The price per liter.
     * @return The total purchase amount.
     */
    public static double calculatePurchaseAmount(double liters, double pricePerLiter) {
        return liters * pricePerLiter;
    }

    /**
     * Calculate the Value Added Tax (VAT) amount, which is 12% of the purchase amount.
     * 
     * @param purchaseAmount The total purchase amount.
     * @return The VAT amount.
     */
    public static double calculateVAT(double purchaseAmount) {
        return 0.12 * purchaseAmount;
    }

    /**
     * Display a receipt with purchase details, including fuel type, price per liter, purchase
     * amount, VAT, and total amount.
     * 
     * @param fuelType The type of fuel (unleaded, diesel, or premium).
     * @param pricePerLiter The price per liter.
     * @param purchaseAmount The total purchase amount.
     * @param vat The Value Added Tax (VAT) amount.
     */
    public static void displayReceipt(String fuelType, double pricePerLiter, double purchaseAmount,
            double vat) {
        System.out.println("\nReceipt:");
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Price Per Liter: " + String.format("%.2f", pricePerLiter));
        System.out.println("Purchase Amount: " + String.format("%.2f", purchaseAmount));
        System.out.println("VAT (12%): " + String.format("%.2f", vat));
        System.out.println("Total Amount: " + String.format("%.2f", (purchaseAmount + vat)) + "\n");
    }
}
