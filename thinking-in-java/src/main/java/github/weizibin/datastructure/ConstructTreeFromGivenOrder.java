package github.weizibin.datastructure;

import github.weizibin.datastructure.util.TreeUtils;

public class ConstructTreeFromGivenOrder {

    public static <T> TreeNode<T> constructFromPreorderAndInorder(T[] preorder, T[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        T rootVal = preorder[0];
        TreeNode<T> root = new TreeNode<>(null, null, rootVal);
        if (preorder.length == 1) {
            return root;
        }

        int inorderIndex = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (rootVal.equals(inorder[i])) {
                inorderIndex = i;
            }
        }

        int leftTreeSize = inorderIndex;
        int rightTreeSize = preorder.length - inorderIndex - 1;

        T[] newLeftPreorder = (T[]) new Object[leftTreeSize];
        T[] newRightPreorder = (T[]) new Object[rightTreeSize];
        T[] newLeftInorder = (T[]) new Object[leftTreeSize];
        T[] newRightInorder = (T[]) new Object[rightTreeSize];

        System.arraycopy(preorder, 1, newLeftPreorder, 0, leftTreeSize);
        System.arraycopy(preorder, inorderIndex + 1, newRightPreorder, 0, rightTreeSize);
        System.arraycopy(inorder, 0, newLeftInorder, 0, leftTreeSize);
        System.arraycopy(inorder, inorderIndex + 1, newRightInorder, 0, rightTreeSize);

        root.left = constructFromPreorderAndInorder(newLeftPreorder, newLeftInorder);
        root.right = constructFromPreorderAndInorder(newRightPreorder, newRightInorder);

        return root;
    }

    public static <T> TreeNode<T> constructFromInorderAndPostorder(T[] inorder, T[] postorder) {
        if (postorder.length == 0) {
            return null;
        }
        T rootVal = postorder[postorder.length - 1];
        TreeNode<T> root = new TreeNode<>(null, null, rootVal);
        if (postorder.length == 1) {
            return root;
        }

        int inorderIndex = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (rootVal.equals(inorder[i])) {
                inorderIndex = i;
            }
        }

        int leftTreeSize = inorderIndex;
        int rightTreeSize = postorder.length - inorderIndex - 1;

        T[] newLeftPostorder = (T[]) new Object[leftTreeSize];
        T[] newRightPostorder = (T[]) new Object[rightTreeSize];
        T[] newLeftInorder = (T[]) new Object[leftTreeSize];
        T[] newRightInorder = (T[]) new Object[rightTreeSize];

        System.arraycopy(postorder, 0, newLeftPostorder, 0, leftTreeSize);
        System.arraycopy(postorder, inorderIndex, newRightPostorder, 0, rightTreeSize);
        System.arraycopy(inorder, 0, newLeftInorder, 0, leftTreeSize);
        System.arraycopy(inorder, inorderIndex + 1, newRightInorder, 0, rightTreeSize);

        root.left = constructFromInorderAndPostorder(newLeftInorder, newLeftPostorder);
        root.right = constructFromInorderAndPostorder(newRightInorder, newRightPostorder);

        return root;
    }

    public static void main(String[] args) {
        String[] preorder = {"A","B","D","E","C","F"};
        String[] inorder = {"D","B","E","A","F","C"};
        String[] postorder = {"D","E","B","F","C","A"};
        TreeNode root1 = constructFromPreorderAndInorder(preorder, inorder);
        TreeNode root2 = constructFromInorderAndPostorder(inorder, postorder);
        TreeUtils.print(root1);
        TreeUtils.print(root2);
    }

}
