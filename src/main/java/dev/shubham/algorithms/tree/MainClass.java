package dev.shubham.algorithms.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainClass {

    public static TreeNode treeNode1 = new TreeNode(3);
    public static TreeNode treeNode2 = new TreeNode(5);
    public static TreeNode treeNode3 = new TreeNode(1);

    public static TreeNode treeNode4 = new TreeNode(6);
    public static TreeNode treeNode5 = new TreeNode(2);
    public static TreeNode treeNode6 = new TreeNode(0);
    public static TreeNode treeNode7 = new TreeNode(8);

    public static TreeNode treeNode8 = new TreeNode(15);
    public static TreeNode treeNode9 = new TreeNode(11);
    public static TreeNode treeNode10 = new TreeNode(7);
    public static TreeNode treeNode11 = new TreeNode(4);

    public static void main(String[] args) {
        TreeNode root = createTree();

        travers(root);
        System.out.println();

        // BSTIterator bstIterator = new BSTIterator(root);

        // while (bstIterator.hasNext()) {
        // System.out.println("ans : " + bstIterator.next());
        // }

        // System.out.println("ans : " + bstIterator.next() + " and has next : " +
        // bstIterator.hasNext());
        // System.out.println("ans : " + bstIterator.next() + " and has next : " +
        // bstIterator.hasNext());
        // System.out.println("ans : " + bstIterator.next() + " and has next : " +
        // bstIterator.hasNext());
        //
        // System.out.println("ans : " + bstIterator.next() + " and has next : " +
        // bstIterator.hasNext());
        // System.out.println("ans : " + bstIterator.next() + " and has next : " +
        // bstIterator.hasNext());
        // System.out.println("ans : " + bstIterator.next() + " and has next : " +
        // bstIterator.hasNext());
        // System.out.println("ans : " + bstIterator.next() + " and has next : " +
        // bstIterator.hasNext());
        // System.out.println("ans : " + bstIterator.next() + " and has next : " +
        // bstIterator.hasNext());
        // System.out.println("ans : " + bstIterator.next() + " and has next : " +
        // bstIterator.hasNext());

        Solution solution = new Solution();
        // solution.flatten(root);

        // int ans = solution.maxPathSum(root);
        // System.out.println("ans : " + ans);

        // solution.rightSideView(root);
        int[] inorder = new int[]{4, 2, 5, 1, 6, 3, 7};
        int[] postorder = new int[]{4, 5, 2, 6, 7, 3, 1};

        // TreeNode rootNew = solution.buildTree(inorder, postorder);
        // travers(rootNew);

        TreeNode common = solution.lowestCommonAncestor(root, treeNode9, treeNode2);
        System.out.println("common: " + common);

        testPathSum();
    }

    private static TreeNode createTree() {

        // TreeNode treeNode1 = new TreeNode(3);
        // TreeNode treeNode2 = new TreeNode(5);
        // TreeNode treeNode3 = new TreeNode(1);
        //
        //
        // TreeNode treeNode4 = new TreeNode(6);
        // TreeNode treeNode5 = new TreeNode(2);
        // TreeNode treeNode6 = new TreeNode(0);
        // TreeNode treeNode7 = new TreeNode(8);
        //
        //
        // TreeNode treeNode8 = new TreeNode(15);
        // TreeNode treeNode9 = new TreeNode(11);
        // TreeNode treeNode10 = new TreeNode(7);
        // TreeNode treeNode11 = new TreeNode(4);

        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;

        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;

        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;

        treeNode4.left = treeNode8;
        treeNode4.right = treeNode9;

        treeNode5.left = treeNode10;
        treeNode5.right = treeNode11;

        return treeNode1;
    }

    private static void travers(TreeNode root) {

        if (root == null)
            return;

        System.out.print(root.val + " ");
        travers(root.left);
        travers(root.right);
    }

    private static void testPathSum() {

        TreeNode treeNode1 = new TreeNode(1000000000);
        TreeNode treeNode2 = new TreeNode(1000000000);
        TreeNode treeNode3 = new TreeNode(294967296);
        TreeNode treeNode4 = new TreeNode(1000000000);
        TreeNode treeNode5 = new TreeNode(1000000000);
        TreeNode treeNode6 = new TreeNode(1000000000);

        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        treeNode3.left = treeNode4;
        treeNode4.left = treeNode5;
        treeNode5.left = treeNode6;

        Solution solution = new Solution();
        assertEquals(0, solution.pathSum(treeNode1, 0));

    }
}
