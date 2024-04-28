package tree;

import java.util.ArrayList;
import java.util.List;

public class Solution {


    public Node connect(Node root) {

        if (root == null) {
            return null;
        }

        connectUtil(root.left, root.right);
        return root;

    }

    public void connectUtil(Node root1, Node root2) {

        if (root1 == null && root2 == null) {
            return;
        }
        if (root1 == null) {
            connectUtil(root2.left, root2.right);
        } else if (root2 == null) {
            connectUtil(root1.left, root1.right);
        } else {
            root1.next = root2;

            if (root1.left != null) {
                if (root1.right != null) {
                    connectUtil(root1.left, root1.right);
                } else if (root2.left != null) {
                    connectUtil(root1.left, root2.left);
                } else if (root2.right != null) {
                    connectUtil(root1.left, root2.right);
                }
            }

            if (root1.right != null) {
                if (root2.left != null) {
                    connectUtil(root1.right, root2.left);
                } else if (root2.right != null) {
                    connectUtil(root1.right, root2.right);
                }
            }

            if (root2.left != null) {
                if (root2.right != null) {
                    connectUtil(root2.left, root2.right);
                }
            }
        }


    }

    public List<Double> averageOfLevels(TreeNode root) {

        if (root == null) {
            return new ArrayList<>();
        }

        List<Double> sums = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        averageOfLevelsUtil(root, sums, counts, 0);


        for (int i = 0; i < sums.size(); i++) {
            sums.set(i, sums.get(i) / counts.get(i));
        }
        return sums;
    }

    public void averageOfLevelsUtil(TreeNode root, List<Double> sums, List<Integer> counts, int level) {

        if (root == null) {
            return;
        }

        if (sums.size() < level + 1) {
            sums.add((double) root.val);
            counts.add(1);
        } else {
            sums.set(level, sums.get(level) + root.val);
            counts.set(level, counts.get(level) + 1);
        }


        averageOfLevelsUtil(root.left, sums, counts, level + 1);
        averageOfLevelsUtil(root.right, sums, counts, level + 1);

    }

    public int getMinimumDifference(TreeNode root) {
        return 0;
    }


    public int getMinimumDifferenceUtil(TreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (root.left == null && root.right == null) {
            return root.val;
        }

        int leftValue = getMinimumDifferenceUtil(root.left);
        int rightValue = getMinimumDifferenceUtil(root.right);

        return Math.min(root.val - leftValue, rightValue - root.val);
    }


    public boolean isValidBST(TreeNode root) {
        boolean isValid = isValidBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
        System.out.print("isValid : " + isValid);
        return isValid;
    }


    public boolean isValidBSTUtil(TreeNode root, Long minValue, Long maxValue) {

        if (root == null)
            return true;

        long value = root.val;
        if (root.val < minValue || root.val > maxValue) {
            return false;
        }

        return isValidBSTUtil(root.left, minValue, value - 1) && isValidBSTUtil(root.right, value + 1, maxValue);
    }


    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        List<List<Integer>> zigzag = new ArrayList<>();
        zigzagLevelOrderUtil(root, 0, zigzag);
        return zigzag;
    }

    public void zigzagLevelOrderUtil(TreeNode root, int level, List<List<Integer>> zigzag) {

        if (root == null)
            return;

        if (zigzag.size() <= level) {
            List<Integer> row = new ArrayList<>();
            row.add(root.val);
            zigzag.add(row);
        } else {
            if (level % 2 == 0) {
                zigzag.get(level).add(root.val);
            } else {
                zigzag.get(level).add(0, root.val);
            }
        }
        zigzagLevelOrderUtil(root.left, level + 1, zigzag);
        zigzagLevelOrderUtil(root.right, level + 1, zigzag);

    }

    public int maxDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth((root.right)));

    }

    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        if (root.left != null && root.right != null) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
        } else if (root.left != null) {
            root.right = root.left;
            root.left = null;
        } else if (root.right != null) {
            root.left = root.right;
            root.right = null;
        }

        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    public boolean isMirrorImage(TreeNode root1, TreeNode root2) {

        if (root1 == null && root2 == null)
            return true;

        if (root1 == null || root2 == null)
            return false;

        if (root1.val != root2.val)
            return false;

        return isMirrorImage(root1.left, root2.right) && isMirrorImage(root1.right, root2.left);

    }

    public boolean isSymmetric(TreeNode root) {

        if (root == null)
            return true;

        return isMirrorImage(root.left, root.right);
    }

}
