package HangmanGame;

import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    public static void main(String[] args) {
        System.out.println("Welcome to Hangman Game!");
        Scanner scanner = new Scanner(System.in);
        /**
         * Allow the player to choose a word category from a predefined list such as Animals,Fruits,
         * Countries, Movies, Sports
         */

        while (true) {
            System.out.println("Please choose a word category from the following list:");

            System.out.println("\n1. Animals");
            System.out.println("2. Fruits");
            System.out.println("3. Countries");
            System.out.println("4. Movies");
            System.out.println("5. Sports");

            System.out.print("\nEnter your choice: ");
            // Write Java code to read the player's choice from the console.
            int categoryChoice = scanner.nextInt();
            scanner.nextLine();

            String[] chosenWordArray;
            String chosenCategory;

            /**
             * Use GitHub CoPilot to generate an array of secret words for the game. Include at
             * least 10 example words in the array.
             */
            switch (categoryChoice) {
                case 1:
                    chosenCategory = "Animals";
                    chosenWordArray = new String[] {"cat", "dog", "elephant", "giraffe", "lion",
                            "tiger", "zebra", "monkey", "panda", "bear"};
                    break;
                case 2:
                    chosenCategory = "Fruits";
                    chosenWordArray = new String[] {"apple", "banana", "cherry", "durian", "grape",
                            "lemon", "orange", "peach", "pear", "watermelon"};
                    break;
                case 3:
                    chosenCategory = "Countries";
                    chosenWordArray = new String[] {"australia", "brazil", "canada", "denmark",
                            "egypt", "france", "germany", "hungary", "india", "japan"};
                    break;
                case 4:
                    chosenCategory = "Movies";
                    chosenWordArray = new String[] {"inception", "avatar", "starwars", "jumanji",
                            "matrix", "up", "titanic", "interstellar", "parasite", "joker"};
                    break;
                case 5:
                    chosenCategory = "Sports";
                    chosenWordArray = new String[] {"football", "basketball", "tennis", "badminton",
                            "volleyball", "baseball", "golf", "hockey", "boxing", "rugby"};
                    break;
                default:
                    System.out.println("Invalid choice. Exiting the game.");
                    return;
            }

            /** Based on the chosen category, randomly select a word for the player to guess */
            Random random = new Random();

            /**
             * Implement a function to randomly select a secret word from the array at the start of
             * each game.
             */
            String secretWord = getRandomWord(chosenWordArray, random);
            int attemptsRemaining = 6;
            boolean[] guessedLetters = new boolean[secretWord.length()];

            System.out.println("You've selected '" + chosenCategory + "' Let's begin!");
            System.out.println("Word to guess: " + displayWord(secretWord, guessedLetters));

            // Game loop
            while (attemptsRemaining > 0) {
                System.out.print("Enter your guess (a letter): ");
                char guess = scanner.nextLine().toLowerCase().charAt(0);

                if (!Character.isLetter(guess)) {
                    System.out.println("Invalid input. Please enter a letter.");
                    continue;
                }
                if (hasGuessed(secretWord, guessedLetters, guess)) {
                    System.out.println("You've already guessed this letter.");
                } else if (checkGuess(secretWord, guessedLetters, guess)) {
                    System.out.println("Correct guess!");
                    System.out.println("Word to guess: " + displayWord(secretWord, guessedLetters));
                } else {
                    attemptsRemaining--;
                    System.out.println("Incorrect guess. Attempts remaining: " + attemptsRemaining);
                }

                if (isWordGuessed(guessedLetters)) {
                    System.out.println("Congratulations! You've guessed the word '" + secretWord
                            + "' correctly!");
                    break;
                }
            }

            if (attemptsRemaining == 0) {
                System.out
                        .println("You've run out of attempts. The word was '" + secretWord + "'.");
            }

            // Ask if the player wants to play again
            System.out.print("Do you want to play again? (Y/N): ");
            String playAgain = scanner.nextLine();
            if (!playAgain.equalsIgnoreCase("Y")) {
                System.out.println("Thank you for playing Hangman!");
                break;
            }
        }
        scanner.close();
    }

    /**
     * Select a random word from the given array.
     *
     * @param wordArray An array of words to choose from.
     * @param random A random number generator.
     * @return The randomly selected word.
     */
    private static String getRandomWord(String[] wordArray, Random random) {
        int randomIndex = random.nextInt(wordArray.length);
        return wordArray[randomIndex];
    }

    /**
     * Check if a guessed letter is in the secret word.
     *
     * @param word The secret word to guess.
     * @param guessedLetters An array representing the guessed letters.
     * @param guess The letter guessed by the player.
     * @return True if the guess is correct, false otherwise.
     */
    private static boolean checkGuess(String word, boolean[] guessedLetters, char guess) {
        boolean correctGuess = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess) {
                guessedLetters[i] = true;
                correctGuess = true;
            }
        }
        return correctGuess;
    }

    /**
     * Check if a letter has already been guessed.
     *
     * @param word The secret word to guess.
     * @param guessedLetters An array representing the guessed letters.
     * @param guess The letter guessed by the player.
     * @return True if the letter has already been guessed, false otherwise.
     */
    private static boolean hasGuessed(String word, boolean[] guessedLetters, char guess) {
        for (int i = 0; i < word.length(); i++) {
            if (guessedLetters[i] && word.charAt(i) == guess) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the entire word has been guessed.
     *
     * @param guessedLetters An array representing the guessed letters.
     * @return True if the word has been completely guessed, false otherwise.
     */
    private static boolean isWordGuessed(boolean[] guessedLetters) {
        for (boolean guessed : guessedLetters) {
            if (!guessed) {
                return false;
            }
        }
        return true;
    }

    /**
     * Display the word with guessed letters and underscores.
     *
     * @param word The secret word to guess.
     * @param guessedLetters An array representing the guessed letters.
     * @return A string showing correctly guessed letters and underscores for unguessed letters.
     */
    private static String displayWord(String word, boolean[] guessedLetters) {
        StringBuilder display = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (guessedLetters[i]) {
                display.append(word.charAt(i));
            } else {
                display.append("_");
            }
            display.append(" ");
        }
        return display.toString();
    }

}

