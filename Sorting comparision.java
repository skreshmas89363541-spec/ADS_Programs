import java.util.*;

public class SortingComparison {

    // Swap two elements
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Partition function for Quick Sort
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Merge function
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j])
                arr[k++] = leftArr[i++];
            else
                arr[k++] = rightArr[j++];
        }

        while (i < n1)
            arr[k++] = leftArr[i++];

        while (j < n2)
            arr[k++] = rightArr[j++];
    }

    // Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    // Measure execution time
    public static double measureTime(int[] arr, boolean quick) {
        long start = System.nanoTime();

        if (quick)
            quickSort(arr, 0, arr.length - 1);
        else
            mergeSort(arr, 0, arr.length - 1);

        long end = System.nanoTime();
        return (end - start) / 1e9;
    }

    public static void testSortingAlgorithms(int n) {
        Random rand = new Random();

        int[] best = new int[n];
        int[] average = new int[n];
        int[] worst = new int[n];

        // Best case: sorted input
        for (int i = 0; i < n; i++) {
            best[i] = i;
            worst[i] = n - i;
            average[i] = rand.nextInt(10000);
        }

        System.out.println("\nInput size: " + n);

        System.out.printf("Quick Sort (Best Case): %.6f seconds%n",
                measureTime(best.clone(), true));

        System.out.printf("Merge Sort (Best Case): %.6f seconds%n",
                measureTime(best.clone(), false));

        System.out.printf("Quick Sort (Average Case): %.6f seconds%n",
                measureTime(average.clone(), true));

        System.out.printf("Merge Sort (Average Case): %.6f seconds%n",
                measureTime(average.clone(), false));

        System.out.printf("Quick Sort (Worst Case): %.6f seconds%n",
                measureTime(worst.clone(), true));

        System.out.printf("Merge Sort (Worst Case): %.6f seconds%n",
                measureTime(worst.clone(), false));
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 5000, 10000};

        for (int n : sizes) {
            testSortingAlgorithms(n);
        }
    }
}