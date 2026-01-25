package dev.shubham.algorithms.tree;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {

    public List<TreeNode> rootNodes;
    public Integer currentIndex;

    public BSTIterator(TreeNode root) {
        rootNodes = new ArrayList<>();
        addAllLeftNode(root, 0);
        currentIndex = rootNodes.size() - 1;
    }

    private boolean isValid(List<TreeNode> rootNodes, int index) {
        if (index >= 0 && index < rootNodes.size()) {
            return true;
        }
        return false;
    }

    public void addAllLeftNode(TreeNode root, int index) {
        while (root != null) {
            if (rootNodes.size() <= index) {
                rootNodes.add(root);
            } else {
                rootNodes.set(index, root);
            }
            index++;
            root = root.left;
        }
    }

    public int next() {
        TreeNode currentNode = rootNodes.get(currentIndex);

        if (currentNode.right != null) {
            addAllLeftNode(currentNode.right, currentIndex + 1);
            currentIndex = rootNodes.size() - 1;
        } else {
            int index = currentIndex - 1;
            while (isValid(rootNodes, index) && rootNodes.get(index).val < currentNode.val) {
                rootNodes.remove(index + 1);
                index--;
            }
            rootNodes.remove(index + 1);
            currentIndex = index;
        }

        if (isValid(rootNodes, currentIndex + 1) && currentNode.val > rootNodes.get(currentIndex + 1).val) {
            addAllLeftNode(currentNode.right, currentIndex + 1);
            currentIndex++;
        }

        System.out.println("rootNodes : " + rootNodes);
        return currentNode.val;
    }

    public boolean hasNext() {

        if (!isValid(rootNodes, currentIndex)) {
            return false;
        }
        if (rootNodes.isEmpty()) {
            return false;
        }

        TreeNode currentNode = rootNodes.get(currentIndex);

        if (currentNode.right != null) {
            return true;
        }

        // if(currentNode.val >= rootNodes.get(0).val){
        // return false;
        // }

        return true;
    }
}
