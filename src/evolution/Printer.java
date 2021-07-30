package evolution;

import java.text.DecimalFormat;
import java.util.List;

public class Printer {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.##");

    public static void printResults(List<Integer> results, int evolutionsDone) {
        System.out.println("\n" + evolutionsDone + " generations where needed to archieve goal.");
        int noResult = 0;
        for (Integer result : results) {
            if (result == -1) {
                noResult++;
            }
        }
        double percentSuccessfull = 100 - (((double) noResult / (double) evolutionsDone) * 100);
        System.out.println(" " + DECIMAL_FORMAT.format(percentSuccessfull) + "% of all generations could develop themselves.");
    }

    public static void printEvolvedStatus(Dna currentDns, Dna evolvedDns, int evolutionsDone) {
        if (evolutionsDone % 100 == 0)
            System.out.println("Generation " + evolutionsDone + " developed from fitness value '" + currentDns.fitness() + "' to '" + evolvedDns.fitness() + "' -> DNS: '"
                    + new String(evolvedDns.getWord()) + "'");
        System.out.flush();
    }

    public static void printStartWith(String targetWord, Dna currentDns) {
        System.out.println("Input / Target: '" + targetWord + "'");
        System.out.println("Starting with DNS '" + new String(currentDns.getWord()) + "' (fitness " + currentDns.fitness() + ")");
    }

    public static void printSolutionvalue(Dna currentDns) {
        System.out.println("\nSolution: '" + new String(currentDns.getWord()) + "'");
    }
}
