package Tree.datastructure.binarysearchtree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Binary Search Tree
 */
@SuppressWarnings("all")
public class BSTTree {

    BSTNode root;

    static class BSTNode {
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * <h3>get the value by key</h3>
     *
     */
    public Object get(int key) {
        BSTNode node = root;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (node.key < key) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    /**
     * <h3>find the min value of all keys</h3>
     *
     * @return value of key
     */
    public Object min() {
        return min(root);
    }

    private Object min(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.left != null) {
            p = p.left;
        }
        return p.value;
    }

    /**
     * <h3>find the max value of all keys</h3>
     *
     * @return value of key
     */
    public Object max() {
        return max(root);
    }

    private Object max(BSTNode node) {
        if (node == null) {
            return null;
        }
        BSTNode p = node;
        while (p.right != null) {
            p = p.right;
        }
        return p.value;
    }

    /**
     * <h3>save the key and value</h3>
     *
     * @param key
     * @param value
     */
    public void put(int key, Object value) {
        root = doPut(root, key, value);
    }

    private BSTNode doPut(BSTNode node, int key, Object value) {
        if (node == null) {
            return new BSTNode(key, value);
        }
        if (key < node.key) {
            node.left = doPut(node.left, key, value);
        } else if (node.key < key) {
            node.right = doPut(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    /**
     * <h3>find the predecessor of key </h3>
     *
     * @param key key number
     * @return predecessor
     */
    public Object predecessor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while (p != null) {
            if (key < p.key) {
                p = p.left;
            } else if (p.key < key) {
                ancestorFromLeft = p;
                p = p.right;
            } else {
                break;
            }
        }
        // No nodes found.
        if (p == null) {
            return null;
        }
        // Find the node Case 1: the node has a left subtree,
        // and the predecessor is the maximum value of the left subtree.
        if (p.left != null) {
            return max(p.left);
        }
        // Finding a node Case 2: a node has no left subtree,
        // if the nearest ancestor from the left is the predecessor.
        return ancestorFromLeft != null ?
                ancestorFromLeft.value : null;
    }

    /**
     * <h3>find the seccessor of key</h3>
     *
     * @param key key number
     * @return successor
     */
    public Object successor(int key) {
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while (p != null) {
            if (key < p.key) {
                ancestorFromRight = p;
                p = p.left;
            } else if (p.key < key) {
                p = p.right;
            } else {
                break;
            }
        }
        // No nodes found.
        if (p == null) {
            return null;
        }
        // Find the node Case 1: the node has a right subtree,
        // in which case the latter is the minimum of the right subtree.
        if (p.right != null) {
            return min(p.right);
        }
        // Find node Case 2: node has no right subtree,
        // if the nearest ancestor from the right is the successor
        return ancestorFromRight != null ?
                ancestorFromRight.value : null;
    }

    /**
     * <h3>Delete by keyword</h3>
     *
     * @param key key number
     * @return The value of the deleted keyword
     */


    public Object remove(int key) {
        BSTNode p = root;
        BSTNode parent = null;
        while (p != null) {
            if (key < p.key) {
                parent = p;
                p = p.left;
            } else if (p.key < key) {
                parent = p;
                p = p.right;
            } else {
                break;
            }
        }
        if (p == null) {
            return null;
        }
        if (p.left == null) {
            shift(parent, p, p.right);
        } else if (p.right == null) {
            shift(parent, p, p.left);
        } else {

            BSTNode s = p.right;
            BSTNode sParent = p;
            while (s.left != null) {
                sParent = s;
                s = s.left;
            }
            if (sParent != p) {
                shift(sParent, s, s.right);
                s.right = p.right;
            }
            shift(parent, p, s);
            s.left = p.left;
        }
        return p.value;
    }

    /**
     *
     *
     * @param parent  the parent of deleted key
     * @param deleted deleted key
     * @param child
     */
    private void shift(BSTNode parent, BSTNode deleted, BSTNode child) {
        if (parent == null) {
            root = child;
        } else if (deleted == parent.left) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /*
                 4
               /   \
              2     6
             / \   / \
            1   3 5   7
     */

    // find all values < key
    public List<Object> less(int key) { // key=6
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                // 处理值
                if (pop.key < key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

    // find all values > key
    public List<Object> greater(int key) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.right;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key > key) {
                    result.add(pop.value);
                } else {
                    break;
                }
                p = pop.left;
            }
        }
        return result;
    }

    // ind all values >= key1 and <= key2.
    public List<Object> between(int key1, int key2) {
        ArrayList<Object> result = new ArrayList<>();
        BSTNode p = root;
        LinkedList<BSTNode> stack = new LinkedList<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                BSTNode pop = stack.pop();
                if (pop.key >= key1 && pop.key <= key2) {
                    result.add(pop.value);
                } else if (pop.key > key2) {
                    break;
                }
                p = pop.right;
            }
        }
        return result;
    }

}
