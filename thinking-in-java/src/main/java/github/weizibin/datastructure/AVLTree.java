package github.weizibin.datastructure;

import github.weizibin.datastructure.util.TreeUtils;

public class AVLTree {

    public static <T extends Comparable<? super T>> TreeNode<T> insert(T content, TreeNode<T> root) {
        if (root == null) {
            TreeNode<T> newNode = new TreeNode<>(null, null, content);
            return newNode;
        }

        if (content.compareTo(root.content) < 0) {
            root.left = insert(content, root.left);
            if (TreeUtils.level(root.left) - TreeUtils.level(root.right) == 2) {
                if (content.compareTo(root.left.content) > 0) {
                    root.left = leftRotate(root.left);
                }
                root = rightRotate(root);
            }
        } else if (content.compareTo(root.content) > 0) {
            root.right = insert(content, root.right);
            if (TreeUtils.level(root.right) - TreeUtils.level(root.left) == 2) {
                if (content.compareTo(root.right.content) < 0) {
                    root.right = rightRotate(root.right);
                }
                root = leftRotate(root);
            }
        }
        return root;
    }

    public static <T extends Comparable<? super T>> boolean exist(T content, TreeNode<T> root) {
        if (root == null) {
            return false;
        }
        int compare = root.content.compareTo(content);
        return compare == 0 ? true : (compare > 0) ? exist(content, root.left) : exist(content, root.right);
    }

    private static TreeNode leftRotate(TreeNode node) {
        TreeNode root = node.right;
        node.right = root.left;
        root.left = node;
        return root;
    }

    private static TreeNode rightRotate(TreeNode node) {
        TreeNode root = node.left;
        node.left = root.right;
        root.right = node;
        return root;
    }

    public static void main(String[] args) {
        TreeNode<Integer> t = AVLTree.insert(1, null);
        t = AVLTree.insert(7, t);
        t = AVLTree.insert(3, t);
        t = AVLTree.insert(2, t);
        t = AVLTree.insert(6, t);
        t = AVLTree.insert(10, t);
        t = AVLTree.insert(5, t);
        t = AVLTree.insert(9, t);
        t = AVLTree.insert(8, t);
        t = AVLTree.insert(4, t);
        TreeUtils.print(t);
    }

}
