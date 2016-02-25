package timluedtke;

import java.text.DecimalFormat;
import java.util.List;

public class PrintHelper {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.###");

    public static void printSuccessStatus(char[] statusQuo, int iteration, int fitness) {
        System.out.println("Goal reached in step: " + iteration + " | fitness: " + fitness + " | " + new String(statusQuo));
    }

    public static void printResults(List<Integer> results, int evolutionsToMake, int interationsToMake) {
        int noResult = 0;
        int average = 0;
        for ( Integer result : results ) {
            if ( result == -1 ) {
                noResult++;
            } else {
                average += result;
            }
        }
        try {
            average = average / (evolutionsToMake - noResult);
        } catch ( ArithmeticException e ) {
            average = interationsToMake;
        }
        double percentSuccessfull = 100 - (((double) noResult / (double) evolutionsToMake) * 100);
        System.out.println("\n\n" + decimalFormat.format(percentSuccessfull) + "% waren erfolgreich. Durchschnittliche Anzahl an Versuchen: " + average);
    }

    private static void printStatus(int iteration, int fitness, char[] statusQuo, double interationsToMake) {
        if ( iteration % (interationsToMake / 10) == 0 ) {
            System.out.println("step: " + iteration + " | fitness: " + fitness + " | " + new String(statusQuo));
        }
    }
}
