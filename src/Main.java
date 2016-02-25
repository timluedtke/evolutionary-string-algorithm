import java.util.Random;

public class Main {
    private static Random random = new Random();

    private static char[] target;
    private static char[] source;
    private static int    targetHash;

    public static void main(String[] args) {
        target = "n".toCharArray();
        source = "n".toCharArray();

        calcTargetHash();

        char[] statusQuo = source;
        int iteration = 0;
        int fitness = 1;
        while ( fitness != 0 ) {
            iteration++;
            mutate(statusQuo);
            fitness = calcFitness(statusQuo);
            printStatus(statusQuo, iteration, fitness);
        }
        System.out.println("targetHash was: " + targetHash);
    }

    private static void calcTargetHash() {
        int hash = 7;
        for ( char targetChar : target ) {
            hash = hash * 31 + (int) targetChar;
        }
        targetHash = hash;
    }

    private static void printStatus(char[] statusQuo, int iteration, int fitness) {
        if ( iteration % 100 == 0 ) {
            System.out.println("fitness: " + fitness + " | " + iteration + " " + new String(statusQuo));
        }
    }

    private static void mutate(char[] statusQuo) {
        int position = random.nextInt(source.length);
        int direction = random.nextInt((1 - (-1)) + 1) - 1;
        int slide = (statusQuo[position] + direction) % 128;
        statusQuo[position] = (char) slide;
    }

    private static int calcFitness(char[] statusQuo) {
        int hash = 7;
        for ( char quoChar : statusQuo ) {
            hash = hash * 31 + (int) quoChar;
        }
//        int difference = 0;
//        for ( int i = 0; i < statusQuo.length; i++ ) {
//            difference += Math.exp((int) target[i] - (int) statusQuo[i]);
//        }
        int difference = targetHash - hash;
        if ( difference == 0 ) {
            difference = statusQuo.equals(target) ? 0 : 222;
        }
        return difference;
    }
}
