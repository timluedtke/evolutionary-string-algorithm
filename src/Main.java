import evolution.EvolutionRunner;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1 || args[0] == null || "".equals(args[0])) {
            System.out.print("No argument found. Please insert target string for evolutionary algorithm: ");
            Scanner input = new Scanner(System.in);
            new EvolutionRunner(input.nextLine());
            return;
        }
        new EvolutionRunner(args[0]);
    }

}
