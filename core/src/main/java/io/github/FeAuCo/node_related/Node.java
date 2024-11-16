package io.github.FeAuCo.node_related;

import java.util.ArrayList;

public class Node {
    private ArrayList<Node> frontier = new ArrayList<>();
    private NodeTypes type;
    private float value;
    private int[] coordinates;


    public Node(NodeTypes type, int[] coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }


    public String getTexture() {
        return type.getTexture();
    }

    public ArrayList<Node> getNodeBonds() {
        return frontier;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public NodeTypes getNodeType() {
        return type;
    }

    public float getValue() {
        return type.equals(NodeTypes.BARRIER) || type.equals(NodeTypes.START) ? type.getValue() : value;
    }

    public void setNodeType(NodeTypes type) {
        this.type = type;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void addNode(Node node) {
        this.frontier.add(node);
    }
}
