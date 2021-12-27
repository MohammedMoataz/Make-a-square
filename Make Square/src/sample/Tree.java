package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Tree<T> {
    private T matrix;
    private List<Tree<T>> children;

    public Tree(T matrix) {
        this.matrix = matrix;
    }

    public void addChild(T matrix) {
        if (this.children == null)
            this.children = new ArrayList<>();

        Tree<T> child = new Tree<>(matrix);
        this.children.add(child);
    }

    @Override
    public String toString() {
        return Objects.toString(this.matrix);
    }
}
