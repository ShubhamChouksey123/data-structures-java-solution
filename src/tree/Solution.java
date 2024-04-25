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
}
