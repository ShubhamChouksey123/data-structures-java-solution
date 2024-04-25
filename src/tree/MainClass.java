package tree;

public class MainClass {


    public static void main(String[] args) {
        TreeNode root = createTree();

//        travers(root);

        Solution solution = new Solution();
        int[] inorder = new int[]{4, 2, 5, 1, 6, 3, 7};
        int[] postorder = new int[]{4, 5, 2, 6, 7, 3, 1};

//        TreeNode rootNew = solution.buildTree(inorder, postorder);
//        travers(rootNew);

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
