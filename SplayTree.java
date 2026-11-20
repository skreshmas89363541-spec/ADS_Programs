
import java.util.Scanner;

class SplayTree {

    class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    Node root;

    Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    Node splay(Node root, int key) {

        if (root == null || root.data == key)
            return root;

        if (key < root.data) {

            if (root.left == null)
                return root;

            if (key < root.left.data) {
                root.left.left =
                    splay(root.left.left, key);
                root = rightRotate(root);
            }

            else if (key > root.left.data) {
                root.left.right =
                    splay(root.left.right, key);

                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }

            return (root.left == null) ?
                root : rightRotate(root);
        }

        else {

            if (root.right == null)
                return root;

            if (key > root.right.data) {
                root.right.right =
                    splay(root.right.right, key);
                root = leftRotate(root);
            }

            else if (key < root.right.data) {
                root.right.left =
                    splay(root.right.left, key);

                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            }

            return (root.right == null) ?
                root : leftRotate(root);
        }
    }

    void insert(int key) {

        if (root == null) {
            root = new Node(key);
            return;
        }

        root = splay(root, key);

        if (root.data == key) {
            System.out.println("Duplicate value!");
            return;
        }

        Node newNode = new Node(key);

        if (key < root.data) {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        } else {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }

        root = newNode;
        System.out.println("Node inserted successfully.");
    }

    boolean search(int key) {
        root = splay(root, key);

        return root != null && root.data == key;
    }

    void delete(int key) {

        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        root = splay(root, key);

        if (root.data != key) {
            System.out.println("Value not found.");
            return;
        }

        if (root.left == null) {
            root = root.right;
        } else {
            Node rightSubtree = root.right;
            root = splay(root.left, key);
            root.right = rightSubtree;
        }

        System.out.println("Node deleted successfully.");
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.data + " ");
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SplayTree tree = new SplayTree();

        int choice, value;

        do {
            System.out.println("\n--- Splay Tree ---");
            System.out.println("1. Insert");
            System.out.println("2. Search");
            System.out.println("3. Delete");
            System.out.println("4. Display");
            System.out.println("5. Exit");

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
                    System.out.print("Enter value: ");
                    value = sc.nextInt();
                    tree.delete(value);
                    break;

                case 4:
                    System.out.println("Inorder Traversal:");
                    tree.inorder(tree.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.println("Program Ended.");
                    break;

                default:
                    System.out.println("Invalid Choice");
            }

        } while (choice != 5);

        sc.close();
    }
}
