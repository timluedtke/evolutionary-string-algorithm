package evolution;

import java.util.Arrays;

public class Settings {
    private final char[] target;
    private final int interationsPerEvolution = 1;

    public Settings(String target) {
        this.target = target.toCharArray();
    }

    public char[] getTarget() {
        return target;
    }

    public int getInterationsPerEvolution() {
        return interationsPerEvolution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Settings)) return false;

        Settings settings = (Settings) o;

        if (interationsPerEvolution != settings.interationsPerEvolution) return false;
        return Arrays.equals(target, settings.target);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(target);
        result = 31 * result + interationsPerEvolution;
        return result;
    }
}
