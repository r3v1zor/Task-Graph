package graph;

import java.util.HashMap;
import java.util.Map;

public class Node<T> {
    T label;
    Map<T, Long> weights;

    public Node(T label, Map<T, Long> weights) {
        this.label = label;
        this.weights = weights;
    }

    public Node(T label) {
        this.label = label;
        weights = new HashMap<>();
    }

    public Node(){
        weights = new HashMap<>();
    }
}
