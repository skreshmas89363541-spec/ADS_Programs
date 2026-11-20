
import java.util.Scanner;

class BPlusTree {

    static final int MAX = 3;

    class Node {
        int[] keys = new int[MAX + 1];
        Node[] child = new Node[MAX + 2];
        int n = 0;
        boolean leaf;
        Node next;

        Node(boolean leaf) {
            this.leaf = leaf;
        }
    }

    class Split {
        int key;
        Node right;

        Split(int key, Node right) {
            this.key = key;
            this.right = right;
        }
    }

    Node root = new Node(true);

    Split insertNode(Node node, int key) {

        if (node.leaf) {
            int i = node.n - 1;

            while (i >= 0 && node.keys[i] > key) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }

            if (i >= 0 && node.keys[i] == key)
                return null;

            node.keys[i + 1] = key;
            node.n++;

            if (node.n <= MAX)
                return null;

            Node right = new Node(true);
            right.n = 2;

            for (int j = 0; j < 2; j++)
                right.keys[j] = node.keys[j + 2];

            node.n = 2;
            right.next = node.next;
            node.next = right;

            return new Split(right.keys[0], right);
        }

        int i = 0;

        while (i < node.n && key >= node.keys[i])
            i++;

        Split result = insertNode(node.child[i], key);

        if (result == null)
            return null;

        for (int j = node.n; j > i; j--)
            node.keys[j] = node.keys[j - 1];

        for (int j = node.n + 1; j > i + 1; j--)
            node.child[j] = node.child[j - 1];

        node.keys[i] = result.key;
        node.child[i + 1] = result.right;
        node.n++;

        if (node.n <= MAX)
            return null;

        Node right = new Node(false);
        int middle = 2;
        int promoted = node.keys[middle];

        right.n = 2;

        right.child[0] = node.child[middle + 1];

        for (int j = 0; j < 2; j++) {
            right.keys[j] = node.keys[middle + 1 + j];
            right.child[j + 1] =
                node.child[middle + 2 + j];
        }

        node.n = middle;

        return new Split(promoted, right);
    }

    void insert(int key) {
        Split result = insertNode(root, key);

        if (result != null) {
            Node newRoot = new Node(false);
            newRoot.keys[0] = result.key;
            newRoot.child[0] = root;
            newRoot.child[1] = result.right;
            newRoot.n = 1;
            root = newRoot;
        }

        System.out.println("Insertion completed.");
    }

    boolean search(int key) {
        Node current = root;

        while (!current.leaf) {
            int i = 0;

            while (i < current.n && key >= current.keys[i])
                i++;

            current = current.child[i];
        }

        for (int i = 0; i < current.n; i++) {
            if (current.keys[i] == key)
                return true;
        }

        return false;
    }

    void display() {
        Node current = root;

        while (!current.leaf)
            current = current.child[0];

        System.out.println("B+ Tree Elements:");

        while (current != null) {
            for (int i = 0; i < current.n; i++)
                System.out.print(current.keys[i] + " ");

            current = current.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BPlusTree tree = new BPlusTree();

        int choice, value;

        do {
            System.out.println("\n--- B+ Tree ---");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Display");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    value = sc.nextInt();
                    tree.insert(value);
                    break;

                case 2:
                    System.out.print("Enter value: ");
                    value = sc.nextInt();

                    if (tree.search(value))
                        System.out.println("Value Found");
                    else
                        System.out.println("Value Not Found");
                    break;

                case 3:
                    tree.display();
                    break;

                case 4:
                    System.out.println("Program Ended.");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 4);

        sc.close();
    }
}
