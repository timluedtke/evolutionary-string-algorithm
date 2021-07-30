package evolution;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EvolutionRunner {
    private final Random randomGenerator = new Random();

    private int evolutionsDone;
    private Settings settings;

    public EvolutionRunner(String enteredTargetWord) {
        this.run(enteredTargetWord);
    }

    private void run(String enteredTargetWord) {
        String targetWord = replaceAllNonAsciiCharacters(enteredTargetWord);

        settings = new Settings(targetWord);
        Dna currentDns = new Dna(generateStartCharWithLength(settings.getTarget().length), settings);
        Printer.printStartWith(targetWord, currentDns);
        List<Integer> results = new ArrayList<>();
        while (currentDns.fitness() != 0) {
            Dna evolvedDns = evolveFrom(currentDns);
            if ((evolvedDns.fitness() >= 0) && (evolvedDns.fitness() < currentDns.fitness())) {
                Printer.printEvolvedStatus(currentDns, evolvedDns, evolutionsDone);
                currentDns = evolvedDns;
                results.add(evolvedDns.getIterations());
            } else {
                results.add(-1);
            }
            evolutionsDone++;
        }
        Printer.printResults(results, evolutionsDone);
        Printer.printSolutionvalue(currentDns);
    }

    private String replaceAllNonAsciiCharacters(String targetWord) {
        String processedTargetWord = Normalizer.normalize(targetWord, Normalizer.Form.NFD);
        return processedTargetWord.replaceAll("[^\\x00-\\x7F]", "");
    }

    private char[] generateStartCharWithLength(int length) {
        StringBuilder randomCharacters = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomCharacters.append((char) randomGenerator.nextInt(128));
        }
        return randomCharacters.toString().toCharArray();
    }

    private Dna evolveFrom(Dna juniorDns) {
        char[] statusQuo = copyCharArray(juniorDns.getWord());
        Dna seniorDns = new Dna(statusQuo, settings);
        int iterationNumber;
        for (iterationNumber = 1; iterationNumber < settings.getInterationsPerEvolution() + 1; iterationNumber++) {
            seniorDns.setWord(mutate(seniorDns.getWord()));
            int fitness = seniorDns.fitness();
            if (fitness == 0) {
                break;
            }
        }
        seniorDns.setIterations(iterationNumber);
        return seniorDns;
    }

    private char[] copyCharArray(char[] source) {
        char[] newChars = new char[source.length];
        for (int i = 0; i < newChars.length; i++) {
            newChars[i] = source[i];
        }
        return newChars;
    }

    private char[] mutate(char[] mutant) {
        int position = randomPositionOf(mutant.length);
        int direction = randomBackOrForward();
        int slide = (mutant[position] + direction) % 128;
        mutant[position] = (char) slide;
        return mutant;
    }

    private int randomBackOrForward() {
        return randomGenerator.nextInt((1 - (-1)) + 1) - 1;
    }

    private int randomPositionOf(int length) {
        return randomGenerator.nextInt(length);
    }
}
