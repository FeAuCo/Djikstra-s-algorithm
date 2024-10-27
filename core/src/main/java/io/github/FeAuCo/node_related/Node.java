package io.github.FeAuCo.node_related;

import java.util.ArrayList;

public class Node {
    private ArrayList<Node> nodeBonds = new ArrayList<>();
    private NodeTypes type;
    private float value;
    private float[] coordinates = new float[2];


    Node(NodeTypes type, float value, float[] coordinates, ArrayList<Node> nodeBonds){
        this.type = type;
        this.value = value;
        this.nodeBonds = nodeBonds;
        this.coordinates = coordinates;
    }


    public String getTexture(NodeTypes type){
        return switch (type){
            case START -> "start_node_dot.png";
            case EMPTY -> "empty_node_gray.png";
            case VISITED -> "visited_node_green.png";
            case BARRIER -> "barrier_node_brown.png";
            case END -> "end_node_cross.png";
        };
    }


    public ArrayList<Node> getNodeBonds() {
        return nodeBonds;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public NodeTypes getNodeType() {
        return type;
    }

    public float getValue() {
        return value;
    }

    public void setNodeType(NodeTypes type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void addNodeBonds(Node node) {
        this.nodeBonds.add(node);
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }
}
