import java.util.*;
import java.io.*;
import java.time.*;

public class AlgoAnalysisHelper {
    public static Integer[][] getArrays(String fileName, int n) {
        //open a scanner for the file passed in
        try {
            Scanner in = new Scanner(new File(fileName));
            Integer[][] output = new Integer[100][n];
            for (int i = 0; i < 100; i++) {
                Integer[] arr = new Integer[n];
                String s = in.next();
                String[] line = s.split(",");
                //System.out.println(line[0]);
                for (int j = 0; j < n; j++) {
                    arr[j] = Integer.valueOf(line[j]);
                }
                output[i] = arr;
            }
            in.close();
            return output;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public static Integer[] deepCopy(Integer[] arr) {
        Integer[] cpy = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            cpy[i] = arr[i];
        }
        return cpy;
    }


    public static void sort(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static long measureTime(Integer[] arr) { //this will give you the time it took to sort one array of X size
        long start = System.nanoTime();
        sort(arr);
        long end = System.nanoTime();
        return end - start;
    }

    public static void main(String[] args) {
        String fileName = "arrays10.txt";
        Integer[][] arrays = getArrays(fileName, 10);
        long[] timings = new long[12];

        //getting 12 different times and sticking them in an array of longs
        for (int k = 0; k < 12; k++) {
            long totalTime = 0;


            for (Integer[] array : arrays) {

                Integer[] arrayCopy = deepCopy(array);
                totalTime += measureTime(arrayCopy);
            }
            //getting average
            timings[k] = totalTime / arrays.length;
        }

        //ignoring the first two times and averaging the times then printing the average out
        long sum = 0;
        for (int k = 2; k < 12; k++) {
            sum += timings[k];
        }
        long averageTime = sum / 10;

        System.out.println("Average time: " + averageTime + " nanoseconds");
    }
}
