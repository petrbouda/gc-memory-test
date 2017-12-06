package petrbouda;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static petrbouda.Utility.*;

public class Application {

    private static final int MIN_GARBAGE = 100_000;
    private static final int MAX_GARBAGE = 150_000;
    private static final int MIN_LIFESET = 40_000;
    private static final int MAX_LIFESET = 60_000;

    private static final int PARALLEL_USERS = 4;

    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger();

        Runnable runnable = () -> {
            Contact[] biggerLifeSet = new Contact[]{};
            Contact[] longerLifeSet = new Contact[]{};

            for (int i = 0; i < 20; i++) {
                int index = counter.incrementAndGet();
                System.out.println("INDEX: " + index);

                Contact[] contacts1 = generate("contact-1 " + index,
                        MIN_GARBAGE, MAX_GARBAGE, MIN_LIFESET, MAX_LIFESET);
                Contact[] contacts2 = generate("contact-2 " + index,
                        MIN_GARBAGE, MAX_GARBAGE, MIN_LIFESET, MAX_LIFESET);

                biggerLifeSet = biggerLifeSet(index, biggerLifeSet, contacts1);
                longerLifeSet = longerLifeSet(index, longerLifeSet, contacts2);

                System.out.println("Size ALL: " + (biggerLifeSet.length + longerLifeSet.length + contacts1.length));
            }

            if (counter.get() > 5000) {
                System.exit(0);
            }
        };

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);

        // More parallel users
        for (int i = 0; i < PARALLEL_USERS; i++) {
            executor.scheduleWithFixedDelay(runnable, 0, 50, TimeUnit.MILLISECONDS);
        }
    }
}