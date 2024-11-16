package io.github.FeAuCo.node_related;

import java.util.ArrayList;

public class Node {
    private ArrayList<Node> frontier;
    private NodeTypes type;
    private float value;
    private int[] coordinates;
    private Node previousNode;

    public Node(){
        frontier = new ArrayList<>();
        if (type.equals(NodeTypes.START)){
            value = 0;
        }
        else{
            value = Float.POSITIVE_INFINITY;
        }
    }


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
        return value;
    }

    public Node getPreviousNode() {
        return previousNode;
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

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }
}
