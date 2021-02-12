package evolution;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EvolutionRunner {
    private final Random RANDOM_GENERATOR = new Random();

    private int evolutionsDone;
    private Settings settings;

    public EvolutionRunner(String targetWord) {
        this.run(targetWord);
    }

    private void run(String targetWord) {
        targetWord = replaceAllNonAsciiCharacters(targetWord);

        settings = new Settings(targetWord);
        Dns currentDns = new Dns(generateStartCharWithLength(settings.getTarget().length), settings);
        Printer.printStartWith(targetWord, currentDns);
        List<Integer> results = new ArrayList<>();
        while (currentDns.fitness() != 0) {
            Dns evolvedDns = evolveFrom(currentDns);
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
        targetWord = Normalizer.normalize(targetWord, Normalizer.Form.NFD);
        targetWord = targetWord.replaceAll("[^\\x00-\\x7F]", "");
        return targetWord;
    }

    private char[] generateStartCharWithLength(int length) {
        StringBuilder randomCharacters = new StringBuilder();
        for (int i = 0; i < length; i++) {
            randomCharacters.append((char) RANDOM_GENERATOR.nextInt(128));
        }
        return randomCharacters.toString().toCharArray();
    }

    private Dns evolveFrom(Dns juniorDns) {
        char[] statusQuo = copyCharArray(juniorDns.getWord());
        int iteration;
        Dns seniorDns = new Dns(statusQuo, settings);
        for (iteration = 1; iteration < settings.getInterationsPerEvolution() + 1; iteration++) {
            seniorDns.setWord(mutate(seniorDns.getWord()));
            int fitness = seniorDns.fitness();
            if (fitness == 0) {
                break;
            }
        }
        seniorDns.setIterations(iteration);
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
        return RANDOM_GENERATOR.nextInt((1 - (-1)) + 1) - 1;
    }

    private int randomPositionOf(int length) {
        return RANDOM_GENERATOR.nextInt(length);
    }
}
