package ed.lab;

public class E02KthSmallest {
    private int count;
    private int result;

    public int kthSmallest(TreeNode<Integer> root, int k) {
        count = k;
        result = -1;
        inorder(root);
        return result;
    }

    private void inorder(TreeNode<Integer> node) {
        if (node == null || count == 0) {
            return;
        }

        inorder(node.left);

        count--;
        if (count == 0) {
            result = node.value;
            return;
        }

        inorder(node.right);
    }
}