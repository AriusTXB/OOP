public class Exp {
    public static void main(String[] args) {
        double x = Double.parseDouble(args[0]);
        double exp = 1.0;
        double fact = 1.0;
        for (double i = 1.0; i < 100.0; i++) {
            fact = fact * i;
            exp = exp + Math.pow(x, i) / fact;
        }
        System.out.println(exp);
    }
}
