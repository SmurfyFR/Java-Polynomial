public class Solution {
    private double imaginary;
    private double real;

    Solution(double real) {
        this.real = real;
        this.imaginary = 0.0;
    }

    Solution(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public String getStr() {
        if(this.imaginary != 0) {
            if(this.real == 0) {
                return "(" + this.imaginary + ")i";
            } else {
                return "(" + this.imaginary + ")i + (" + this.real + ")";
            }
        } else {
            return Double.toString(this.real);
        }
    }
}
