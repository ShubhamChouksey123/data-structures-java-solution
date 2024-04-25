package tree;

public class MainClass {


    public static void main(String[] args) {
        TreeNode root = createTree();

        travers( root);
    }

    private static TreeNode createTree() {

        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);


        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        return treeNode1;
    }

    private static void travers(TreeNode root) {

        if (root == null)
            return;

        System.out.print(root.val + " ");
        travers(root.left);
        travers(root.right);

    }

}
