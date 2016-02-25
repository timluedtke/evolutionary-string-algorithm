import evolution.Dns;
import evolution.PrintHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Random random = new Random();

    private final static char[] TARGET = "Hello World!".toCharArray();
    private final static int interationsPerEvolution = 500;
    private static int evolutionsDone;

    public static void main(String[] args) {
        Dns currentDns = new Dns(generateStartCharWithLength(TARGET.length));
        List<Integer> results = new ArrayList<>();
        while (currentDns.fitness(TARGET) != 0) {
            Dns evolvedDns = evolveFrom(currentDns);
            if ((evolvedDns.fitness(TARGET) >= 0) && (evolvedDns.fitness(TARGET) < currentDns.fitness(TARGET))) {
                System.out.println("Evolved in generation " + evolutionsDone + " from fitness '" + currentDns.fitness(TARGET) + "' to '" + evolvedDns.fitness(TARGET)
                        + "' -> current DNS: '" + new String(evolvedDns.getWord()) + "'");
                currentDns = evolvedDns;
                results.add(evolvedDns.getIterations());
            } else {
                results.add(-1);
            }
            evolutionsDone++;
        }
        System.out.println("\n" + evolutionsDone + " generations Done. Ending with word: " + new String(currentDns.getWord()));
        PrintHelper.printResults(results, evolutionsDone);
    }

    private static char[] generateStartCharWithLength(int length) {
        String start = "";
        for (int i = 0; i < length; i++) {
            start += "a";
        }
        return start.toCharArray();
    }

    private static Dns evolveFrom(Dns juniorDns) {
        char[] statusQuo = copyCharArray(juniorDns.startWith());
        int iteration;
        Dns seniorDns = new Dns(statusQuo);
        for (iteration = 1; iteration < interationsPerEvolution + 1; iteration++) {
            seniorDns.setWord(mutate(seniorDns.getWord()));
            int fitness = seniorDns.fitness(TARGET);
            if (fitness == 0) {
                break;
            }
        }
        seniorDns.setIterations(iteration);
        return seniorDns;
    }

    private static char[] copyCharArray(char[] source) {
        char[] newChars = new char[source.length];
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = source[i];
        }
        return newChars;
    }

    private static char[] mutate(char[] mutant) {
        int position = randomPositionOf(mutant.length);
        int direction = randomBackOrForward();
        int slide = (mutant[position] + direction) % 128;
        mutant[position] = (char) slide;
        return mutant;
    }

    private static int randomBackOrForward() {
        return random.nextInt((1 - (-1)) + 1) - 1;
    }

    private static int randomPositionOf(int length) {
        return random.nextInt(length);
    }
}
