package evolution;

import java.text.DecimalFormat;
import java.util.List;

public class PrintHelper {
    private static DecimalFormat decimalFormat = new DecimalFormat("0.##");

    public static void printResults(List<Integer> results, int evolutionsDone) {
        System.out.print("\n" + evolutionsDone + " Generationen wurden benötigt.");
        int noResult = 0;
        for ( Integer result : results ) {
            if ( result == -1 ) {
                noResult++;
            }
        }
        double percentSuccessfull = 100 - (((double) noResult / (double) evolutionsDone) * 100);
        System.out.println("  " + decimalFormat.format(percentSuccessfull) + "% Generationen konnten sich weiterentwickeln.");
    }

    public static void printEvolvedStatus(Dns currentDns, Dns evolvedDns, int evolutionsDone) {
        if ( evolutionsDone % 100 == 0 )
            System.out.println("Generation " + evolutionsDone + " entwickelte sich von Fitness '" + currentDns.fitness() + "' nach '" + evolvedDns.fitness() + "' -> DNS: '"
                    + new String(evolvedDns.getWord()) + "'");
        System.out.flush();
    }

    public static void printStartWith(String targetWord, Dns currentDns) {
        System.out.println("Eingabewort: '" + targetWord + "'");
        System.out.println("Starte mit DNS '" + new String(currentDns.getWord()) + "' (Fitness " + currentDns.fitness() + ")");
    }

    public static void printErgebniswort(Dns currentDns) {
        System.out.println("Lösung: '" + new String(currentDns.getWord()) + "'");
    }
}
