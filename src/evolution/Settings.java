package evolution;

public class Settings {
    public char[] target;
    public int    interationsPerEvolution = 1;

    public Settings(String target) {
        this.target = target.toCharArray();
    }

    public char[] getTarget() {
        return target;
    }

    public int getInterationsPerEvolution() {
        return interationsPerEvolution;
    }
}
