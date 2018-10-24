package github.weizibin.datastructure;

import github.weizibin.datastructure.util.TreeUtils;

// TODO: 2018/10/24
public class AVLTree<T extends Comparable> {

    public TreeNode<T> root;

    public void insert(T content) {
        TreeNode<T> newNode = new TreeNode<>(null, null, content);
        if (root == null) {
            root = newNode;
            return;
        }

        TreeNode<T> node = root;
        while (node != null) {
            boolean less = content.compareTo(node.content) < 0;
            TreeNode<T> next = less ? node.left : node.right;
            if (next == null) {
                if (less) {
                    node.left = newNode;
                } else {
                    node.right = newNode;
                }
            }
            node = next;
        }

    }

    private static TreeNode leftRotate(TreeNode node) {
        TreeNode root = node.right;
        root.left = node;
        node.right = null;
        return root;
    }

    private static TreeNode rightRotate(TreeNode node) {
        TreeNode root = node.left;
        root.right = node;
        node.left = null;
        return root;
    }

    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
        avlTree.insert(1);
        avlTree.insert(2);
        avlTree.insert(3);
        avlTree.insert(4);
        TreeUtils.print(avlTree.root);
    }

}
