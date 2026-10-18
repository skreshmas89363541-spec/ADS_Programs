
public class HeapDemo
{
    // Swap two elements
    static void swap(int[] arr, int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Min Heapify
    static void minHeapify(int[] arr, int n, int i)
    {
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] < arr[smallest])
            smallest = left;

        if (right < n && arr[right] < arr[smallest])
            smallest = right;

        if (smallest != i)
        {
            swap(arr, i, smallest);
            minHeapify(arr, n, smallest);
        }
    }

    // Max Heapify
    static void maxHeapify(int[] arr, int n, int i)
    {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i)
        {
            swap(arr, i, largest);
            maxHeapify(arr, n, largest);
        }
    }

    // Build Min Heap
    static void buildMinHeap(int[] arr, int n)
    {
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            minHeapify(arr, n, i);
        }
    }

    // Build Max Heap
    static void buildMaxHeap(int[] arr, int n)
    {
        for (int i = n / 2 - 1; i >= 0; i--)
        {
            maxHeapify(arr, n, i);
        }
    }

    // Delete root from Min Heap
    static int deleteMinRoot(int[] arr, int n)
    {
        if (n <= 0)
        {
            System.out.println("Min Heap is empty");
            return n;
        }

        arr[0] = arr[n - 1];
        n--;

        minHeapify(arr, n, 0);

        return n;
    }

    // Delete root from Max Heap
    static int deleteMaxRoot(int[] arr, int n)
    {
        if (n <= 0)
        {
            System.out.println("Max Heap is empty");
            return n;
        }

        arr[0] = arr[n - 1];
        n--;

        maxHeapify(arr, n, 0);

        return n;
    }

    // Display heap
    static void displayHeap(int[] arr, int n)
    {
        for (int i = 0; i < n; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // Main method
    public static void main(String[] args)
    {
        int[] minHeap = {10, 20, 15, 12, 40, 25};
        int[] maxHeap = {10, 20, 15, 12, 40, 25};

        int nMin = minHeap.length;
        int nMax = maxHeap.length;

        System.out.print("Original Array: ");
        displayHeap(minHeap, nMin);

        // Build Min Heap
        buildMinHeap(minHeap, nMin);

        System.out.print("Min Heap: ");
        displayHeap(minHeap, nMin);

        // Build Max Heap
        buildMaxHeap(maxHeap, nMax);

        System.out.print("Max Heap: ");
        displayHeap(maxHeap, nMax);

        // Delete root of Min Heap
        nMin = deleteMinRoot(minHeap, nMin);

        System.out.print("Min Heap after deleting root: ");
        displayHeap(minHeap, nMin);

        // Delete root of Max Heap
        nMax = deleteMaxRoot(maxHeap, nMax);

        System.out.print("Max Heap after deleting root: ");
        displayHeap(maxHeap, nMax);
    }
}