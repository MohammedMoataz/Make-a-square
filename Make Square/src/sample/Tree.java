package sample;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private static class Node {
        private boolean[][] matrix;
        private Thread thread;
        private List<Node> children;

        private Node() {
            this.matrix = new boolean[][]{
                    {false, false, false, false},
                    {false, false, false, false},
                    {false, false, false, false},
                    {false, false, false, false}
            };
            this.thread = null;
            this.children = new ArrayList<>();
        }

        private Node(boolean[][] matrix) {
            this.matrix = matrix;
            this.thread = null;
            this.children = null;
        }
    }

    private Node node;

    public Tree() {
        this.node = new Node();
    }

    public void addNode(boolean[][] matrix) {
        if (this.node.children == null)
            this.node.children = new ArrayList<>();

        Node child = new Node(matrix);

        this.node.children.add(child);
    }

    public void travers() {
        for (Node child : this.node.children) {

        }
    }
}
