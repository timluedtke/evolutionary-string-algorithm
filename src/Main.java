import timluedtke.Dns;
import timluedtke.PrintHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Random       random                  = new Random();

    private final static char[] target                  = "Hello World!".toCharArray();
    private final static char[] source                  = "aaaaaaaaaaaa".toCharArray();
    private final static int    interationsPerEvolution = 500;
    private static int          evolutionsDone;

    public static void main(String[] args) {
        Dns currentDns = new Dns(source);
        List<Integer> results = new ArrayList<>();
        while ( currentDns.fitness(target) != 0 ) {
            Dns evolvedDns = evolveFrom(currentDns);
            if ( (evolvedDns.fitness(target) >= 0) && (evolvedDns.fitness(target) < currentDns.fitness(target)) ) {
                System.out.println("Evolved in iteration " + evolutionsDone + " from fitness '" + currentDns.fitness(target) + "' to '" + evolvedDns.fitness(target) + "'");
                currentDns = evolvedDns;
                results.add(currentDns.getIterations());
            }
            evolutionsDone++;
        }
        System.out.println("\n" + evolutionsDone + " iterations Done. Ending with word: " + new String(currentDns.getWord()));
        PrintHelper.printResults(results, evolutionsDone, interationsPerEvolution);
    }

    private static Dns evolveFrom(Dns juniorDns) {
        char[] statusQuo = copyCharArray(juniorDns.start());
        int iteration;
        Dns seniorDns = new Dns(statusQuo);
        for ( iteration = 1; iteration < interationsPerEvolution + 1; iteration++ ) {
            seniorDns.setWord(mutate(seniorDns.getWord()));
            int fitness = seniorDns.fitness(target);
            if ( fitness == 0 ) {
                PrintHelper.printSuccessStatus(statusQuo, iteration, fitness);
                break;
            }
        }
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
