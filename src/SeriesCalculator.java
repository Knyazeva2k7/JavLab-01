public class SeriesCalculator {
    public static void series() {
        double summ = 0;
        double x = 1.0;

        for (int n = 2; Math.abs(x)>= 1E-6; n++) {
            System.out.println(n + ": ");
            x = 1. / (n * n + n - 2);
            System.out.println(x);

            if (Math.abs(x) >= 1E-6) {
                summ += x;
            }
        }
        System.out.println(summ);

    }
}
