public class RecursiveDigitReport {

    public static int digitSum(int n) {
        n = Math.abs(n);
        if (n < 10) return n;
        return (n % 10) + digitSum(n / 10);
    }

    public static int digitCount(int n) {
        if (n == 0) return 1;
        return digitCountHelper(Math.abs(n));
    }

    private static int digitCountHelper(int n) {
        if (n == 0) return 0;
        return 1 + digitCountHelper(n / 10);
    }

    public static int countDigit(int n, int d) {
        if (n == 0 && d == 0) return 1;
        return countDigitHelper(Math.abs(n), d);
    }

    private static int countDigitHelper(int n, int d) {
        if (n == 0) return 0;
        int count = (n % 10 == d) ? 1 : 0;
        return count + countDigitHelper(n / 10, d);
    }

    public static void main(String[] args) {
        int[] tests = {50205, 0, -731};
        for (int t : tests) {
            System.out.println("Number: " + t);
            System.out.println("  digitSum: " + digitSum(t));
            System.out.println("  digitCount: " + digitCount(t));
            System.out.println("  countDigit(0): " + countDigit(t, 0));
            System.out.println("-------------------------");
        }
    }
}
