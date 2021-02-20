public class RandomGenerator {

    public double get() {
        final int a = 16087;
        final int m = 2147483647;
        final int q = 127773;
        final int r = 2836;

        double lo, hi, test;

        hi = Math.ceil(seed / q);
        lo = seed - q * hi;
        test = a * lo - r * hi;
        if (test < 0.0) {
            seed = test + m;
        } else {
            seed = test;
        }
        return seed / m;
    }


    public RandomGenerator(double s) {
        seed = s;
    }

    private double seed;

    public static double[] findRandomGeneretor(int size){
        double n;
        double[] array = new double[size];
        RandomGenerator r = new RandomGenerator(123456789);


            for (int i = 0; i < 10; i++) {
                n = Math.round(r.get() * 100);
                array[i] = n;
            }

        return array;
    }

    public static void main(String[] args) {
        double n;
        int l = 100;
        double[] array = new double[l];
        long seed = 123456789 ;
        RandomGenerator r = new RandomGenerator(seed);
        System.out.println("Start");
        for (int j = 0; j < l; j++) {
            for (int i = 0; i < l; i++) {
                n = Math.round(r.get() * 100);
                array[i] = n/10;
            }
            Main.mergeSort(array);
            for (int i = 0; i < l; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }
    }
}