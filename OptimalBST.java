import java.util.Scanner;

public class OptimalBST {

    // Find minimum of two values
    public static double minValue(double a, double b) {
        return (a < b) ? a : b;
    }

    // Calculate sum of probabilities
    public static double sumProbabilities(
            double[] probabilities, int i, int j) {
        double sum = 0;

        for (int k = i; k <= j; k++) {
            sum += probabilities[k];
        }

        return sum;
    }

    // Sort keys and probabilities
    public static void sortKeys(int[] keys,
                                double[] probabilities) {
        int n = keys.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (keys[i] > keys[j]) {
                    int tempKey = keys[i];
                    keys[i] = keys[j];
                    keys[j] = tempKey;

                    double tempProb = probabilities[i];
                    probabilities[i] = probabilities[j];
                    probabilities[j] = tempProb;
                }
            }
        }
    }

    // Find Optimal Binary Search Tree
    public static void optimalBST(double[] probabilities,
                                  int[] keys) {
        int n = keys.length;

        double[][] cost = new double[n][n];
        int[][] root = new int[n][n];

        // Initialize cost and root matrices
        for (int i = 0; i < n; i++) {
            cost[i][i] = probabilities[i];
            root[i][i] = i;
        }

        // Dynamic programming
        for (int L = 2; L <= n; L++) {
            for (int i = 0; i <= n - L; i++) {
                int j = i + L - 1;
                cost[i][j] = Double.MAX_VALUE;

                double sum = sumProbabilities(
                    probabilities, i, j);

                for (int r = i; r <= j; r++) {
                    double left = (r > i)
                            ? cost[i][r - 1] : 0;

                    double right = (r < j)
                            ? cost[r + 1][j] : 0;

                    double c = left + right + sum;

                    if (c < cost[i][j]) {
                        cost[i][j] = c;
                        root[i][j] = r;
                    }
                }
            }
        }

        // Print cost matrix
        System.out.println("\nCost Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%.2f ", cost[i][j]);
            }
            System.out.println();
        }

        // Print root matrix
        System.out.println("\nRoot Matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(root[i][j] + " ");
            }
            System.out.println();
        }

        System.out.printf(
            "\nThe minimum cost of the optimal BST is: %.2f\n",
            cost[0][n - 1]);

        System.out.println(
            "Root key of the optimal BST: "
            + keys[root[0][n - 1]]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of keys: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Number of keys must be positive.");
            sc.close();
            return;
        }

        int[] keys = new int[n];
        double[] probabilities = new double[n];

        System.out.println(
            "Enter each key followed by its probability:");

        for (int i = 0; i < n; i++) {
            System.out.print("Key " + (i + 1) + ": ");
            keys[i] = sc.nextInt();
            probabilities[i] = sc.nextDouble();
        }

        // Presorting step
        sortKeys(keys, probabilities);

        System.out.println("\nSorted Keys and Probabilities:");
        for (int i = 0; i < n; i++) {
            System.out.printf("%d: %.2f\n",
                              keys[i], probabilities[i]);
        }

        optimalBST(probabilities, keys);
        sc.close();
    }
}