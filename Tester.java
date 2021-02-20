public class Tester {

    public static void main(String[] args) {
        double e = 0, delta = 0, D = 0.0000002;
        long tMin = granularity();
        int c = 10;
        double za = 2.33;

        System.out.println("size, time(ms)");

        for (int i = 10; i <= 500; i+=10) {
            misuration(i, c, za, tMin, D, delta, e);
        }
        for (int i = 1000; i <= 100000; i+=1250) {
            misuration(i, c, za, tMin, D, delta, e);
        }
    }

    public static long granularity() {
        long t0 = System.currentTimeMillis();
        long t1 = System.currentTimeMillis();
        while (t1 == t0) {
            t1 = System.currentTimeMillis();
        }
        return (t1 - t0);
    }

    public static long getTime(){
        long t = System.currentTimeMillis();
        return t;
    }

    public static double calculateRip( int size, long tMin ){
        long t0 = 0, t1 = 0;
        double rip = 1;
        while (t1-t0 <= tMin){
            rip = 2*rip;
            t0 = getTime();
            for (int i = 0; i < rip; i++){
                double[] array = RandomGenerator.findRandomGeneretor(size);
                Main.weightedLowerMedian(array);
            }
            t1 = getTime();
        }
        double max = rip;
        double min = rip/2;
        int cicliErrati = 5;
        while ( max-min >= cicliErrati ){
            rip = (max + min)/2;
            t0 = getTime();
            for (int i = 0; i < rip; i++){
                double[] array = RandomGenerator.findRandomGeneretor(size);
                Main.weightedLowerMedian(array);
            }
            t1 = getTime();
            if(t1-t0 <= tMin){
                min = rip;
            }else{
                max = rip;
            }
        }
        return max;
    }

    public static double Prepara( int size, long tMin ){
        long t0 = 0, t1 = 0;
        double rip = 1;
        while (t1-t0 <= tMin){
            rip = 2*rip;
            t0 = getTime();
            for (int i = 0; i < rip; i++){
                double[] array = RandomGenerator.findRandomGeneretor(size);
            }
            t1 = getTime();
        }
        double max = rip;
        double min = rip/2;
        int cicliErrati = 5;
        while ( max-min >= cicliErrati ){
            rip = (max + min)/2;
            t0 = getTime();
            for (int i = 0; i < rip; i++){
                double[] array = RandomGenerator.findRandomGeneretor(size);
            }
            t1 = getTime();
            if(t1-t0 <= tMin){
                min = rip;
            }else{
                max = rip;
            }
        }
        return max;
    }

    public static long timeCalcul(int size, int rip) {
        long t0 = getTime();
        for (int i = 0; i < rip; i++) {
            double[] array = RandomGenerator.findRandomGeneretor(size);
            Main.weightedLowerMedian(array);
        }
        long t1 = getTime();
        long tTot = t1 - t0;
        long tSing = tTot / rip;
        return tSing;
    }

    public static double averageNetTime(int size, long tMin){
        double ripTara = Prepara(size, tMin);
        double ripLordo = calculateRip(size, tMin);
        double t0 = getTime();
        for (int i = 0; i < ripTara; i++){
            double[] array = RandomGenerator.findRandomGeneretor(size);
        }
        double t1 = getTime();
        double tTara = t1-t0;
        t0 = getTime();
        for (int i = 0; i < ripLordo; i++){
            double[] e = RandomGenerator.findRandomGeneretor(size);
            Main.weightedLowerMedian(e);
        }
        t1 = getTime();
        double tLordo = t1-t0;
        double tMedio = (tLordo/ripLordo) - (tTara/ripTara);
        return tMedio;
    }

    public static void misuration(int size, int c, double za, long tMin, double D, double delta, double e){
        double t = 0;
        double sum2 = 0;
        double cn = 0;

        while(delta < D){
            for (int i = 0; i < c; i++) {

                double m = averageNetTime(size, tMin);
                t = t + m;
                sum2 = sum2 + Math.pow(m,2);
            }
            cn = cn + c;
            e = t / cn;
            double s = Math.sqrt(sum2/cn - Math.pow(e,2));
            delta = ((1/Math.sqrt(cn)) * za * s);
            System.out.println(size + ", " + e+ ",");
        }
    }
}