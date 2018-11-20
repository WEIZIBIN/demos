package github.weizibin.datastructure;

public class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public T val;

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T val) {
        this.left = left;
        this.right = right;
        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
