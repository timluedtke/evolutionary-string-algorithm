package evolution;

public class Dns {
    private char[] word;
    private int iterations;

    public Dns(char[] word) {
        this.word = word;
    }

    public int fitness(char[] target) {
        int fitnessValue = 0;
        for ( int i = 0; i < word.length; i++ ) {
            fitnessValue += ((int) target[i] - (int) word[i]) * ((int) target[i] - (int) word[i]);
        }
        return fitnessValue;
    }

    public char[] startWith() {
        return word;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public char[] getWord() {
        return word;
    }

    public void setWord(char[] word) {
        this.word = word;
    }
}
