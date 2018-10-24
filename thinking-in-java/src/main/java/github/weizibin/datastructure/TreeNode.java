package github.weizibin.datastructure;

// TODO: 2018/10/24  
public class TreeNode<T> {
    public TreeNode left;
    public TreeNode right;
    public T content;

    public TreeNode() {

    }

    public TreeNode(TreeNode left, TreeNode right, T content) {
        this.left = left;
        this.right = right;
        this.content = content;
    }
}
