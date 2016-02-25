package timluedtke;

public class Dns {
    private char[] word;
    private int    iterationsDone;

    public Dns(char[] word) {
        this.word = word;
    }

    public Dns(char[] word, int iterationsDone) {
        this.word = word;
        this.iterationsDone = iterationsDone;
    }

    public char[] start() {
        return word;
    }

    public int getIterations() {
        return iterationsDone;
    }

    public int fitness(char[] target) {
        int fitnessValue = 0;
        for ( int i = 0; i < word.length; i++ ) {
            fitnessValue += ((int) target[i] - (int) word[i]) * ((int) target[i] - (int) word[i]);
        }
        return fitnessValue;
    }

    public char[] getWord() {
        return word;
    }

    public void setWord(char[] word) {
        this.word = word;
    }
}
