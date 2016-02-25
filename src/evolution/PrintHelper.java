package evolution;

import java.text.DecimalFormat;
import java.util.List;

public class PrintHelper {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.###");

    public static void printResults(List<Integer> results, int evolutionsDone) {
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
            average = average / (evolutionsDone - noResult);
        } catch ( ArithmeticException e ) {
            average = 0;
        }
        double percentSuccessfull = 100 - (((double) noResult / (double) evolutionsDone) * 100);
        System.out.println("\n\n" + decimalFormat.format(percentSuccessfull) + "% waren erfolgreich. Durchschnittliche Anzahl an Versuchen: " + average);
    }

}
