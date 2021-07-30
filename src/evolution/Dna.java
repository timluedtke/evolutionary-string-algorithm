package evolution;

import java.util.Arrays;

public class Dna {
    private final Settings settings;

    private char[] word;
    private int iterations;
    private int fitnessValue;

    public Dna(char[] word, Settings settings) {
        this.word = word;
        this.settings = settings;
        updateFitnessValue(word);
    }

    private void updateFitnessValue(char[] word) {
        int fitnessValue = 0;
        for (int i = 0; i < word.length; i++) {
            fitnessValue += Math.pow((int) settings.getTarget()[i] - (int) word[i], 2);
        }
        this.fitnessValue = fitnessValue;
    }

    public char[] getWord() {
        return word;
    }

    public void setWord(char[] word) {
        updateFitnessValue(word);
        this.word = word;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int fitness() {
        return fitnessValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dna)) return false;

        Dna dns = (Dna) o;

        if (iterations != dns.iterations) return false;
        if (fitnessValue != dns.fitnessValue) return false;
        if (!Arrays.equals(word, dns.word)) return false;
        return settings != null ? settings.equals(dns.settings) : dns.settings == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(word);
        result = 31 * result + (settings != null ? settings.hashCode() : 0);
        result = 31 * result + iterations;
        result = 31 * result + fitnessValue;
        return result;
    }
}
