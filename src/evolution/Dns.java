package evolution;

public class Dns {
    private char[]   word;
    private Settings settings;
    private int      iterations;
    private int      fitnessValue;

    public Dns(char[] word, Settings settings) {
        this.word = word;
        this.settings = settings;
        updateFitnessValue(word);
    }

    private void updateFitnessValue(char[] word) {
        int fitnessValue = 0;
        for ( int i = 0; i < word.length; i++ ) {
            fitnessValue += Math.pow((int) settings.target[i] - (int) word[i], 2);
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
}
