package company.errorsCreation;

public class SOFErrorWithRecursion {

    private static int level = 0;

    public static void main(String[] args) {
        System.out.println(fact(1 << 15));
    }

    public static long fact(int n) {
        level++;
        return n < 2 ? n : n * fact(n - 1);
    }
}
