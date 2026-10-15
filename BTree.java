class BTreeNode {

    int[] keys;
    BTreeNode[] children;
    int n;
    boolean leaf;

    // Constructor
    BTreeNode(boolean leaf) {
        this.leaf = leaf;

        // Order 3
        // Maximum keys = 2
        // 3rd position is used temporarily during overflow
        keys = new int[3];

        // Maximum children = 3
        // Extra position is used temporarily during overflow
        children = new BTreeNode[4];

        n = 0;
    }

    // Inorder Traversal
    void inorder() {
        int i;

        for (i = 0; i < n; i++) {

            if (!leaf)
                children[i].inorder();

            System.out.print(keys[i] + " ");
        }

        if (!leaf)
            children[i].inorder();
    }

    // Preorder Traversal
    void preorder() {

        for (int i = 0; i < n; i++)
            System.out.print(keys[i] + " ");

        if (!leaf) {
            for (int i = 0; i <= n; i++)
                children[i].preorder();
        }
    }

    // Postorder Traversal
    void postorder() {

        if (!leaf) {
            for (int i = 0; i <= n; i++)
                children[i].postorder();
        }

        for (int i = 0; i < n; i++)
            System.out.print(keys[i] + " ");
    }
}


public class BTree {

    BTreeNode root;

    // Constructor
    BTree() {
        root = null;
    }

    // Insert
    void insert(int k) {

        // If tree is empty
        if (root == null) {

            root = new BTreeNode(true);

            root.keys[0] = k;
            root.n = 1;

            return;
        }

        // Insert the key
        SplitResult result = insertRecursive(root, k);

        // If root is split
        if (result != null) {

            BTreeNode newRoot = new BTreeNode(false);

            // Middle key becomes root
            newRoot.keys[0] = result.key;
            newRoot.n = 1;

            // Attach left and right nodes
            newRoot.children[0] = result.left;
            newRoot.children[1] = result.right;

            root = newRoot;
        }
    }


    // Recursive insertion
    SplitResult insertRecursive(BTreeNode node, int k) {

        int i = node.n - 1;

        // CASE 1: Leaf node
        if (node.leaf) {

            // Move greater keys to the right
            while (i >= 0 && node.keys[i] > k) {

                node.keys[i + 1] = node.keys[i];
                i--;
            }

            // Insert key
            node.keys[i + 1] = k;
            node.n++;

            // If 3 keys, split
            if (node.n == 3)
                return split(node);

            return null;
        }


        // CASE 2: Non-leaf node

        // Find correct child
        while (i >= 0 && node.keys[i] > k)
            i--;

        i++;

        // Insert into child
        SplitResult result =
            insertRecursive(node.children[i], k);

        // Child did not split
        if (result == null)
            return null;

        // Shift children
        for (int j = node.n; j >= i + 1; j--)
            node.children[j + 1] = node.children[j];

        // Put left and right nodes
        node.children[i] = result.left;
        node.children[i + 1] = result.right;

        // Shift keys
        for (int j = node.n - 1; j >= i; j--)
            node.keys[j + 1] = node.keys[j];

        // Put promoted key
        node.keys[i] = result.key;
        node.n++;

        // If this node now has 3 keys, split
        if (node.n == 3)
            return split(node);

        return null;
    }


    // Split a node containing 3 keys
    SplitResult split(BTreeNode node) {

        // Example:
        // [10 20 30]
        //
        // Middle key = 20
        // Left  = [10]
        // Right = [30]

        int middleKey = node.keys[1];

        // Create left node
        BTreeNode left =
            new BTreeNode(node.leaf);

        left.keys[0] = node.keys[0];
        left.n = 1;

        // Create right node
        BTreeNode right =
            new BTreeNode(node.leaf);

        right.keys[0] = node.keys[2];
        right.n = 1;

        // If node has children
        if (!node.leaf) {

            left.children[0] = node.children[0];
            left.children[1] = node.children[1];

            right.children[0] = node.children[2];
            right.children[1] = node.children[3];
        }

        // Return promoted key
        // and two new nodes
        return new SplitResult(
            middleKey, left, right
        );
    }


    // Preorder
    void preorder() {

        if (root != null)
            root.preorder();
    }

    // Inorder
    void inorder() {

        if (root != null)
            root.inorder();
    }

    // Postorder
    void postorder() {

        if (root != null)
            root.postorder();
    }


    // Stores split information
    static class SplitResult {

        int key;
        BTreeNode left;
        BTreeNode right;

        SplitResult(int key,
                    BTreeNode left,
                    BTreeNode right) {

            this.key = key;
            this.left = left;
            this.right = right;
        }
    }


    public static void main(String[] args) {

        // ORDER = 3
        BTree tree = new BTree();

        // Values
        int[] values =
            {10, 20, 5, 6, 12, 30, 7, 17};

        // Creating B-Tree
        for (int x : values)
            tree.insert(x);

        // Preorder
        System.out.print("Preorder: ");
        tree.preorder();
        System.out.println();

        // Inorder
        System.out.print("Inorder: ");
        tree.inorder();
        System.out.println();

        // Postorder
        System.out.print("Postorder: ");
        tree.postorder();
        System.out.println();
    }
}