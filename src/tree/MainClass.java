package tree;

import java.util.ArrayList;
import java.util.List;

public class MainClass {


    public static void main(String[] args) {
        TreeNode root = createTree();

        travers(root);

        Solution solution = new Solution();
        solution.getMinimumDifference(root);


//        solution.rightSideView(root);
        int[] inorder = new int[]{4, 2, 5, 1, 6, 3, 7};
        int[] postorder = new int[]{4, 5, 2, 6, 7, 3, 1};

//        TreeNode rootNew = solution.buildTree(inorder, postorder);
//        travers(rootNew);

    }

    private static TreeNode createTree() {

        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);


        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
//
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

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
