import java.security.SecureRandom;
import java.util.Scanner;
import java.lang.Math;

public class Program1 {
    static double actualResult;
    static int problemType = 0;
    static int randVariable = 1;
    static boolean isDivision = false;
    static boolean isRandomType = false;
    private final static double Epsilon = 0.00001;
    static int countCorrect = 0;
    public static int getRandomVariable(SecureRandom rand){
        int bound;
        int num;
        switch(randVariable){
            case 1:
                bound = 9;
                break;
            case 2:
                bound = 19;
                break;
            case 3:
                bound = 29;
                break;
            case 4:
                bound = 39;
                break;
            default:
                return -1;
        }
        num = rand.nextInt(bound) + 1;
        return num;
    }
    public static void setProblemType(Scanner in){
        System.out.println("Choose a problem type. 1 for addition, 2 for multiplication, 3 for subtraction," +
                "4 for division, 5 for a random mixture.");
        problemType = in.nextInt();
    }
    public static int getRandomOptions(SecureRandom rand){
        int num = rand.nextInt(4) + 1;
        return num;
    }
    public static void printFeedback(int num, boolean isCorrect){
        if (isCorrect){
            switch (num){
                case 1:
                    System.out.println("Very good!");
                    break;
                case 2:
                    System.out.println("Excellent!");
                    break;
                case 3:
                    System.out.println("Nice work!");
                    break;
                case 4:
                    System.out.println("Keep up the good work!");
                    break;
                default:
                    return;
            }
        }
        else {
            switch (num){
                case 1:
                    System.out.println("No. Please try again.");
                    break;
                case 2:
                    System.out.println("Wrong. Try once more.");
                    break;
                case 3:
                    System.out.println("Don't give up!");
                    break;
                case 4:
                    System.out.println("No. Keep trying.");
                    break;
                default:
                    return;
            }
        }
    }
    public static double calculatePercent(int numCorrect) {
        return ((double)numCorrect / 10.00) * 100.00;
    }
    public static void scoreFeedback(int countCorrect){
        double percent = calculatePercent(countCorrect);
        System.out.println(percent);
        if (percent - 75.00 < Epsilon){
            System.out.println("Please ask your teacher for extra help.");
        }
        else {
            System.out.println("Congratulations, you are ready to go to the next level.");
        }
    }
    public static void setRandVariable(Scanner in){
        System.out.println("Please choose a level from 1-4.");
        int num = in.nextInt();
        if (num > 0 && num < 5){
            randVariable = num;
        }
        else {
            randVariable = -1;
        }
    }
    // right now I'm not calling the method anywhere
    public static void askProblemType(int num1, int num2, SecureRandom rand, int count){
        switch (problemType){
            case 1:
                System.out.println("How much is " + num1 + " plus " + num2 + " ?");
                actualResult = (double)num1 + (double)num2;
                break;
            case 2:
                System.out.println("How much is " + num1 + " times " + num2 + " ?");
                actualResult = (double)num1 * (double)num2;
                break;
            case 3:
                System.out.println("How much is " + num1 + " minus " + num2 + " ?");
                actualResult = (double)num1 - (double)num2;
                break;
            case 4:
                System.out.println("How much is " + num1 + " divided by " + num2 + " (to the second decimal place)?");
                actualResult = (double)num1 / (double)num2;
                isDivision = true;
                break;
            case 5:
                isRandomType = true;
                break;
            default:
                return;
        }
    }
    // I need to reset the level and problem type so that another user can use the program.
    public static void multiplicationQuiz(int num1, int num2, Scanner in, SecureRandom rand, int count) {
        SecureRandom rand2 = new SecureRandom();
        double userResult;
        //int randomVariable;
        // I have to do this bc strings are immutable, and I can't change a string in another method in Java;
        // it is a local instance of the string in memory
        //randomVariable = getRandomFeedback(rand2);
        if (count == 10) {
            return;
        }
        int count5 = 0;
        if (!isRandomType){
            askProblemType(num1, num2, rand, 0);
        }

        if (isRandomType) {
            if ((count5 != 10)) {
                problemType = getRandomOptions(rand);
                switch (problemType) {
                    case 1:
                        System.out.println("How much is " + num1 + " plus " + num2 + " ?");
                        actualResult = (double) num1 + (double) num2;
                        count5++;
                        break;
                    case 2:
                        System.out.println("How much is " + num1 + " times " + num2 + " ?");
                        actualResult = (double) num1 * (double) num2;
                        count5++;
                        break;
                    case 3:
                        System.out.println("How much is " + num1 + " minus " + num2 + " ?");
                        actualResult = (double) num1 - (double) num2;
                        count5++;
                        break;
                    case 4:
                        isDivision = true;
                        System.out.println("How much is " + num1 + " divided by " + num2 + " (to the second decimal place)?");
                        actualResult = (double) num1 / (double) num2;
                        count5++;
                        break;
                    default:
                        return;
                }
            }
        }
        userResult = in.nextFloat();
        if (isDivision){
            actualResult = Math.round(actualResult * 100.00)/ 100.00;
            userResult = Math.round(userResult * 100.00)/ 100.00;
        }

        if (Math.abs(userResult - actualResult) < Epsilon){
            //printFeedback(randomVariable, true);
            num1 = getRandomVariable(rand);
            num2 = getRandomVariable(rand);
            countCorrect++;
            multiplicationQuiz(num1, num2, in, rand, count + 1);
        }
        else {
            //toggle is not necessary because I do not want the student to repeat the question
            //printFeedback(randomVariable, false);
            num1 = getRandomVariable(rand);
            num2 = getRandomVariable(rand);
            multiplicationQuiz(num1, num2, in, rand, count + 1);
        }

    }
    public static void multiplicationQuizHelper(int num1, int num2, Scanner in, SecureRandom rand){
        multiplicationQuiz(num1, num2, in, rand, 0);
        scoreFeedback(countCorrect);
        System.out.println(countCorrect);
        countCorrect = 0;
    }
    public static void main(String[] arg){
        SecureRandom rand = new SecureRandom();
        Scanner in = new Scanner(System.in);
        int num1 = getRandomVariable(rand);
        int num2 = getRandomVariable(rand);
        char input = 'm';
        do {
            System.out.println("Press s to start and q to quit.");
            input = in.next().charAt(0);
            if (input == 'q'){
                return;
            }
            setProblemType(in);
            setRandVariable(in);
            multiplicationQuizHelper(num1, num2, in, rand);
            //multiplicationQuiz(num1, num2, in, rand, 0);
        } while (input == 's');

    }
}
