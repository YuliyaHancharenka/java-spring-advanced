package company.errorsCreation;

import java.util.stream.IntStream;

public class SOFErrorWithoutRecursion {

    private final static int N = 5000;

    public static void main(String[] args) {
        final Runnable[] runnables = new Runnable[N];
        IntStream.range(0, N).forEach(i -> runnables[i] = () -> {
            System.out.println("Hi, I am " + (i + 1) + " runnable");
            if (i < N - 1) {
                runnables[i + 1].run();
            }
        });
        runnables[0].run();
    }
}
