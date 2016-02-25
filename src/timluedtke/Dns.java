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
}
