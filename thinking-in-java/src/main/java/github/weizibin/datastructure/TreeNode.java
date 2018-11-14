package github.weizibin.datastructure;

public class TreeNode<T> {
    public TreeNode<T> left;
    public TreeNode<T> right;
    public T content;

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T content) {
        this.left = left;
        this.right = right;
        this.content = content;
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
