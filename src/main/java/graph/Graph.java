package graph;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Graph<T> {
    boolean weighted;
    boolean directional;
    Map<T, Node<T>> nodes;


    public Graph(Map<T, Node<T>> nodes) {
        this.nodes = new HashMap<>(nodes);

    }

    public Graph() {
        nodes = new HashMap<>();
    }

    public Graph(String filepath) {
        JSONParser parser = new JSONParser();
        try (FileReader input = new FileReader(filepath)) {
            JSONObject jsObject = (JSONObject) parser.parse(input);
            String type = (String) jsObject.get("type");
            weighted = (boolean) jsObject.get("weighted");
            directional = (boolean) jsObject.get("directional");


            JSONArray map = (JSONArray) jsObject.get("map");
            map.forEach(node -> {
                Node<T> nodeT = parseNode((JSONObject) node);

            });
        } catch (IOException | ParseException exp) {
            exp.printStackTrace();
        }
    }

    private Node<T> parseNode(JSONObject node) {

        System.out.println(node);
        return null;
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
}
