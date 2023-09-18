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

    public static boolean isValidFuelType(String fuelType) {
        return fuelType.equals("unleaded") || fuelType.equals("diesel") || fuelType.equals("premium");
    }

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

    public static double calculatePurchaseAmount(double liters, double pricePerLiter) {
        return liters * pricePerLiter;
    }

    public static double calculateVAT(double purchaseAmount) {
        return 0.12 * purchaseAmount;
    }

    public static void displayReceipt(String fuelType, double pricePerLiter, double purchaseAmount, double vat) {
        System.out.println("\nReceipt:");
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Price Per Liter: " + String.format("%.2f", pricePerLiter));
        System.out.println("Purchase Amount: " + String.format("%.2f", purchaseAmount));
        System.out.println("VAT (12%): " + String.format("%.2f", vat));
        System.out.println("Total Amount: " + String.format("%.2f", (purchaseAmount + vat)) + "\n");
    }
}
