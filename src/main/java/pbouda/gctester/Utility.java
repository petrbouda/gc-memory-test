package pbouda.gctester;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static pbouda.gctester.Application.LEVEL;
import static pbouda.gctester.Application.LOGGER;

class Utility {

    static Contact[] longerLifeSet(int index, Contact[] current, Contact[] contacts1) {
        if (index % 10 == 0) {
            return contacts1;
        } else {
            return getSample(.8f, current);
        }
    }

    static Contact[] biggerLifeSet(int index, Contact[] current, Contact[] contacts) {
        Contact[] preserved = getSample(.5f, current);
        Contact[] newSample = getSample(.5f, contacts);
        Contact[] newLifeSet = combine(newSample, preserved);

        return index % 2 == 0 ? getSample(.5f, newLifeSet) : newLifeSet;
    }

    private static Contact[] getSample(float percentage, Contact[] contacts) {
        return Arrays.copyOf(contacts, Math.round(contacts.length * percentage));
    }

    private static Contact[] combine(Contact[] a, Contact[] b) {
        int length = a.length + b.length;
        Contact[] result = new Contact[length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

    static Contact[] generate(String label, int minGarbage, int maxGarbage, int minLifeSet, int maxLifeSet) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int lifesetIteration = random.nextInt(minGarbage, maxGarbage);

        int overallSize = 0;
        for (int i = 0; i < random.nextInt(minLifeSet, maxLifeSet); i++) {
            String garbage = "Garbage: " + i + " Index: " + label;
            overallSize += garbage.length();
        }
        LOGGER.log(LEVEL, label + ": Size: " + overallSize);

        Contact[] contacts = new Contact[lifesetIteration];
        for (int i = 0; i < lifesetIteration; i++) {
            contacts[i] = new Contact("FirstName " + i + " | " + label, "LastName " + i + " | " + label);
        }
        return contacts;
    }
}
