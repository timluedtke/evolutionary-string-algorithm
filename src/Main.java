import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Random       random = new Random();

    private final static char[] target = "z".toCharArray();
    private final static char[] source = "a".toCharArray();
    private static int          interationsToMake;
    private static int          evolutionsToMake;

    public static void main(String[] args) {
        interationsToMake = 10000;
        evolutionsToMake = 10000;

        System.out.println("Begin evolution from '" + new String(source) + "' with " + interationsToMake + "x" + evolutionsToMake + " iterations, target is: '"
                + new String(target) + "'");

        List<Integer> results = new ArrayList<>();
        for ( int i = 0; i < evolutionsToMake; i++ ) {
            results.add(runEvolutionOnce());
        }

        printResults(results);
    }

    private static void printResults(List<Integer> results) {
        int noResult = 0;
        int average = 0;
        for ( Integer result : results ) {
            if ( result == -1 ) {
                noResult++;
            } else {
                average += result;
            }
        }
        average = average / (evolutionsToMake - noResult);
        double percentSuccessfull = 100 - (((double) noResult / (double) evolutionsToMake) * 100);
        System.out.println("\n\n" + percentSuccessfull + "% waren erfolgreich. Durchschnittliche Anzahl an Versuchen: " + average);
    }

    private static int runEvolutionOnce() {
        System.out.println("--------------------------------------------------");
        char[] statusQuo = copyCharArray(source);
        int iteration;
        int fitness;
        for ( iteration = 1; iteration < interationsToMake + 1; iteration++ ) {
            statusQuo = mutate(statusQuo);
            fitness = calcFitness(statusQuo);
            printStatus(iteration, fitness, statusQuo);
            if ( fitness == 0 ) {
                System.out.println("Goal reached in step: " + iteration + " | fitness: " + fitness + " | " + new String(statusQuo));
                return iteration;
            }
        }
        return -1;
    }

    private static char[] copyCharArray(char[] source) {
        char[] newChars = new char[source.length];
        for ( int i = 0; i < newChars.length; i++ ) {
            newChars[i] = source[i];
        }
        return newChars;
    }

    private static void printStatus(int iteration, int fitness, char[] statusQuo) {
        if ( iteration % (interationsToMake / 10) == 0 ) {
            System.out.println("step: " + iteration + " | fitness: " + fitness + " | " + new String(statusQuo));
        }
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
            fitnessValue += ((int) target[i] - (int) statusQuo[i]);
        }
        return fitnessValue;
    }

    private static int hashMe(char[] input) {
        int hash = 7;
        for ( char quoChar : input ) {
            hash = hash * 31 + (int) quoChar;
        }
        return hash;
    }
}
