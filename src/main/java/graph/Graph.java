package graph;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph<T> {
    private boolean weighted;
    private boolean directional;
    private Map<T, Node<T>> nodes;


    public Graph(Map<T, Node<T>> nodes) {
        this.nodes = new HashMap<>(nodes);
    }

    public Graph() {
        nodes = new HashMap<>();
    }

    public Graph(String filepath) {
        try (FileReader input = new FileReader(filepath)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Graph<T>>() {
            }.getType();

            Graph<T> graph = gson.fromJson(input, type);

            nodes = graph.getNodes();
            weighted = graph.isWeighted();
            directional = graph.isDirectional();

        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }

    public void saveToJson(String path, String filename) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(path + filename)) {
            final Graph<T> graph = new Graph<>(getNodes());
            final String json = gson.toJson(graph);
            writer.write(json);
        } catch (IOException exp) {
            exp.printStackTrace();
        }
    }


    public void addNode(T label, Map<T, Long> connections) {
        nodes.put(label, new Node<>(label, connections));
    }

    public void addConnection(T label1, T label2, Long weight) {
        if (directional) {
            nodes.get(label1).weights.put(label2, weight);
        } else {
            nodes.get(label1).weights.put(label2, weight);
            nodes.get(label2).weights.put(label1, weight);
        }
    }

    public void removeNode(T label) {
        for (Node<T> node : nodes.values()) {
            node.weights.remove(label);
        }
        nodes.remove(label);
    }

    public void removeConnection(T label1, T label2){
        nodes.get(label1).weights.remove(label2);
        nodes.get(label2).weights.remove(label1);
    }



    public boolean isWeighted() {
        return weighted;
    }

    public void setWeighted(boolean weighted) {
        this.weighted = weighted;
    }

    public boolean isDirectional() {
        return directional;
    }

    public void setDirectional(boolean directional) {
        this.directional = directional;
    }

    private Map<T, Node<T>> getNodes() {
        return nodes;
    }
}
