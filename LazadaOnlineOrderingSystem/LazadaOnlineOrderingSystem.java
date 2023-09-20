package LazadaOnlineOrderingSystem;

import java.util.Scanner;

public class LazadaOnlineOrderingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Lazada Online Ordering System!");

        while (true) {
            /**
             * Create input scanner from user: Enter customer name / shipping address / select your
             * xiaomi phone model
             */
            System.out.println("Please enter the following information:");
            System.out.print("Enter Customer Name: ");
            String customerName = scanner.nextLine();

            System.out.print("Enter Shipping Address: ");
            String shippingAddress = scanner.nextLine();

            /** Display the menu of Xiaomi phone models with prices */
            displayXiaomiPhoneMenu();

            /** Select model choice */
            System.out.print("\nEnter Xiaomi Model (1-5): ");
            int modelChoice = scanner.nextInt();
            scanner.nextLine();
            /** Enter number of items */
            System.out.print("Enter number of items: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            boolean validCreditCard = false;
            while (!validCreditCard) {
                /** Enter credit card number and check if it valid or not */
                System.out.print("Enter Credit Card Number: ");
                String creditCardNumber = scanner.nextLine().replaceAll("\\s", "");

                if (!isValidCreditCardNumber(creditCardNumber) || !creditCardNumber.matches("\\d+")
                        || creditCardNumber.length() != 16) {
                    System.out.println("Invalid credit card number. Please try again.");
                } else {
                    validCreditCard = true;
                }
            }

            /** Calculate total cost */
            double itemPrice = getPriceByModelChoice(modelChoice);
            double grossAmount = calculateGrossAmount(itemPrice, quantity);
            double vatAmount = calculateVAT(grossAmount);
            double totalPrice = grossAmount + vatAmount;

            /**
             * Display transaction details and round grossAmount/vatAmount/totalPrice to 2 decimal
             * places
             */
            System.out.println("\nOrder Verified. Successful Transaction");
            System.out.println("Customer Name: " + customerName);
            System.out.println("Shipping Address: " + shippingAddress);
            System.out.println("Item Ordered: " + getXiaomiModelName(modelChoice));
            System.out.println("Quantity: " + quantity);
            System.out.println("Gross Amount: " + String.format("%.2f", grossAmount));
            System.out.println("VAT (12%): " + String.format("%.2f", vatAmount));
            System.out.println("Total Price: " + String.format("%.2f", totalPrice));

            /** Ask if the user wants to make another purchase or exit */
            System.out.print("\nDo you want to buy again? (Y/N): ");
            String buyAgain = scanner.nextLine();

            if (!buyAgain.equalsIgnoreCase("Y")) {
                System.out.println("Thank you for shopping with us!");
                break;
            }
        }

        scanner.close();
    }

    /**
     * Display the menu of Xiaomi phone models with their prices.
     */
    private static void displayXiaomiPhoneMenu() {
        System.out.println("\nSelect Your Xiaomi Phone:");
        System.out.println("    1 - Redmi 4A (4199.00)");
        System.out.println("    2 - Redmi 4X (6199.00)");
        System.out.println("    3 - Redmi Note 4X (7599.00)");
        System.out.println("    4 - Mi Max 2 (12590.00)");
        System.out.println("    5 - Mi Mix 2 (24700.00)");
    }

    /**
     * Check if a credit card number is valid using the Luhn algorithm.
     *
     * @param creditCardNumber The credit card number to validate.
     * @return true if the credit card number is valid, false otherwise.
     */
    private static boolean isValidCreditCardNumber(String creditCardNumber) {
        int s1 = 0;
        int s2 = 0;
        String reverse = new StringBuffer(creditCardNumber).reverse().toString();
        for (int i = 0; i < reverse.length(); i++) {
            int digit = Character.digit(reverse.charAt(i), 10);
            if (i % 2 == 0) {
                s1 += digit;
            } else {
                s2 += 2 * digit;
                if (digit >= 5) {
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }

    /**
     * Get the price of a Xiaomi phone model based on the user's choice.
     *
     * @param modelChoice The user's choice of Xiaomi phone model (1-5).
     * @return The price of the selected Xiaomi phone model.
     */
    private static double getPriceByModelChoice(int modelChoice) {
        switch (modelChoice) {
            case 1:
                return 4199.00;
            case 2:
                return 6199.00;
            case 3:
                return 7599.00;
            case 4:
                return 12590.00;
            case 5:
                return 24700.00;
            default:
                return 0.00;
        }
    }

    /**
     * Calculate the gross amount for a purchase.
     *
     * @param itemPrice The price of the selected Xiaomi phone model.
     * @param quantity The quantity of items to purchase.
     * @return The gross amount for the purchase.
     */
    private static double calculateGrossAmount(double itemPrice, int quantity) {
        return itemPrice * quantity;
    }

    /**
     * Calculate the VAT (Value Added Tax) for a purchase. Implement VAT calculation (12% of the
     * gross amount)
     *
     * @param grossAmount The gross amount for the purchase.
     * @return The VAT amount.
     */
    private static double calculateVAT(double grossAmount) {
        return 0.12 * grossAmount;
    }

    /**
     * Get the name of the Xiaomi model based on the user's choice.
     *
     * @param modelChoice The user's choice of Xiaomi phone model (1-5).
     * @return The name of the selected Xiaomi phone model.
     */
    private static String getXiaomiModelName(int modelChoice) {
        switch (modelChoice) {
            case 1:
                return "Xiaomi Redmi 4A";
            case 2:
                return "Xiaomi Redmi 4X";
            case 3:
                return "Xiaomi Redmi Note 4X";
            case 4:
                return "Xiaomi Mi Max 2";
            case 5:
                return "Xiaomi Mi Mix 2";
            default:
                return "Unknown Model";
        }
    }
}
