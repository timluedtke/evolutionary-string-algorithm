package evolution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {
    private static Random random = new Random();
    private static int    evolutionsDone;

    public static void run() {
        Dns currentDns = new Dns(generateStartCharWithLength(Settings.TARGET.length));
        List<Integer> results = new ArrayList<>();
        while ( currentDns.fitness() != 0 ) {
            Dns evolvedDns = evolveFrom(currentDns);
            if ( (evolvedDns.fitness() >= 0) && (evolvedDns.fitness() < currentDns.fitness()) ) {
                PrintHelper.printEvolvedStatus(currentDns, evolvedDns, evolutionsDone);
                currentDns = evolvedDns;
                results.add(evolvedDns.getIterations());
            } else {
                results.add(-1);
            }
            evolutionsDone++;
        }
        PrintHelper.printResults(results, evolutionsDone, currentDns);
    }

    private static char[] generateStartCharWithLength(int length) {
        String start = "";
        for ( int i = 0; i < length; i++ ) {
            start += " ";
        }
        return start.toCharArray();
    }

    private static Dns evolveFrom(Dns juniorDns) {
        char[] statusQuo = copyCharArray(juniorDns.getWord());
        int iteration;
        Dns seniorDns = new Dns(statusQuo);
        for ( iteration = 1; iteration < Settings.interationsPerEvolution + 1; iteration++ ) {
            seniorDns.setWord(mutate(seniorDns.getWord()));
            int fitness = seniorDns.fitness();
            if ( fitness == 0 ) {
                break;
            }
        }
        seniorDns.setIterations(iteration);
        return seniorDns;
    }

    private static char[] copyCharArray(char[] source) {
        char[] newChars = new char[source.length];
        for ( int i = 0; i < newChars.length; i++ ) {
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
