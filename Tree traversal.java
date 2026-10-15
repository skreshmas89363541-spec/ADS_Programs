class Node {
    int key;
    Node left, right;

    Node(int key) {
        this.key = key;
        left = right = null;
    }
}

public class TreeTraversal {

    Node root;

    // Preorder Traversal
    void preorder(Node node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    // Inorder Traversal
    void inorder(Node node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    // Postorder Traversal
    void postorder(Node node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.key + " ");
        }
    }

    public static void main(String[] args) {
        TreeTraversal tree = new TreeTraversal();

        // Creating the binary tree
        tree.root = new Node(30);
        tree.root.left = new Node(20);
        tree.root.right = new Node(40);
        tree.root.left.left = new Node(10);
        tree.root.left.right = new Node(25);
        tree.root.right.left = new Node(35);
        tree.root.right.right = new Node(50);

        System.out.print("Preorder : ");
        tree.preorder(tree.root);

        System.out.print("\nInorder : ");
        tree.inorder(tree.root);

        System.out.print("\nPostorder : ");
        tree.postorder(tree.root);
    }
}