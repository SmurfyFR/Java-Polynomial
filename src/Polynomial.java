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
                System.out.println("Not impl.");
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
            this.solutions.add(new Solution((float) (-this.coefficients[0]) / (float) (this.coefficients[1])));
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
                    (float) (-this.coefficients[1]) / (float) (2 * this.coefficients[2])
            ));
        } else if(discriminant > 0) {
            // first solution
            this.solutions.add(new Solution(
                    (float) (-this.coefficients[1] + Math.sqrt(discriminant)) / (float) (2 * this.coefficients[2])
            ));

            // second solution
            this.solutions.add(new Solution(
                    (float) (-this.coefficients[1] - Math.sqrt(discriminant)) / (float) (2 * this.coefficients[2])
            ));
        } else {
            // first solution
            this.solutions.add(new Solution(
                    (float) (-this.coefficients[1]) / (float) (2 * this.coefficients[2]),
                    (float) (Math.sqrt(Math.abs(discriminant))) / (float) (2 * this.coefficients[2])
            ));

            // second solution
            this.solutions.add(new Solution(
                    (float) (-this.coefficients[1]) / (float) (2 * this.coefficients[2]),
                    (float) - (Math.sqrt(Math.abs(discriminant))) / (float) (2 * this.coefficients[2])
            ));
        }

        return this.solutions;
    }

    /**
     * Fills solutions for the cubic equation into this.solutions ArrayList
     */
    protected List<Solution> processCubicRoots()  {
        // Note : a at index 3, b at index 2, c at index 1, d at index 0
        double discriminant = 0;
        // 18abcd - 4 * b^3 * d + b^2 * c^2 - 4 * a * c^3 - 27 * a^2 * d^2
        //discriminant = (18 * this.coefficients[3] * this.coefficients[2] * this.coefficients[1] * this.coefficients[0])
                //- (4 * Math.pow(this.coefficients[2], 3) + this.coefficients[0])
                //+ (Math.pow(this.coefficients[2], 2) * Math.pow(this.coefficients[1], 2))
                //- (4 * this.coefficients[3] * Math.pow(this.coefficients[1], 3))
                //- (27 * Math.pow(this.coefficients[3], 2) * Math.pow(this.coefficients[0], 2));

        //if(discriminant == 0) {
            // One solution uniq
        //} else if(discriminant > 0) {
            // 3 solutions
        //} else {
            // Complex
        //}

        return this.solutions;
    }
}

