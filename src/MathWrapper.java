public class MathWrapper {
    protected static boolean useJavaMath = false;

    public static void useJavaMath(boolean use) {
        MathWrapper.useJavaMath = use;
    }

    public static final double PI = 3.14159265358979323846;

    public static double pow(double base, double exponent) {
        if(MathWrapper.useJavaMath) {
            return Math.pow(base, exponent);
        } else {
            if(exponent == 0.0) return 1.0;
            return base * pow(base, exponent - 1);
        }
    }

    public static double sqrt(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.sqrt(a);
        } else {
            return 1.0;
        }
    }

    public static double cbrt(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.cbrt(a);
        } else {
            return 1.0;
        }
    }

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

    public static double cos(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.cos(a);
        } else {
            return 1.0;
        }
    }

    public static double acos(double a) {
        if(MathWrapper.useJavaMath) {
            return Math.acos(a);
        } else {
            return 1.0;
        }
    }
}
