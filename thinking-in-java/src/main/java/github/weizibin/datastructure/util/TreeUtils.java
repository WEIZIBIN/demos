package github.weizibin.datastructure.util;

import github.weizibin.datastructure.TreeNode;

public class TreeUtils {

    public static int level(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = level(root.left);
        int right = level(root.right);
        return left > right ? left + 1 : right + 1;
    }
    
}
