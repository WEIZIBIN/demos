package github.weizibin.datastructure;

import github.weizibin.datastructure.util.TreeUtils;

public class ConstructTreeFromGivenOrder {

    public static <T> TreeNode<T> constructFromPreorderAndInorder(int preStart, int inStart, int inEnd, T[] preorder, T[] inorder) {
        if (inStart > inEnd) {
            return null;
        }

        T rootVal = preorder[preStart];
        TreeNode<T> root = new TreeNode<>(null, null, rootVal);
        int inorderIndex = -1;
        for (int i = inStart; i < inEnd + 1; i++) {
            if (rootVal.equals(inorder[i])) {
                inorderIndex = i;
                break;
            }
        }

        root.left = constructFromPreorderAndInorder(preStart + 1, inStart, inorderIndex - 1, preorder, inorder);
        root.right = constructFromPreorderAndInorder(preStart + 1 + inorderIndex - inStart, inorderIndex + 1, inEnd, preorder, inorder);

        return root;
    }

    public static <T> TreeNode<T> constructFromInorderAndPostorder(int postEnd, int inStart, int inEnd, T[] inorder, T[] postorder) {
        if (inStart > inEnd) {
            return null;
        }

        T rootVal = postorder[postEnd];
        TreeNode<T> root = new TreeNode<>(null, null, rootVal);
        int inorderIndex = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (rootVal.equals(inorder[i])) {
                inorderIndex = i;
            }
        }

        root.left = constructFromInorderAndPostorder(postEnd - 1 + inorderIndex - inEnd, inStart, inorderIndex - 1, inorder, postorder );
        root.right = constructFromInorderAndPostorder(postEnd - 1, inorderIndex + 1, inEnd,inorder, postorder);

        return root;

    }

    public static void main(String[] args) {
        String[] preorder = {"A", "B", "D", "E", "C", "F"};
        String[] inorder = {"D", "B", "E", "A", "F", "C"};
        String[] postorder = {"D", "E", "B", "F", "C", "A"};
        TreeNode root1 = constructFromPreorderAndInorder(0, 0, inorder.length - 1, preorder, inorder);
        TreeNode root2 = constructFromInorderAndPostorder(postorder.length - 1,0, inorder.length - 1,inorder, postorder);
        TreeUtils.print(root1);
        TreeUtils.print(root2);
    }

}
