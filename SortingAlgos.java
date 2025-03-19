// Camron Ganchi
// January 22nd
// DSA

import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[] arr = {3, -1, 67, 23, 1, -4, 1004, 2, 45, 44, 48, 2, 4, 6, 7, 1};
        bidirectionalSelectionSort(arr);
        System.out.println("(Insert whichever sorting algorithim you called above): " + printArr(arr));

    }

    public static String printArr(int[] arr) {
        String str = "";
        for(int i = 0; i<arr.length; i++){
            str += arr[i] + " ";
        }
        return str;
    }

    // Iterative Comparison Sorts

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
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

    // Shaker Sort
    public static void shakerSort(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            for (int i = left; i < right; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (arr[i] < arr[i - 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                }
            }
            left++;
        }
    }

    // Selection Sort
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    // Bidirectional Selection Sort
    public static void bidirectionalSelectionSort(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int minIndex = left, maxIndex = right;
            for (int i = left; i <= right; i++) {
                if (arr[i] < arr[minIndex]) minIndex = i;
                if (arr[i] > arr[maxIndex]) maxIndex = i;
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[left];
            arr[left] = temp;

            if (maxIndex == left) maxIndex = minIndex;

            temp = arr[maxIndex];
            arr[maxIndex] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    // Insertion Sort
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // Shell Sort
    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i;
                while (j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }

    // Recursive Comparison Sorts

    // Merge Sort
    public static int[] mergeSort(int[] arr) {
        if (arr.length <= 1) return arr;

        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }

        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }

        return result;
    }

    // Quick Sort
    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    // Heap Sort
    public static void heapSort(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, arr.length, i);
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    // Non-Comparison Sorts

    // Counting Sort
    public static int[] countingSort(int[] arr) {
        int max = arr[0], min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }

        int range = max - min + 1;
        int[] count = new int[range];
        for (int num : arr) {
            count[num - min]++;
        }

        int[] output = new int[arr.length];
        int index = 0;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                output[index++] = i + min;
                count[i]--;
            }
        }
        return output;
    }

    // Bucket Sort
    public static int[] bucketSort(int[] arr) {
        int max = arr[0], min = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int bucketCount = (int) Math.sqrt(arr.length);
        int[][] buckets = new int[bucketCount][0];

        for (int num : arr) {
            int bucketIndex = (num - min) * bucketCount / (max - min + 1);
            buckets[bucketIndex] = appendToBucket(buckets[bucketIndex], num);
        }

        int[] output = new int[arr.length];
        int index = 0;
        for (int[] bucket : buckets) {
            if (bucket.length > 0) {
                bubbleSort(bucket);
                for (int num : bucket) {
                    output[index++] = num;
                }
            }
        }
        return output;
    }

    private static int[] appendToBucket(int[] bucket, int value) {
        int[] newBucket = new int[bucket.length + 1];
        for (int i = 0; i < bucket.length; i++) {
            newBucket[i] = bucket[i];
        }
        newBucket[bucket.length] = value;
        return newBucket;
    }
}