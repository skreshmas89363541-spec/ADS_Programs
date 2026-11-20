
import java.util.Scanner;

class NQueens {

    int n;
    int[] board;
    int count = 0;

    NQueens(int n) {
        this.n = n;
        board = new int[n];
    }

    boolean isSafe(int row, int col) {

        for (int i = 0; i < row; i++) {

            if (board[i] == col)
                return false;

            if (Math.abs(board[i] - col) ==
                Math.abs(i - row))
                return false;
        }

        return true;
    }

    void solve(int row) {

        if (row == n) {
            count++;
            printBoard();
            return;
        }

        for (int col = 0; col < n; col++) {

            if (isSafe(row, col)) {

                board[row] = col;

                solve(row + 1);
            }
        }
    }

    void printBoard() {

        System.out.println("\nSolution " + count + ":");

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (board[i] == j)
                    System.out.print("Q ");
                else
                    System.out.print("- ");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of Queens: ");
        int n = sc.nextInt();

        if (n < 1 || n > 10) {
            System.out.println("Enter a value from 1 to 10.");
        } else {
            NQueens obj = new NQueens(n);

            obj.solve(0);

            System.out.println(
                "\nTotal Solutions: " + obj.count
            );
        }

        sc.close();
    }
}
