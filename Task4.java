import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Task4 {

    private static final int QUIZ_TIME_LIMIT_SECONDS = 30; // Time limit for the quiz in seconds

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        long startTime = System.currentTimeMillis();

        System.out.println("Welcome to the Quiz!");
        System.out.println("You have " + QUIZ_TIME_LIMIT_SECONDS + " seconds to complete the quiz.");

        // Question 1
        System.out.println("Question 1: What is the capital of France?");
        System.out.println("1. Paris");
        System.out.println("2. Berlin");
        System.out.println("3. Madrid");
        System.out.print("Enter your choice (1-3): ");
        int answer1 = scanner.nextInt();
        if (answer1 == 1) {
            score++;
        }

        // Question 2
        System.out.println("Question 2: What is 5 + 3?");
        System.out.println("1. 6");
        System.out.println("2. 8");
        System.out.println("3. 10");
        System.out.print("Enter your choice (1-3): ");
        int answer2 = scanner.nextInt();
        if (answer2 == 2) {
            score++;
        }

        // Question 3
        System.out.println("Question 3: Which planet is known as the Red Planet?");
        System.out.println("1. Earth");
        System.out.println("2. Mars");
        System.out.println("3. Jupiter");
        System.out.print("Enter your choice (1-3): ");
        int answer3 = scanner.nextInt();
        if (answer3 == 2) {
            score++;
        }

        // Check if time is up
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        if (timeTaken > QUIZ_TIME_LIMIT_SECONDS * 1000) {
            System.out.println("Time's up!");
        } else {
            System.out.println("You completed the quiz in " + TimeUnit.MILLISECONDS.toSeconds(timeTaken) + " seconds.");
        }

        // Display the score
        System.out.println("Your score: " + score + " out of 3");

        scanner.close();
    }
}
