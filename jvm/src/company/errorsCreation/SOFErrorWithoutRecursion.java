package company.errorsCreation;

import java.util.stream.IntStream;

public class SOFErrorWithoutRecursion {

    private static final int N = 5000;
    private static final Runnable[] runnables = new Runnable[N];

    static {
        IntStream.range(0, N).forEach(i -> runnables[i] = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Hi, I am " + (i + 1) + " runnable");
                        if (i < N - 1) {
                            runnables[i + 1].run();
                        }
                    }
                }
        );
    }

    public static void main(String[] args) {
        runnables[0].run();
    }
}
