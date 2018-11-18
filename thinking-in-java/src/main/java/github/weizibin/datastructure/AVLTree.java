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
            root = balance(root);
        } else if (content.compareTo(root.content) > 0) {
            root.right = insert(content, root.right);
            root = balance(root);
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

    public static <T extends Comparable<? super T>> TreeNode<T> remove(T content, TreeNode<T> root) {
        return delete(content, root);
    }

    private static <T extends Comparable<? super T>> TreeNode<T> delete(T content, TreeNode<T> node) {
        if (node == null) {
            return null;
        }
        // search
        int compare = node.content.compareTo(content);
        if (compare != 0) {
            if (compare > 0) {
                node.left = delete(content, node.left);
            }
            if (compare < 0) {
                node.right = delete(content, node.right);
            }
        } else {
            // search for replace element
            if (node.left == null && node.right == null) {
                // delete current
                return null;
            } else if (node.left != null && node.right == null) {
                // replace by left
                return node.left;
            } else if (node.left == null && node.right != null) {
                // replace by right
                return node.right;
            } else {
                // find left child
                TreeNode mostLeft = node.right;
                TreeNode mostLeftParent = null;
                while (mostLeft.left != null) {
                    mostLeftParent = mostLeft;
                    mostLeft = mostLeft.left;
                }
                if (mostLeftParent == null) {
                    mostLeft.left = node.left;
                } else {
                    mostLeftParent.left = mostLeft.right;
                    mostLeft.left = node.left;
                    mostLeft.right = node.right;
                }
                return mostLeft;
            }
        }

        if (node.left != null) {
            balance(node.left);
        }
        if (node.right != null) {
            balance(node.right);
        }
        node = balance(node);
        return node;
    }

    private static TreeNode balance(TreeNode node) {
        int balanceFactor = TreeUtils.level(node.left) - TreeUtils.level(node.right);
        if (balanceFactor == 2) {
            if (TreeUtils.level(node.left.left) < TreeUtils.level(node.left.right)) {
                node.left = leftRotate(node.left);
            }
            node = rightRotate(node);
        }
        if (balanceFactor == -2) {
            if (TreeUtils.level(node.right.right) < TreeUtils.level(node.right.left)) {
                node.right = rightRotate(node.right);
            }
            node = leftRotate(node);
        }
        return node;
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
        t = AVLTree.remove(4, t);
        TreeUtils.print(t);
        t = AVLTree.remove(1, t);
        TreeUtils.print(t);
        t = AVLTree.remove(9, t);
        TreeUtils.print(t);
        t = AVLTree.remove(6, t);
        TreeUtils.print(t);
        t = AVLTree.remove(5, t);
        TreeUtils.print(t);
    }

}
