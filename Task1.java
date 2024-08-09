import java.util.Scanner;

public class Task1{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int chances = 8;
        int finals = 0;
        boolean playAgain = true;

        System.out.println("Welcome buddy!");
        System.out.println("Hey buddy, you have " + chances + " chances to win the game.");

        while (playAgain) {
            int rand = getRandN(1, 100);
            boolean guess = false;

            for (int i = 0; i < chances; i++) {
                System.out.println("Chance " + (i + 1) + ": Enter your guess");
                int user = sc.nextInt();

                if (user == rand) {
                    guess = true;
                    finals += 1;
                    System.out.println("You won it!");
                    break;
                } else if (user > rand) {
                    System.out.println("Too high");
                } else {
                    System.out.println("Too low");
                }
            }

            if (!guess) {
                System.out.println("Sorry buddy, you lost your chances. The number was " + rand);
            }

            System.out.println("Do you want to play again (y/n)?");
            String pa = sc.next();
            playAgain = pa.equalsIgnoreCase("y");
        }

        System.out.println("That's it buddy, hope you enjoyed it.");
        System.out.println("Here is your score: " + finals);
    }

    public static int getRandN(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
