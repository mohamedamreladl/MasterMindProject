
import java.util.Random;
import java.util.Scanner;

public class Mastermind {
    private static final int CODE_LENGTH = 4;
    private static final int MAX_ATTEMPTS = 10;
    private static final String[] COLORS = {"red", "green", "blue", "yellow", "orange", "purple"};

    public static void main(String[] args) {
        String[] secretCode = generateSecretCode();
        Scanner scanner = new Scanner(System.in);
        int attempts = MAX_ATTEMPTS;

        System.out.println("Welcome to Mastermind!");
        System.out.println("Try to guess the secret code made up of " + CODE_LENGTH + " colors");
        System.out.println("Available colors are:");
        for (String color : COLORS) {
            System.out.println("- " + color);
        }
        System.out.println("You have " + attempts + " attempts");

        while (attempts > 0) {
            System.out.println("\nRemaining attempts: " + attempts);
            System.out.println("Enter your guess (" + CODE_LENGTH + " colors separated by spaces, or 'quit' to exit): ");

            String firstInput = scanner.next().toLowerCase();
            if (firstInput.equals("quit")) {
                System.out.println("Game ended by player.");
                break;
            }

            String[] guess = new String[CODE_LENGTH];
            guess[0] = firstInput;

            for (int i = 1; i < CODE_LENGTH; i++) {
                String input = scanner.next().toLowerCase();
                if (isValidColor(input)) {
                    guess[i] = input;
                } else {
                    System.out.println("Invalid color! Please enter one of the available colors");
                    i--;
                    continue;
                }
            }

            System.out.print("Your guess: ");
            for (String color : guess) {
                System.out.print(color + " ");
            }
            System.out.println();

            int exactMatches = 0;
            int partialMatches = 0;

            boolean[] secretUsed = new boolean[CODE_LENGTH];
            boolean[] guessUsed = new boolean[CODE_LENGTH];

            for (int i = 0; i < CODE_LENGTH; i++) {
                if (guess[i].equals(secretCode[i])) {
                    exactMatches++;
                    secretUsed[i] = true;
                    guessUsed[i] = true;
                }
            }

            for (int i = 0; i < CODE_LENGTH; i++) {
                if (!guessUsed[i]) {
                    for (int j = 0; j < CODE_LENGTH; j++) {
                        if (!secretUsed[j] && guess[i].equals(secretCode[j])) {
                            partialMatches++;
                            secretUsed[j] = true;
                            break;
                        }
                    }
                }
            }


            System.out.println("Exact matches: " + exactMatches);
            System.out.println("Partial matches: " + partialMatches);

            if (exactMatches == CODE_LENGTH) {
                System.out.println("\nCongratulations! You've won!");
                break;}
            attempts--; }

        if (attempts == 0) {
            System.out.println("\nGame Over! The secret code was:");
            for (String color : secretCode) {
                System.out.print(color + " ");}}

        scanner.close(); }

    private static String[] generateSecretCode() {
        Random random = new Random();
        String[] code = new String[CODE_LENGTH];
        for (int i = 0; i < CODE_LENGTH; i++) {
            code[i] = COLORS[random.nextInt(COLORS.length)];
        }
        return code; }

    private static boolean isValidColor(String color) {
        for (String validColor : COLORS) {
            if (validColor.equals(color)) {
                return true;
            }           }
        return false;
    }             }
