import evolution.Runner;

public class Main {

    public static void main(String[] args) {
        if ( args.length < 1 || args[0] == null || "".equals(args[0]) ) {
            System.out.println("ungültige eingabe");
            return;
        }
        Runner.run(args[0]);
    }

}
