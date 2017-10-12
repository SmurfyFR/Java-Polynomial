public class MathWrapper {
    protected static boolean useJavaMath = false;

    /**
     * Do we need to use Java Math library or our own implementations ?
     */
    public static void useJavaMath(boolean use) {
        MathWrapper.useJavaMath = use;
    }

    /**
     * PI is defined here (same as Math.PI)
     * EPSILON is to fix the precision of our approximations
     */
    public static final double PI = 3.14159265358979323846;
    public static final double EPSILON = 0.000001;

    /**
     * Returns the base^(exponent) using recursivity (2^3 = 2 * 2^2 = 2 * 2 * 2 * 1)
     */
    public static double pow(double base, double exponent) {
        if(MathWrapper.useJavaMath) {
            return Math.pow(base, exponent);
        } else {
            if(exponent == 0.0) return 1.0;
            return base * pow(base, exponent - 1);
        }
    }

    /**
     * Returns an approach of sqrt(a)
     */
    public static double sqrt(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.sqrt(a);
        } else {
            if(a == 0.0) {
                return 0;
            } else if(a < 0.0) {
                a = -a;
            }

            double sqrt = a / 2;
            double tmp = sqrt;
            do {
                tmp = sqrt;
                sqrt = (tmp + (a/tmp)) / 2;
            } while(tmp - sqrt > MathWrapper.EPSILON);

            return sqrt;
        }
    }

    /**
     * Returns an approach of cubic root of a
     */
    public static double cbrt(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.cbrt(a);
        } else {
            double cbrt = a / 3;
            double tmp = cbrt;
            do {
                tmp = cbrt;
                cbrt = (2*tmp + (a/MathWrapper.pow(tmp, 2))) / 3;
            } while(tmp - cbrt > MathWrapper.EPSILON);

            return cbrt;
        }
    }

    /**
     * Returns absolute value of a
     */
    public static double abs(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.abs(a);
        } else {
            if(a >= 0.0) {
                return a;
            } else {
                return -a;
            }
        }
    }

    /**
     * Returns factorial a (recursivity)
     */
    public static int factorial(int a) {
        if(a == 0.0) {
            return 1;
        } else {
            return a * MathWrapper.factorial(a - 1);
        }
    }

    /**
     * Limited development of cos(a) at degree 8
     */
    public static double cos(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.cos(a);
        } else {
            double cos = 1;

            for(int i = 2; i < 10; i+=2) {
                if(i%4 == 2) {
                    cos -= (MathWrapper.pow(a, i) / MathWrapper.factorial(i));
                } else {
                    cos += (MathWrapper.pow(a, i) / MathWrapper.factorial(i));
                }
            }
            return cos;
        }
    }

    /**
     * Limited development of arccos(a) at degree 3
     */
    public static double acos(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.acos(a);
        } else {
            return MathWrapper.PI / 2 - a
                    - (MathWrapper.pow(a, 3) / 6)
                    - (3*MathWrapper.pow(a, 5) / 40)
                    - (5*MathWrapper.pow(a, 7) / 112);
        }
    }
}
