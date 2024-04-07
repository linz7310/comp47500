package Tree.datastructure.avltree;

/**
 * <h3>AVL Tree</h3>
 * <ul>
 *     <li>Binary search trees may have out-of-balance nodes during insertion and deletion</li>
 *     <li>If the binary search tree is always balanced by rotating it during insertion and deletion,
 *     it is called a self-balancing binary search tree.</li>
 *     <li>AVL is one of the implementations of self-balancing binary search trees</li>
 * </ul>
 */
public class AVLTree {

    static class AVLNode {
        int key;
        Object value;
        AVLNode left;
        AVLNode right;
        int height = 1;

        public AVLNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public AVLNode(int key) {
            this.key = key;
        }

        public AVLNode(int key, Object value, AVLNode left, AVLNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    // Find the height of the node
    private int height(AVLNode node) {
        return node == null ? 0 : node.height;
    }

    // Update node height (add, delete, rotation)
    private void updateHeight(AVLNode node) {
        node.height = Integer.max(height(node.left), height(node.right)) + 1;
    }

    // balance factor = height of leftchild-height of rightchild = 1 -1 0
    private int bf(AVLNode node) {
        return height(node.left) - height(node.right);
    }


    private AVLNode rightRotate(AVLNode red) {
        AVLNode yellow = red.left;
        AVLNode green = yellow.right;
        yellow.right = red;
        red.left = green;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }


    private AVLNode leftRotate(AVLNode red) {
        AVLNode yellow = red.right;
        AVLNode green = yellow.left;
        yellow.left = red;
        red.right = green;
        updateHeight(red);
        updateHeight(yellow);
        return yellow;
    }

    // Left-rotate the left subtree, then right-rotate the root node.
    private AVLNode leftRightRotate(AVLNode node) {
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    // Right-rotate the right subtree, then left-rotate the root node.
    private AVLNode rightLeftRotate(AVLNode node) {
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    // Check if the nodes are out of balance, rebalance the Tree.
    private AVLNode balance(AVLNode node) {
        if (node == null) {
            return null;
        }
        int bf = bf(node);
        if (bf > 1 && bf(node.left) >= 0) { // LL
            return rightRotate(node);
        } else if (bf > 1 && bf(node.left) < 0) { // LR
            return leftRightRotate(node);
        } else if (bf < -1 && bf(node.right) > 0) { // RL
            return rightLeftRotate(node);
        } else if (bf < -1 && bf(node.right) <= 0) { // RR
            return leftRotate(node);
        }
        return node;
    }

    AVLNode root;

    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }

    private AVLNode doPut(AVLNode node, int key, Object value) {
        if (node == null) {
            return new AVLNode(key, value);
        }
        if (key == node.key) {
            node.value = value;
            return node;
        }
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else {
            node.right = doPut(node.right, key, value);
        }
        updateHeight(node);
        return balance(node);
    }

    public void remove(int key) {
        root = doRemove(root, key);
    }

    private AVLNode doRemove(AVLNode node, int key) {
        // 1. node == null
        if (node == null) {
            return null;
        }
        // 2. do not find the key
        if (key < node.key) {
            node.left = doRemove(node.left, key);
        } else if (node.key < key) {
            node.right = doRemove(node.right, key);
        } else {
            // 3. Find key 1) no children 2) only one child 3) two children
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.left == null) {
                node = node.right;
            } else if (node.right == null) {
                node = node.left;
            } else {
                AVLNode s = node.right;
                while (s.left != null) {
                    s = s.left;
                }
                s.right = doRemove(node.right, s.key);
                s.left = node.left;
                node = s;
            }
        }
        // 4. Update height
        updateHeight(node);
        // 5. balance
        return balance(node);
    }

    public Object search(int key) {
        return doSearch(root, key);
    }

    private Object doSearch(AVLNode node, int key) {
        if (node == null) {
            return null;
        }
        if (key == node.key) {
            return node.value;
        } else if (key < node.key) {
            return doSearch(node.left, key);
        } else {
            return doSearch(node.right, key);
        }
    }
}
