package Tree.algorithm.binarysearchtree;

import Tree.datastructure.binarysearchtree.TreeNode;
import java.util.LinkedList;

/**
 * ClassName BSTTree
 * Package Tree.algorithm.binarysearchtree
 * Description:
 *Validate Binary Search Tree
 * @Author: Lin
 * @Creat: 2024/3/18
 */
public class BSTTree {
    public boolean isValidBST(TreeNode node) {
        TreeNode p = node;
        LinkedList<TreeNode> stack = new LinkedList<>();
        long prev = Long.MIN_VALUE;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                TreeNode pop = stack.pop();
//                System.out.println("compare:" + prev + " " + pop.val);
                if (prev >= pop.val) {
                    return false;
                }
                prev = pop.val;
                p = pop.right;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        /*
                4
               / \
              2   6
             / \
            1   3
        */
        TreeNode root1 = new TreeNode(4, new TreeNode(2, new TreeNode(1), new TreeNode(3)), new TreeNode(6));
        System.out.println(new BSTTree().isValidBST(root1));
//        System.out.println("---------------");
        /*
                4
               / \
              2   6
                 / \
                3   7
         */
        TreeNode root2 = new TreeNode(4, new TreeNode(2), new TreeNode(6, new TreeNode(3), new TreeNode(7)));
        System.out.println(new BSTTree().isValidBST(root2));
//        System.out.println("---------------");

        /*
               1
              /
             1
         */
        TreeNode root3 = new TreeNode(1, new TreeNode(1), null);
        System.out.println(new BSTTree().isValidBST(root3));
//        System.out.println("---------------");

        /*
              3
               \
                4
               /
              5
         */
        TreeNode root4 = new TreeNode(3, null, new TreeNode(4, new TreeNode(5), null));
        System.out.println(new BSTTree().isValidBST(root4));
    }
}
