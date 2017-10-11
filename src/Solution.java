public class Solution {
    private float imaginary;
    private float real;

    Solution(float real) {
        this.real = real;
        this.imaginary = 0.0f;
    }

    Solution(float real, float imaginary) {
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
            return Float.toString(this.real);
        }
    }
}
