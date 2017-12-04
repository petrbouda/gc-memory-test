package petrbouda;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Application {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 25; i++) {
            /*
             * To keep some LiveObjects longer in memory.
             */
            Task task1 = new Task(i, "A" + i);
            List<Contact> contacts1 = task1.call();

            Task task2 = new Task(i, "B" + i);
            List<Contact> contacts2 = task2.call();

            Task task3 = new Task(i, "C" + i);
            List<Contact> contacts3 = task3.call();

            Future<List<Contact>> submit4 = executor.submit(new Task(i, "D" + i));
            Future<List<Contact>> submit5 = executor.submit(new Task(i, "E" + i));

            System.out.println("INDEX: " + i);
            System.out.println("Contacts A: " + contacts1.size());
            System.out.println("Contacts B: " + contacts2.size());
            System.out.println("Contacts C: " + contacts3.size());
            System.out.println("Contacts D: " + submit4.get(10000, TimeUnit.SECONDS).size());
            System.out.println("Contacts E: " + submit5.get(10000, TimeUnit.SECONDS).size());
        }
        long end = System.currentTimeMillis() - start;

        System.out.println("-------------------------------------");
        System.out.println("Total Time: " + end);
        System.out.println("-------------------------------------");
    }
}
