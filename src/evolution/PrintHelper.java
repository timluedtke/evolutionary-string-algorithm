package evolution;

import java.text.DecimalFormat;
import java.util.List;

public class PrintHelper {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.####");

    public static void printResults(List<Integer> results, int evolutionsDone, Dns currentDns) {
        System.out.println("\n" + evolutionsDone + " Generationen wurden benötigt. Ergebnis: " + new String(currentDns.getWord()));
        int noResult = 0;
        for ( Integer result : results ) {
            if ( result == -1 ) {
                noResult++;
            }
        }
        double percentSuccessfull = 100 - (((double) noResult / (double) evolutionsDone) * 100);
        System.out.println(decimalFormat.format(percentSuccessfull) + "% Generationen konnten sich weiterentwickeln.");
    }

    public static void printEvolvedStatus(Dns currentDns, Dns evolvedDns, int evolutionsDone) {
        System.out.println("Generation " + evolutionsDone + " entwickelte sich von Fitness '" + currentDns.fitness() + "' nach '" + evolvedDns.fitness() + "' -> DNS: '"
                + new String(evolvedDns.getWord()) + "'");
        System.out.flush();
    }
}
