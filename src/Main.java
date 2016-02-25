import timluedtke.Dns;
import timluedtke.PrintHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Random        random        = new Random();

    private final static char[]  target        = "Hello World!".toCharArray();
    private final static char[]  source        = "aaaaaaaaaaaa".toCharArray();
    private static int           interationsToMake;
    private static int           evolutionsToMake;

    public static void main(String[] args) {
        interationsToMake = 500;
        evolutionsToMake = 10;

        Dns currentDns = new Dns(source);
        List<Integer> results = new ArrayList<>();
        for ( int i = 0; i < evolutionsToMake; i++ ) {
            currentDns = evolveFrom(currentDns);
            results.add(currentDns.getIterations());
        }
        PrintHelper.printResults(results, evolutionsToMake, interationsToMake);
    }

    private static Dns evolveFrom(Dns dns) {
        System.out.println("Begin evolution from '" + new String(dns.start()) + "' with " + interationsToMake + "x" + evolutionsToMake + " iterations");
        char[] statusQuo = copyCharArray(dns.start());
        int iteration;
        int fitness;
        for ( iteration = 1; iteration < interationsToMake + 1; iteration++ ) {
            statusQuo = mutate(statusQuo);
            fitness = calcFitness(statusQuo);
            if ( fitness == 0 ) {
                PrintHelper.printSuccessStatus(statusQuo, iteration, fitness);
                return new Dns(statusQuo, iteration);
            }
        }
        return new Dns(statusQuo, iteration);
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

    private static int calcFitness(char[] statusQuo) {
        int fitnessValue = 0;
        for ( int i = 0; i < statusQuo.length; i++ ) {
            fitnessValue += ((int) target[i] - (int) statusQuo[i]) * ((int) target[i] - (int) statusQuo[i]);
        }
        return fitnessValue;
    }
}
