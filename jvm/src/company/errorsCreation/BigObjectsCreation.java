package company.errorsCreation;


import java.math.BigInteger;

public class BigObjectsCreation {

    public static void main(String[] args) {

        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < 100000; j++) {
                BigInteger b = BigInteger.valueOf((i + j) * 1000000000);
                String s1 = "String " + j;
                String s2 = "String " + j;
                System.out.println(s1 + " " + s2 + " " + b);
            }
        }
    }
}
