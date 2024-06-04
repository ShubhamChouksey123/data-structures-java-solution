package com.shubham.app.graph;

import java.util.List;

public class Node {

    public int val;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public List<Node> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Node> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public String toString() {
        return "Node{" + "val=" + val + '}';
    }
}
