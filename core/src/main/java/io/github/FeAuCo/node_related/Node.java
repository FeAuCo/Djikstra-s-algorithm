package io.github.FeAuCo.node_related;

public class Node implements Cloneable{
    private NodeTypes type;
    private float value;
    private final int[] coordinates;
    private final int[] indices;
    private Node previousNode;

    public Node(int[] coordinates, int[] indices){
        this.coordinates = coordinates;
        this.indices = indices;

        value = Float.POSITIVE_INFINITY;
        type = NodeTypes.EMPTY;
    }

    @Override
    public Node clone() {
        try {
            return (Node) super.clone();
        }
        catch (CloneNotSupportedException cnse){
            return null;
        }
    }

    public String getTexture() {
        return type.getTexture();
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

    public int[] getIndices() {
        return indices;
    }

    public void setNodeType(NodeTypes type) {
        this.type = type;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }
}
