/**
 * Main class for the Polynomial Project
 * @author Team A
 */

import java.util.*;

public class Main {
    public static final int ASCII_A = 97;

    public static void main(String[] args) {

        MathWrapper.useJavaMath(false);

        Scanner sc = new Scanner(System.in);
        int degree = 1;

        // Let the user decide which degree his polynome is
        System.out.println("Please select the degree of your polynome [1/2/3] :");
        System.out.println("1. ax + b");
        System.out.println("2. ax^2 + bx + c");
        System.out.println("3. ax^3 + bx^2 + cx + d");
        do {
            System.out.print("> ");
            while (!sc.hasNextInt()) sc.next(); // Prevent user from providing invalid input (ie : not a number)
            degree = sc.nextInt();
        } while(degree <= 0 || degree > Polynomial.MAX_SUPPORTED_DEGREE); // Degree should be supported

        int[] coefficients = new int[degree + 1]; // We need an extra place for the trailing coefficient
        System.out.println("Please provide coefficients of your polynome :");
        for (int i = degree; i >= 0; i--) {
            // We cast an integer into a char using ASCII table
            // For instance, if degree is 3 and i is 2, ASCII_A + (3 - 2) = 98, which is b
            System.out.print("Coefficient " + (char) (ASCII_A + (degree - i)) + " : ");
            while (!sc.hasNextInt()) sc.next(); // We need an integer value here
            coefficients[i] = sc.nextInt();
        }

	    Polynomial polynome = new Polynomial(coefficients);

        System.out.print("Equation : ");
        polynome.print();
        System.out.println(" = 0");

        /**
         * With Java.util.Math
         */
        System.out.println("[INFO] Using Math library");
        MathWrapper.useJavaMath(true);
        List<Solution> solutionsWithJavaMath;
        solutionsWithJavaMath = polynome.getRoots();

        if(polynome.hasInfiniteSolutions()) {
            System.out.println("Every number is a solution ...");
        } else {
            System.out.println("Found " + solutionsWithJavaMath.size() + " solutions.");

            int solutionIndex = 0;
            for (Solution item : solutionsWithJavaMath) {
                System.out.println("x" + solutionIndex + " = " + item.getStr());
                solutionIndex++;
            }
        }

        /**
         * Without Java.util.Math
         */
        System.out.println("[INFO] Using own Math implementation");
        MathWrapper.useJavaMath(false);
        List<Solution> solutionsWithoutJavaMath;
        solutionsWithoutJavaMath = polynome.getRoots();

        if(polynome.hasInfiniteSolutions()) {
            System.out.println("Every number is a solution ...");
        } else {
            System.out.println("Found " + solutionsWithoutJavaMath.size() + " solutions.");

            int solutionIndex = 0;
            for (Solution item : solutionsWithoutJavaMath) {
                System.out.println("x" + solutionIndex + " = " + item.getStr());
                solutionIndex++;
            }
        }
    }
}
