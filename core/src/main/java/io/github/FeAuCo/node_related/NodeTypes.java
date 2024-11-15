package io.github.FeAuCo.node_related;

public enum NodeTypes {
    START("start_node_dot.png", 0),
    EMPTY("empty_node_gray.png"),
    VISITED("visited_node_green.png"),
    BARRIER("barrier_node_brown.png", Float.POSITIVE_INFINITY),
    END("end_node_cross.png");

    final String texture;
    float value;

    NodeTypes(String texture){
        this.texture = texture;
    }

    NodeTypes(String texture, float value){
        this.texture = texture;
        this.value = value;
    }

    public String getTexture() {
        return texture;
    }

    public float getValue() {
        return value;
    }
}
