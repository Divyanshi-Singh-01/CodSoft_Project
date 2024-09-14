import java.util.Random;
import java.util.Scanner;

public class guessinggame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxAttempts = 5;
        int score = 0;
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int generatedNumber = random.nextInt(100) + 1; // Generate random number between 1 and 100
            int attemptsLeft = maxAttempts;
            boolean correctGuess = false;

            System.out.println("\nI have generated a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            // Loop for the limited number of attempts
            while (attemptsLeft > 0 && !correctGuess) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                // Difference between the guess and the generated number
                int difference = Math.abs(generatedNumber - userGuess);

                if (userGuess == generatedNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    correctGuess = true;
                    score++; // Increase score if the user guesses correctly
                } else {
                    // Provide detailed feedback based on how far off the guess is
                    if (userGuess > generatedNumber) {
                        if (difference > 20) {
                            System.out.println("Too high!");
                        } else {
                            System.out.println("Little high!");
                        }
                    } else {
                        if (difference > 20) {
                            System.out.println("Too low!");
                        } else {
                            System.out.println("Little low!");
                        }
                    }
                }

                attemptsLeft--;

                if (!correctGuess && attemptsLeft > 0) {
                    System.out.println("You have " + attemptsLeft + " attempts left.");
                }
            }

            if (!correctGuess) {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + generatedNumber + ".");
            }

            // Ask if the user wants to play another round
            System.out.print("Would you like to play another round? (yes/no): ");
            scanner.nextLine(); // Consume the newline
            String response = scanner.nextLine();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("\nGame Over. Your final score is: " + score);
        scanner.close();
    }
}
