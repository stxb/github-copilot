package SalesDiscountChecker;

import java.util.Scanner;

public class SalesDiscountChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int validEntries = 0;
        int invalidEntries = 0;

        System.out.println("Welcome to the Sales Discount Checker!");

        while (true) {
            System.out.print("Enter Customer Name: ");
            /**
             * Remove customerName variable declarstion because it is not used
             */
            scanner.nextLine();

            System.out.print("Enter Sales Agent: ");
            String salesAgent = scanner.nextLine();

            System.out.print("Enter Item Name: ");
            String itemName = scanner.nextLine();

            System.out.print("Enter Sales Retail Price: ");
            double salesRetailPrice = scanner.nextDouble();

            System.out.print("Enter Target Sales Price: ");
            double targetSalesPrice = scanner.nextDouble();
            scanner.nextLine();

            /**
             * Calculate the sales discount percentage
             */
            double salesDiscount = ((salesRetailPrice - targetSalesPrice) / salesRetailPrice) * 100;

            System.out.println("\nChecking the sales discount for " + itemName);

            /**
             * Round salesDiscount to 2 decimal places
             */
            System.out.println("Sales Discount: " + String.format("%.2f", salesDiscount) + "%");

            /**
             * Check if the sales discount is greater than 12%
             */
            if (salesDiscount > 12) {
                System.out.println("\nDiscounted Sales Price is INVALID.");
                System.out.println(
                        "Sales Agent " + salesAgent + " is suspended for 6 months with all benefits put on hold.");
                invalidEntries++;
            } else {
                System.out.println("\nDiscounted Sales Price is VALID.");
                validEntries++;
            }

            /**
             * Display the total number of valid and invalid sales entries
             */
            System.out.println("Total Valid Entries: " + validEntries);
            System.out.println("Total Invalid Entries: " + invalidEntries);

            System.out.print("\nDo you want to continue (Y/N)? ");
            String continueInput = scanner.nextLine();

            if (!continueInput.equalsIgnoreCase("Y")) {
                System.out.println("\nProgram terminated. Thank you for using Sales Discount Checker!");
                break;
            }
        }

        scanner.close();
    }
}
