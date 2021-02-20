import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        double[] array = normalization(s);
        if (array.length > 0) {
            double res = weightedLowerMedian(array);
            System.out.println(res);
        }

    }

    // funzione che trova la mediana inferiore pesata
    public static double weightedLowerMedian(double[] array) {
        mergeSort(array);

        double temp = 0.0, sum = 0;
        int i = 0, l = array.length - 1;

        for(int j = 0; j <= l; j++) {
            sum = sum + array[j];
        }

        double halfSum = sum/2;

        while( i < l){
            temp = temp + array[i];
            if (temp >= halfSum) {
                break;
            }
            i++;
        }
        return array[i];
    }

    // algoritmo di sorting
    public static void mergeSort(double[] array) { 
        int l = array.length;
        sort(array, l);
    }

    // procedura di mergeSort
    public static void sort(double[] array, int l) {
        if (l >= 2) {
            int q = l / 2;
            double[] r = new double[q];
            double[] p = new double[l - q];
            
            for(int i = 0; i < q; ++i) {
                r[i] = array[i];
            }
            for(int i = q; i < l; ++i) {
                p[i - q] = array[i];
            }
            sort(r, q);
            sort(p, l - q);
            merge(array, r, p, q, l - q);
        }
    }

    // procedura di sort
    public static void merge(double[] array, double[] l, double[] q, int r, int p) {
        int i = 0;
        int j = 0;
        int k = 0;

        while(i < r && j < p) {
            if (l[i] <= q[j]) {
                array[k++] = l[i++];
            } else {
                array[k++] = q[j++];
            }
        }
        while(i < r) {
            array[k++] = l[i++];
        }
        while(j < p) {
            array[k++] = q[j++];
        }
    }

    // funzione di standardizzazione dell'input
    public static double[] normalization(String s) {
        if (s.substring(s.length()) == ".") {
            s = s.substring(0, s.length() - 1);
        }
        if (s.length() == 0) {
            System.out.println("Empty string!");
            return new double[0];
        } else {
            char c = s.charAt(s.length() - 1);
            if (c == '.') {
                s = s.substring(0, s.length() - 1);
            }
            s = s.replaceAll(" ", "");
            String[] a = s.split(",");
            double[] b = new double[a.length];
            for(int p = 0; p < a.length; ++p) {
                if (a[p].charAt(a[p].length() - 1) == ',') {
                    a[p] = a[p].substring(0, a[p].length() - 1);
                }
                b[p] = Double.parseDouble(a[p]);
            }
            return b;
        }
    }

    // funzione per stampare un array
    public static void toString(double[] array) {
        for(int i = 0; i < array.length; ++i) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
    }
}

