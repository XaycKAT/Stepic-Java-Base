import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

public class StepicJavaBase {
    public static boolean isPalindrome(String text) {
        String newText = text.replaceAll("[^A-Za-z1-9]+", "");
        String revText = new StringBuilder(newText).reverse().toString();
        if (newText.equalsIgnoreCase(revText))
            return true;
        return false;
    }
    public static BigInteger factorial(int n) {
        BigInteger ret = BigInteger.ONE;
        for (int i = 1; i <= n; ++i) ret = ret.multiply(BigInteger.valueOf(i));
        return ret;
    }
    public static int[] mergeArrays(int[] a1, int[] a2) {
        int[] res = new int[a1.length + a2.length];
        int posA = 0, posB = 0;
        for (int i = 0; i < res.length; i++) {
            if (posA == a1.length) {
                res[i] = a2[posB];
                posB++;
            } else if (posB == a2.length) {
                res[i] = a1[posA];
                posA++;
            } else if (a1[posA] <= a2[posB]) {
                res[i] = a1[posA];
                posA++;
            } else if (a1[posA] > a2[posB]) {
                res[i] = a2[posB];
                posB++;
            }
        }
        return res;
    }
    public static String printTextPerRole(String[] roles, String[] textLines) {
        int rolesSize = roles.length;
        int textLinesSize = textLines.length;
        StringBuilder answer = new StringBuilder();
        for(int i = 0; i < rolesSize; ++i){
            answer.append(roles[i]+ ':' + "\n");
            for(int j = 0; j < textLinesSize; ++j){
                if (textLines[j].startsWith(roles[i] + ":")) {
                    answer.append(j + 1 + ")" + textLines[j].substring(roles[i].length()+1) + "\n");
                }
            }
            if(roles[i] != roles[roles.length-1])
                answer.append("\n");
        }
        return answer.toString().substring(0,answer.length()-1);
    }




    public final class ComplexNumber {
        private final double re;
        private final double im;

        public ComplexNumber(double re, double im) {
            this.re = re;
            this.im = im;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ComplexNumber that = (ComplexNumber) o;
            return Double.compare(that.re, re) == 0 &&
                    Double.compare(that.im, im) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(re, im);
        }

        public double getRe() {
            return re;
        }

        public double getIm() {
            return im;
        }
        
    }

    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        double num = 10e7 ;
        double step = (b - a) / num;
        double result = .0;
        for (int i = 0; i < (int) num ; ++i){
            result +=f.applyAsDouble(a+step*i)*(step*(i+1)-step*i);
        }
        return result;
    }

    public static class AsciiCharSequence implements CharSequence {

        private byte[] bytes;

        public AsciiCharSequence (byte[] bytes) {
            this.bytes = bytes;
        }
        @Override
        public int length() {
            return bytes.length;
        }

        @Override
        public char charAt(int index) {
            return (char) bytes[index];
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            byte[] subBytes = Arrays.copyOfRange(bytes,start,end);
            return new AsciiCharSequence(subBytes);

        }

        @Override
        public String toString() {
           return new String(bytes);
        }


    }

    public static double sqrt(double x) {
        try {
            if(x < 0)
                throw new IllegalArgumentException();
            return Math.sqrt(x);

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Expected non-negative number, got " + x);
        }
    }


    public static void main(String[] args) {
        System.out.println(sqrt(4));
    }
}
