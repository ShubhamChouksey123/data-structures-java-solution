package com.shubham.app.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Solution {

    public Integer minDiff = Integer.MAX_VALUE;

    public Integer prev = null;
    public Integer nodeVisitedCount = 0;
    public Integer kthSmallestNode = 0;
    public Integer maxPathSum = Integer.MIN_VALUE;
    private List<TreeNode> ancestorsP;
    private List<TreeNode> ancestorsQ;


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

    public void kthSmallestUtil(TreeNode root, int k) {

        if (root == null)
            return;

        kthSmallestUtil(root.left, k);

        nodeVisitedCount++;
        if (nodeVisitedCount == k) {
            kthSmallestNode = root.val;
        }
        // System.out.println("root.val : " + root.val);
        kthSmallestUtil(root.right, k);
    }

    public int kthSmallest(TreeNode root, int k) {
        this.kthSmallestNode = 0;
        kthSmallestUtil(root, k);
        return kthSmallestNode;
    }

    public int getMinimumDifference(TreeNode root) {

        if (root == null) {
            return Integer.MAX_VALUE;
        }
        getMinimumDifference(root.left);

        if (prev != null) {
            minDiff = Math.max(minDiff, Math.abs(root.val - prev));
        }

        getMinimumDifference(root.right);

        return minDiff;
    }

    public boolean hasPathSum(TreeNode root, int targetSum, int currentSum) {

        if (root == null)
            return false;

        currentSum = currentSum + root.val;
        if (root.left == null && root.right == null) {
            if (targetSum == currentSum)
                return true;
        }

        return hasPathSum(root.left, targetSum, currentSum) || hasPathSum(root.right, targetSum, currentSum);
    }


    public int maxPathSumUtil(TreeNode root) {

        if (root == null)
            return 0;

        if (root.right == null && root.left == null) {
            this.maxPathSum = Math.max(root.val, this.maxPathSum);
            return root.val;
        }

        int thisRootSum = root.val;
        int leftSum = 0;
        if (root.left != null) {
            leftSum = maxPathSumUtil(root.left);
        }
        int rightSum = 0;
        if (root.right != null) {
            rightSum = maxPathSumUtil(root.right);
        }

        this.maxPathSum = Math.max(root.val, this.maxPathSum);
        this.maxPathSum = Math.max(root.val + leftSum, this.maxPathSum);
        this.maxPathSum = Math.max(root.val + rightSum, this.maxPathSum);
        this.maxPathSum = Math.max(root.val + leftSum + rightSum, this.maxPathSum);

        int a = root.val;
        int b = root.val + leftSum;
        int c = root.val + rightSum;
        int d = root.val + leftSum + rightSum;
        return Math.max(a, Math.max(b, c));
    }

    public int maxPathSum(TreeNode root) {

        this.maxPathSum = Integer.MIN_VALUE;
        maxPathSumUtil(root);

        System.out.println("maxPathSum : " + this.maxPathSum);
        return this.maxPathSum;
    }

    public void getAllAncestorsP(TreeNode root, TreeNode p, Stack<TreeNode> ancestors) {

        if (root == null)
            return;

        if (root == p) {
            this.ancestorsP = new ArrayList<>(ancestors);
        }

        ancestors.add(root);

        if (root.left != null) {
            getAllAncestorsP(root.left, p, ancestors);
        }

        if (root.right != null) {
            getAllAncestorsP(root.right, p, ancestors);
        }

        ancestors.pop();
    }

    public void getAllAncestorsQ(TreeNode root, TreeNode p, Stack<TreeNode> ancestors) {

        if (root == null)
            return;

        if (root == p) {
            this.ancestorsQ = new ArrayList<>(ancestors);
        }

        ancestors.add(root);

        if (root.left != null) {
            getAllAncestorsQ(root.left, p, ancestors);
        }

        if (root.right != null) {
            getAllAncestorsQ(root.right, p, ancestors);
        }

        ancestors.pop();
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null)
            return null;

        if (p == q)
            return p;

        getAllAncestorsP(root, p, new Stack<>());
        ancestorsP.add(p);
        System.out.println("ancestorsP : " + ancestorsP);

        getAllAncestorsQ(root, q, new Stack<>());
        ancestorsQ.add(q);

        System.out.println("ancestorsQ : " + ancestorsQ);

        int index = 0;
        TreeNode common = null;
        while (index < ancestorsP.size() && index < ancestorsQ.size()) {
            if (!Objects.equals(ancestorsP.get(index), ancestorsQ.get(index))) {
                System.out.println("common: " + common);
                return common;
            }
            common = ancestorsP.get(index);
            index++;
        }

        System.out.println("common: " + common);
        return common;
    }


    private boolean isLeaf(TreeNode root) {

        if (root == null)
            return false;

        if (root.left == null && root.right == null) {
            return true;
        }
        return false;
    }

    private boolean convertToBoolean(TreeNode root) {

        if (root == null)
            return false;

        return (root.val > 0) ? Boolean.TRUE : Boolean.FALSE;
    }

    private boolean isAttachedToLeaf(TreeNode root) {

        if (root == null || root.left == null || root.right == null) {
            return false;
        }
        if (isLeaf(root.left) && isLeaf(root.right)) {
            return true;
        }

        return false;
    }

    private boolean evaluateTreeUtil(TreeNode root) {

        if (root == null)
            return false;


        if (root.left == null || root.right == null) {
            return (root.val > 0) ? Boolean.TRUE : Boolean.FALSE;
        }

        if (isAttachedToLeaf(root)) {
            if (root.val == 2) {
                return convertToBoolean(root.left) || convertToBoolean(root.right);
            } else if (root.val == 3) {
                return convertToBoolean(root.left) && convertToBoolean(root.right);
            }
        }
        boolean left = evaluateTreeUtil(root.left);
        boolean right = evaluateTreeUtil(root.right);

        if (root.val == 2) {
            return left || right;
        } else if (root.val == 3) {
            return left && right;
        }

        return Boolean.FALSE;
    }

    public boolean evaluateTree(TreeNode root) {


        return evaluateTreeUtil(root);
    }


    public TreeNode flattenUtility(TreeNode root) {

        if (root == null)
            return null;
        if (root.left == null && root.right == null) {
            return root;
        }
        if (root.left != null) {
            root.left = flattenUtility(root.left);
        }
        if (root.right != null) {
            root.right = flattenUtility(root.right);
        }
        if (root.right == null) {
            root.right = root.left;
            root.left = null;
        } else if (root.left != null) {
            /**
             * both not null
             */
            TreeNode right = root.right;
            root.right = root.left;
            root.left = null;
            TreeNode lastNode = root.right;
            while (lastNode != null && lastNode.right != null) {
                lastNode = lastNode.right;
            }
            lastNode.right = right;

        }

        return root;
    }

    public void flatten(TreeNode root) {

        if (root == null)
            return;

        flattenUtility(root);

    }

    private void connect(Node root, List<Node> rightNode, int level) {

        if (root == null)
            return;

        if (rightNode.size() <= level) {
            rightNode.add(root);
        } else {
            root.next = rightNode.get(level);
            rightNode.set(level, root);
        }
        connect(root.right, rightNode, level + 1);
        /**
         * root.value
         */

        connect(root.left, rightNode, level + 1);

    }

    public Node connect(Node root) {

        if (root == null)
            return null;

        connect(root, new ArrayList<>(), 0);
        return root;
    }


}
