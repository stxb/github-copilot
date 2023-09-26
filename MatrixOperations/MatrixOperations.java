package MatrixOperations;

import java.util.Random;
import java.util.Scanner;

public class MatrixOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Welcome to Matrix Operations Program!");


        /**
         * Implement a menu-driven program that allows users to choose between matrix addition and
         * multiplication
         */
        int choice;
        while (true) {
            System.out.println("\nPlease choose an operation from the following list:");
            System.out.println("\n1. Matrix Addition");
            System.out.println("2. Matrix Multiplication");
            System.out.println("3. Exit");

            System.out.print("\nEnter your choice (1/2/3): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    matrixAddition(scanner, random);
                    break;
                case 2:
                    matrixMultiplication(scanner, random);
                    break;
                case 3:
                    System.out.println("Thank you for using Matrix Operations Program!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

            // Ask if user wants to perform another operation
            System.out.print("\nDo you want to perform another operation? (Y/N): ");
            String doItAgain = scanner.nextLine();
            if (!doItAgain.equalsIgnoreCase("Y")) {
                break;
            }
        }

        scanner.close();
    }

    /**
     * Print the given matrix.
     *
     * @param matrix The matrix to be printed.
     */
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

    /**
     * Perform matrix addition.
     *
     * @param scanner A Scanner object to read input from the user.
     * @param random A Random object for generating random integers.
     */
    private static void matrixAddition(Scanner scanner, Random random) {
        /** Initialize and populate the two matrices with random integer values */
        System.out.println("\nEnter the dimensions of Matrix A: ");
        System.out.print("Rows: ");
        int rowsA = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Columns: ");
        int columnsA = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEnter the dimensions of Matrix B: ");
        System.out.print("Rows: ");
        int rowsB = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Columns: ");
        int columnsB = scanner.nextInt();
        scanner.nextLine();

        if (rowsA != rowsB || columnsA != columnsB) {
            System.out.println(
                    "\nMatrix addition is not possible. Matrices must have the same dimensions.");
            return;
        }

        int[][] matrixA = new int[rowsA][columnsA];
        int[][] matrixB = new int[rowsB][columnsB];

        System.out.println("\nMatrix A: ");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsA; j++) {
                matrixA[i][j] = random.nextInt(10);
                System.out.print(matrixA[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nMatrix B: ");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < columnsB; j++) {
                matrixB[i][j] = random.nextInt(10);
                System.out.print(matrixB[i][j] + " ");
            }
            System.out.println();
        }

        int[][] resultMatrix = addMatrices(matrixA, matrixB);
        System.out.println("\nResultant Matrix (Matrix A + Matrix B):");
        printMatrix(resultMatrix);

    }

    /**
     * Add two matrices.
     *
     * @param matrixA The first matrix.
     * @param matrixB The second matrix.
     * @return The result of matrix addition.
     */
    private static int[][] addMatrices(int[][] matrixA, int[][] matrixB) {
        int rows = matrixA.length;
        int columns = matrixA[0].length;
        int[][] resultMatrix = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                resultMatrix[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }

        return resultMatrix;
    }

    /**
     * Perform matrix multiplication.
     *
     * @param scanner A Scanner object to read input from the user.
     * @param random A Random object for generating random integers.
     */
    private static void matrixMultiplication(Scanner scanner, Random random) {
        /** Initialize and populate the two matrices with random integer values */
        System.out.println("\nEnter the dimensions of Matrix A: ");
        System.out.print("Rows: ");
        int rowsA = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Columns: ");
        int columnsA = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nEnter the dimensions of Matrix B: ");
        System.out.print("Rows: ");
        int rowsB = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Columns: ");
        int columnsB = scanner.nextInt();
        scanner.nextLine();

        if (columnsA != rowsB) {
            System.out.println(
                    "\nMatrix multiplication is not possible. Number of columns in Matrix A must be equal to the number of rows in Matrix B.");
            return;
        }

        int[][] matrixA = new int[rowsA][columnsA];
        int[][] matrixB = new int[rowsB][columnsB];

        System.out.println("\nMatrix A: ");
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsA; j++) {
                matrixA[i][j] = random.nextInt(10);
                System.out.print(matrixA[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\nMatrix B: ");
        for (int i = 0; i < rowsB; i++) {
            for (int j = 0; j < columnsB; j++) {
                matrixB[i][j] = random.nextInt(10);
                System.out.print(matrixB[i][j] + " ");
            }
            System.out.println();
        }

        int[][] resultMatrix = multiplyMatrices(matrixA, matrixB);
        System.out.println("\nResultant Matrix (Matrix A * Matrix B):");
        printMatrix(resultMatrix);

    }

    /**
     * Multiply two matrices.
     *
     * @param matrixA The first matrix.
     * @param matrixB The second matrix.
     * @return The result of matrix multiplication.
     */
    private static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) {
        int rowsA = matrixA.length;
        int columnsA = matrixA[0].length;
        int columnsB = matrixB[0].length;
        int[][] resultMatrix = new int[rowsA][columnsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < columnsB; j++) {
                for (int k = 0; k < columnsA; k++) {
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return resultMatrix;
    }
}
