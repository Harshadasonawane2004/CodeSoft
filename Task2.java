import java.util.Scanner;

public class Task2
{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Student Grade Calculator");
        System.out.println("Enter the number of subjects:");
        int nS = sc.nextInt();
        int total = 0;

        for (int i = 1; i <= nS; i++) {
            System.out.println("Enter marks obtained in subject " + i + ": ");
            int marks = sc.nextInt();
            total += marks;
        }

        double averP = (double) total / nS;
        char grade;

        if (averP > 90) {
            grade = 'O';
        } else if (averP > 80) {
            grade = 'A';
        } else if (averP > 70) {
            grade = 'B';
        } else if (averP > 60) {
            grade = 'C';
        } else if (averP > 50) {
            grade = 'D';
        } else if (averP > 40) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        System.out.println("Total marks scored is " + total);
        System.out.println("Average percentage gained is " + averP + "%");
        System.out.println("Grade: " + grade);
    }
}
