
import java.util.Scanner;

class RedBlackTree {

    static final int RED = 1;
    static final int BLACK = 0;

    class Node {
        int data;
        int color;
        Node left, right, parent;

        Node(int data) {
            this.data = data;
            color = RED;
        }
    }

    Node root = null;

    void insert(int data) {
        Node newNode = new Node(data);
        Node parent = null;
        Node current = root;

        while (current != null) {
            parent = current;

            if (data < current.data)
                current = current.left;
            else if (data > current.data)
                current = current.right;
            else {
                System.out.println("Duplicate value!");
                return;
            }
        }

        newNode.parent = parent;

        if (parent == null)
            root = newNode;
        else if (data < parent.data)
            parent.left = newNode;
        else
            parent.right = newNode;

        fixInsertion(newNode);
        System.out.println("Node inserted successfully.");
    }

    void fixInsertion(Node node) {
        while (node != root &&
               node.parent.color == RED) {

            Node parent = node.parent;
            Node grand = parent.parent;

            if (parent == grand.left) {
                Node uncle = grand.right;

                if (uncle != null && uncle.color == RED) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grand.color = RED;
                    node = grand;
                } else {
                    if (node == parent.right) {
                        node = parent;
                        leftRotate(node);
                    }

                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    rightRotate(node.parent.parent);
                }
            } else {
                Node uncle = grand.left;

                if (uncle != null && uncle.color == RED) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    grand.color = RED;
                    node = grand;
                } else {
                    if (node == parent.left) {
                        node = parent;
                        rightRotate(node);
                    }

                    node.parent.color = BLACK;
                    node.parent.parent.color = RED;
                    leftRotate(node.parent.parent);
                }
            }
        }

        root.color = BLACK;
    }

    void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != null)
            y.left.parent = x;

        y.parent = x.parent;

        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;

        y.left = x;
        x.parent = y;
    }

    void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;

        if (x.right != null)
            x.right.parent = y;

        x.parent = y.parent;

        if (y.parent == null)
            root = x;
        else if (y == y.parent.right)
            y.parent.right = x;
        else
            y.parent.left = x;

        x.right = y;
        y.parent = x;
    }

    boolean search(int data) {
        Node current = root;

        while (current != null) {
            if (data == current.data)
                return true;
            else if (data < current.data)
                current = current.left;
            else
                current = current.right;
        }

        return false;
    }

    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);

            System.out.print(node.data);
            System.out.print(
                node.color == RED ? "(R) " : "(B) "
            );

            inorder(node.right);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RedBlackTree tree = new RedBlackTree();

        int choice, value;

        do {
            System.out.println("\n--- Red Black Tree ---");
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
                    System.out.println("Inorder Traversal:");
                    tree.inorder(tree.root);
                    System.out.println();
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
