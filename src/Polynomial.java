/**
 * Polynomial class for Polynomial project
 */
import java.util.*;

public class Polynomial {
    /**
     * The class only support polynomes of degree 3 or less, but this could be easily changed in the future
     */
    public static final int MAX_SUPPORTED_DEGREE = 3;

    /**
     * Array of Int : contains all coefficients.
     * Key 0 contains last coefficients
     */
    private int[] coefficients;

    /**
     * Contains all computed solutions
     */
    private List<Solution> solutions = new ArrayList<Solution>();

    private boolean hasInfiniteSolutions = false;

    Polynomial(int[] coefficients) {
        this.coefficients = coefficients;
    }

    public boolean hasInfiniteSolutions() {
        return this.hasInfiniteSolutions;
    }

    /**
     * Returns the degree of the current Polynomial instance based on the number of coefficients
     */
    public int getDegree() {
        return this.coefficients.length - 1;
    }

    /**
     * Prints the polynome to a human understandable format (like 2 * x + 5)
     */
    public void print() {
        if(this.getDegree() >= 3) {
            System.out.printf("(%d * x^3) + ", coefficients[3]);
        }
        if(this.getDegree() >= 2) {
            System.out.printf("(%d * x^2) + ", coefficients[2]);
        }
        if(this.getDegree() >= 1) {
            System.out.printf("(%d * x) + %d", coefficients[1], coefficients[0]);
        }
    }

    /**
     * Fills solutions based on the degree of the Polynomial
     */
    public List<Solution> getRoots() {
        this.solutions.clear();
        switch(this.getDegree()) {
            case 3: // Cubic
                this.processCubicRoots();
                break;
            case 2: // Quadratic
                this.processQuadraticRoots();
                break;
            case 1: // Linear
                this.processLinearRoots();
                break;
        }

        return this.solutions;
    }

    /**
     * Fills solutions of the linear equation into this.solutions ArrayList
     */
    protected List<Solution> processLinearRoots() {
        if(this.coefficients[1] == 0) { // Special case when a = 0
            if(this.coefficients[0] == 0) { // Every number is a solution when b == 0
                this.hasInfiniteSolutions = true;
            }
            // Note that if b != 0, there is NO solutions
        } else {
            // x1 = -b / a
            this.solutions.add(new Solution((double) (-this.coefficients[0]) / (double) (this.coefficients[1])));
        }

        return this.solutions;
    }

    /**
     * Fills solutions for the quadratic equation into this.solutions ArrayList
     */
    protected List<Solution> processQuadraticRoots() {
        // Note : a at index 2, b at index 1, c at index 0
        double discriminant = 0;
        // d = b^2 - 4ac
        discriminant = Math.pow(this.coefficients[1], 2) - 4 * this.coefficients[2] * this.coefficients[0];

        if(discriminant == 0) {
            this.solutions.add(new Solution(
                    (double) (-this.coefficients[1]) / (double) (2 * this.coefficients[2])
            ));
        } else if(discriminant > 0) {
            // first solution
            this.solutions.add(new Solution(
                    (double) (-this.coefficients[1] + Math.sqrt(discriminant)) / (double) (2 * this.coefficients[2])
            ));

            // second solution
            this.solutions.add(new Solution(
                    (double) (-this.coefficients[1] - Math.sqrt(discriminant)) / (double) (2 * this.coefficients[2])
            ));
        } else {
            // first solution
            this.solutions.add(new Solution(
                    (double) (-this.coefficients[1]) / (double) (2 * this.coefficients[2]),
                    (double) (Math.sqrt(Math.abs(discriminant))) / (double) (2 * this.coefficients[2])
            ));

            // second solution
            this.solutions.add(new Solution(
                    (double) (-this.coefficients[1]) / (double) (2 * this.coefficients[2]),
                    (double) - (Math.sqrt(Math.abs(discriminant))) / (double) (2 * this.coefficients[2])
            ));
        }

        return this.solutions;
    }

    /**
     * Fills solutions for the cubic equation into this.solutions ArrayList
     */
    protected List<Solution> processCubicRoots()  {
        double norm = this.coefficients[3]; // norm = a
        double a = this.coefficients[2] / norm; // a = b / norm
        double b = this.coefficients[1] / norm; // b = c / norm
        double c = this.coefficients[0] / norm; // c = d / norm

        double a_over_3 = a / 3.0;
        double Q = (3*b - a*a) / 9.0;
        double Q_CUBE = Q*Q*Q;
        double R = (9*a*b - 27*c - 2*a*a*a) / 54.0;
        double R_SQR = R*R;
        double D = Q_CUBE + R_SQR;

        if (D < 0.0) {
            // Three unequal real roots.
            double theta = Math.acos (R / Math.sqrt(-Q_CUBE));
            double SQRT_Q = Math.sqrt(-Q);
            this.solutions.add(new Solution(2.0 * SQRT_Q * Math.cos (theta/3.0) - a_over_3));
            this.solutions.add(new Solution(2.0 * SQRT_Q * Math.cos ((theta+(2*Math.PI))/3.0) - a_over_3));
            this.solutions.add(new Solution(2.0 * SQRT_Q * Math.cos ((theta+(4*Math.PI))/3.0) - a_over_3));
        } else if (D > 0.0) {
            // One real solution
            double SQRT_D = Math.sqrt (D);
            double S = Math.cbrt (R + SQRT_D);
            double T = Math.cbrt (R - SQRT_D);
            this.solutions.add(new Solution((S + T) - a_over_3));
        } else {
            // Three real roots, at least two equal.
            double CBRT_R = Math.cbrt (R);
            this.solutions.add(new Solution(2*CBRT_R - a_over_3));
            this.solutions.add(new Solution(CBRT_R - a_over_3));
        }

        return this.solutions;
    }
}

